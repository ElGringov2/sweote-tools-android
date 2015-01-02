package com.dragonrider.SWEotE;



import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.EncounterFile;
import com.dragonrider.SWEotE.GroundFightActivities.GroundFightMultiPanelActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MainMenuItemFragment extends Fragment {



    private static List<MainMenuItem> items;
    private static BitmapDrawable drawable;
    private static String title;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Title Titre du groupe.
     * @param drawable Image d'entete
     * @param items Listes des objets.
     * @return A new instance of fragment MainMenuItemFragment.
     */
    public static MainMenuItemFragment newInstance(String Title, BitmapDrawable drawable, List<MainMenuItem> items) {

        MainMenuItemFragment.items = items;
        MainMenuItemFragment.drawable = drawable;
        MainMenuItemFragment.title = Title;

        return new MainMenuItemFragment();
    }
    public MainMenuItemFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main_menu_item, container, false);

        ImageView img = (ImageView) rootView.findViewById(R.id.ImageView);
        img.setImageDrawable(MainMenuItemFragment.drawable);

        TextView txt = (TextView) rootView.findViewById(R.id.TextView);
        txt.setText(MainMenuItemFragment.title);

        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.linearLayout);

        for (MainMenuItem item : MainMenuItemFragment.items)
        {
            if (!item.getIsEnabled()) continue;
            if (item.equals(MainMenuItem.PrepareCombatSpecialMenuItem()))
            {
                AddAllPrepareFight(layout, inflater);

            }
            else {
                View smallView = inflater.inflate(R.layout.listitem_swlistitem, layout, false);
                TextView textViewTitle = (TextView) smallView.findViewById(R.id.swListItem_Title);
                textViewTitle.setText(item.getName());
                textViewTitle.setOnClickListener(new onMenuItemClickListener(item.getAction()));
                TextView textViewDesc = (TextView) smallView.findViewById(R.id.swListItem_Desc);
                textViewDesc.setText(item.getDescription());
                textViewDesc.setOnClickListener(new onMenuItemClickListener(item.getAction()));

                smallView.findViewById(R.id.swListItem_Image).setVisibility(View.GONE);
                layout.addView(smallView);
            }

            if (MainMenuItemFragment.items.indexOf(item) != MainMenuItemFragment.items.size() - 1)
            {
                View separator = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(4, 4, 4, 4);
                separator.setLayoutParams(params);
                separator.setBackground(getActivity().getResources().getDrawable(android.R.color.darker_gray));
                layout.addView(separator);
            }

        }

        return rootView;
    }

    private void AddAllPrepareFight(final LinearLayout layout, final LayoutInflater inflater) {


        AsyncTask<String, Integer, List<MainMenuItem>> task = new AsyncTask<String, Integer, List<MainMenuItem>>() {
            @Override
            protected List<MainMenuItem> doInBackground(String... strings) {
                File sdCardRoot = Environment.getExternalStorageDirectory();
                File dir = new File(sdCardRoot, "SWEotE/Encounters");
                File[] files = dir.listFiles();
                List<MainMenuItem> lst = new ArrayList<MainMenuItem>();

                for (File file : files) {
                    final EncounterFile encounterFile = EncounterFile.fromFile(file.getPath());
                    if (encounterFile != null) {
                        MainMenuItem item = new MainMenuItem();
                        item.setName(encounterFile.EncounterName);
                        item.setDescription(encounterFile.getDescription());
                        item.setAction(new MainActivity.IMainMenuItemAction() {
                            @Override
                            public void Action() {
                                encounterFile.LaunchFight();
                            }
                        });
                        lst.add(item);
                    }
                }


                return lst;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(List<MainMenuItem> items) {

                for (final MainMenuItem item : items) {
                    View smallView = inflater.inflate(R.layout.listitem_swlistitem, layout, false);
                    TextView textViewTitle = (TextView)smallView.findViewById(R.id.swListItem_Title);
                    textViewTitle.setText(item.getName());
                    textViewTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            item.getAction().Action();
                            GroundFightMultiPanelActivity.StartFight(getActivity(), false, true, false);
                        }
                    });
                    TextView textViewDesc = (TextView)smallView.findViewById(R.id.swListItem_Desc);
                    textViewDesc.setText(item.getDescription());
                    textViewDesc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            item.getAction().Action();
                            GroundFightMultiPanelActivity.StartFight(getActivity(), false, true, false);

                        }
                    });

                    smallView.findViewById(R.id.swListItem_Image).setVisibility(View.GONE);
                    layout.addView(smallView);
                    if (items.indexOf(item) != items.size() - 1)
                    {
                        View separator = new View(getActivity());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
                        params.setMargins(4, 4, 4, 4);
                        separator.setLayoutParams(params);
                        separator.setBackground(getActivity().getResources().getDrawable(android.R.color.darker_gray));
                        layout.addView(separator);
                    }
                }
            }
        };

        task.execute();
    }

    private class onMenuItemClickListener implements View.OnClickListener {
        private final MainActivity.IMainMenuItemAction Action;

        public onMenuItemClickListener(MainActivity.IMainMenuItemAction action) {
            this.Action = action;
        }
        @Override
        public void onClick(View view) {
            if (Action != null)
                Action.Action();
        }
    }

}
