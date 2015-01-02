package com.dragonrider.SWEotE.GroundFightActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.EncounterFile;
import com.dragonrider.SWEotE.Classes.SimpleEncounterFighter;
import com.dragonrider.SWEotE.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GroundFightPrepareList extends Activity {

    EncounterAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_fight_prepare_list);


        adapter = new EncounterAdapter(this);

        File sdCardRoot = Environment.getExternalStorageDirectory();
        File dir = new File(sdCardRoot, "SWEotE/Encounters");
        File[] files = dir.listFiles();
        for (File file : files)
        {
            EncounterFile encounterFile = EncounterFile.fromFile(file.getPath());

            if (encounterFile != null)
                adapter.addItem(encounterFile);
        }

        ((ListView)findViewById(R.id.GroundFightPrepareListMainListView)).setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ground_fight_prepare_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_addencounter) {
            EncounterFile newFile = new EncounterFile();
            newFile.EncounterName = "Nouvelle rencontre";
            newFile.Fighters = new ArrayList<SimpleEncounterFighter>();
            adapter.addItem(newFile);
        }
        return super.onOptionsItemSelected(item);
    }

    public class EncounterAdapter extends BaseAdapter {



        private List<EncounterFile> files = new ArrayList<EncounterFile>();


        private Context context;
        public EncounterAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return files.size();
        }

        @Override
        public Object getItem(int i) {
            return files.get(i);
        }

        public String getItemName(int i) {
            return ((EncounterFile)getItem(i)).EncounterName;
        }

        public String getItemFilename (int i) {
            return ((EncounterFile)getItem(i)).Filename;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            EncounterViewHolder holder;
            if (view != null) {
                holder = (EncounterViewHolder) view.getTag();
            }
            else
            {
                view = LayoutInflater.from(context).inflate(R.layout.groundfightpreparelist_item, viewGroup, false);

                holder = new EncounterViewHolder();
                holder.textView = (TextView) view.findViewById(R.id.GroundFightPrepareListMenuItemMainTextView);

                view.setTag(holder);

            }

            final EncounterFile file = (EncounterFile) getItem(i);

            if (file != null) {
                holder.textView.setText(file.EncounterName);
            }

            view.findViewById(R.id.GroundFightPrepareListMenuItemEditButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GroundFightPrepareActivity.class);
                    GroundFightPrepareActivity.File = file;
                    startActivity(intent);

                }
            });
            view.findViewById(R.id.GroundFightPrepareListMenuItemLaunchButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Delete
                    new AlertDialog.Builder(context)
                            .setMessage(R.string.delete_encounter_question)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String path = file.Filename;
                                    File fileToDel = new File(path);
                                    fileToDel.delete();
                                    adapter.notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();



                }
            });
            return view;
        }

        public void addItem(EncounterFile encounterFile) {
            this.files.add(encounterFile);
            notifyDataSetChanged();
        }
    }

    public static class EncounterViewHolder {
        TextView textView;
    }
}
