package com.dragonrider.SWEotE;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.DiceRoller;
import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.Classes.XmlImport;
import com.dragonrider.SWEotE.EditorNPC.NPCEditorActivity;
import com.dragonrider.SWEotE.GroundFightActivities.GroundFightMultiPanelActivity;
import com.dragonrider.SWEotE.GroundFightActivities.GroundFightPrepareList;
import com.dragonrider.SWEotE.NPCViewer.NpcViewerActivity;
import com.dragonrider.SWEotE.PlayerActivities.EditPlayerActivity;
import com.dragonrider.SWEotE.Sabacc.ActivitySabaccGame;
import com.dragonrider.SWEotE.Scenario.activityScenarioViewer;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {


    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    MainMenuDrawerAdapter adapter;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


        adapter = new MainMenuDrawerAdapter();

        adapter.addItem("Dés", (BitmapDrawable) getResources().getDrawable(R.drawable.dicerollermain));
        adapter.addMainMenuItem("Dés", new MainMenuItem()
                .setName("Nouveau jet de dé")
                .setDescription("Ouvre la fenetre de lancé de dés")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        new DiceRoller(MainActivity.this).ShowAlertDialog();
                    }
                }));


        adapter.addItem("Combat", (BitmapDrawable) getResources().getDrawable(R.drawable.existinggroundfight));
        adapter.addMainMenuItem("Combat", new MainMenuItem()
                .setName("Nouveau combat (Normal)")
                .setDescription("Crée un nouveau combat. Les PJs sont surpris par leurs assaillants, ou personne n'est surpris.")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        GroundFightMultiPanelActivity.StartFight(MainActivity.this, true, false, false);
                    }
                }));
        adapter.addMainMenuItem("Combat", new MainMenuItem()
                .setName("Nouveau combat (Surprise)")
                .setDescription("Crée un nouveau combat. Les PJs surprennent leurs assaillants")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        GroundFightMultiPanelActivity.StartFight(MainActivity.this, true, true, false);
                    }
                }));
        adapter.addMainMenuItem("Combat", new MainMenuItem()
                .setName("Continuer le combat")
                .setDescription("Continuer le combat en cours, tel qu'il a été laissé.")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        GroundFightMultiPanelActivity.StartFight(MainActivity.this, false, false, false);
                    }
                })
                .setCheck(new IMainMenuEnableCheck() {
                    @Override
                    public boolean Check() {
                        return GroundFightScene.getIsFighting();
                    }
                }));
        adapter.addMainMenuItem("Combat", new MainMenuItem()
                .setName("Préparer les combats")
                .setDescription("Permet de préparer les combats en saisissant à l'avance les protagonistes")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this,
                                GroundFightPrepareList.class);

                        startActivity(intent);
                    }
                }));
        adapter.addMainMenuItem("Combat", MainMenuItem.PrepareCombatSpecialMenuItem());





        adapter.addItem("PJ", (BitmapDrawable) getResources().getDrawable(R.drawable.playerinfomenu));
        adapter.addMainMenuItem("PJ", new MainMenuItem()
                .setName("Editer les PJ")
                .setDescription("Ajoute, modifie ou supprimer les PJ.")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this,
                                EditPlayerActivity.class);

                        startActivity(intent);

                    }
                }));


        adapter.addItem("PNJ", (BitmapDrawable) getResources().getDrawable(R.drawable.npceditormenu));
        adapter.addMainMenuItem("PNJ", new MainMenuItem()
                .setName("Editer les PNJ")
                .setDescription("Ajoute, modifie ou supprimer les PNJ.")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this,
                                NPCEditorActivity.class);

                        startActivity(intent);

                    }
                }));
        adapter.addMainMenuItem("PNJ", new MainMenuItem()
                .setName("Parcourir les PNJ")
                .setDescription("Affiche la totalité des PNJ.")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this,
                                NpcViewerActivity.class);

                        startActivity(intent);

                    }
                }));


        adapter.addItem("Scenarios",  (BitmapDrawable) getResources().getDrawable(R.drawable.scenarioeditormenu));
        adapter.addMainMenuItem("Scenarios", new MainMenuItem()
                .setName("Test")
                .setDescription("Teste l'afficheur de scénario")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this, activityScenarioViewer.class);
                        startActivity(intent);
                    }
                }));

        adapter.addItem("Sabacc", (BitmapDrawable) getResources().getDrawable(R.drawable.sabaccgamemenu));
        adapter.addMainMenuItem("Sabacc", new MainMenuItem()
                .setName("Nouvelle partie")
                .setDescription("Commence une partie de Sabacc")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this, ActivitySabaccGame.class);
                        intent.putExtra("NEWGAME", true);
                        startActivity(intent);
                    }
                }));
        adapter.addMainMenuItem("Sabacc", new MainMenuItem()
                .setName("Reprendre")
                .setDescription("Reprend la partie de Sabacc")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Intent intent = new Intent(MainActivity.this, ActivitySabaccGame.class);
                        intent.putExtra("NEWGAME", false);
                        startActivity(intent);
                    }
                }));


        adapter.addItem("Base de données", (BitmapDrawable) getResources().getDrawable(R.drawable.scenarioeditormenu));
        adapter.addMainMenuItem("Base de données", new MainMenuItem()
                .setName("Mettre à jour à partir du stockaged")
                .setDescription("Migre les fichier XML du stockage")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        XmlImport.UpdateDatabaseFromStorage(MainActivity.this);
                    }
                }));
        adapter.addMainMenuItem("Base de données", new MainMenuItem()
                .setName("Sauvegarder la base de donnée")
                .setDescription("Permet d'exporter la base vers le stockage")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Database db = new Database(MainActivity.this);
                        db.SaveDB();
                    }
                }));
        adapter.addMainMenuItem("Base de données", new MainMenuItem()
                .setName("Restaurer la base de donnée")
                .setDescription("Permet de restaurer la base de donnée à partir du fichier du stockage.")
                .setAction(new IMainMenuItemAction() {
                    @Override
                    public void Action() {
                        Database db = new Database(MainActivity.this);
                        db.LoadDB();
                    }
                }));


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);




        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActionBar().setTitle(R.string.MainMenu);
                invalidateOptionsMenu();
            }
        };



        mDrawerLayout.setDrawerListener(mDrawerToggle);


        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerLayout.openDrawer(mDrawerList);




    }


    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view

        return super.onPrepareOptionsMenu(menu);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = MainMenuItemFragment.newInstance(adapter.getItemTitle(position), adapter.getItemImage(position), adapter.getAllItems(position));


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mDrawerList)) {
            //inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }







    public interface IMainMenuItemAction {
        public void Action();

    }
    public interface IMainMenuEnableCheck {
        public boolean Check();
    }


    public class MainMenuDrawerAdapter extends BaseAdapter {


        ArrayList<BitmapDrawable> Bitmaps;
        ArrayList<String> Names;
        HashMap<String, ArrayList<MainMenuItem>> items;


        public MainMenuDrawerAdapter() {
            this.Names = new ArrayList<String>();
            this.Bitmaps = new ArrayList<BitmapDrawable>();
            this.items = new HashMap<String, ArrayList<MainMenuItem>>();

        }



        public MainMenuDrawerAdapter addItem(String name, BitmapDrawable drawable) {
            this.Bitmaps.add(drawable);
            this.Names.add(name);
            return this;
        }

        public String getItemTitle(int Position) {
            return this.Names.get(Position);
        }

        public BitmapDrawable getItemImage(int Position) {
            return this.Bitmaps.get(Position);
        }


        public ArrayList<MainMenuItem> getAllItems(int groupPosition) {

            return items.get(Names.get(groupPosition));
        }

        public MainMenuDrawerAdapter addMainMenuItem(String groupName, MainMenuItem item) {

            if (items.containsKey(groupName))
                items.get(groupName).add(item);
            else {
                items.put(groupName, new ArrayList<MainMenuItem>());
                items.get(groupName).add(item);
            }

            return this;
        }

        @Override
        public int getCount() {
            return Names.size();
        }

        @Override
        public Object getItem(int i) {
            return Names.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        DrawerMainMenuViewHolder holder;

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null)
            {
                holder = new DrawerMainMenuViewHolder();
                view = getLayoutInflater().inflate(R.layout.mainmenu_drawer_item, viewGroup, false);
                holder.img = (ImageView) view.findViewById(R.id.imageView);
                holder.txt = (TextView) view.findViewById(R.id.textView);
                view.setTag(holder);
            }
            else if (view.getTag() == null) {
                holder = new DrawerMainMenuViewHolder();
                holder.img = (ImageView) view.findViewById(R.id.imageView);
                holder.txt = (TextView) view.findViewById(R.id.textView);
                view.setTag(holder);
            }
            else
                holder = (DrawerMainMenuViewHolder) view.getTag();

            holder.img.setImageDrawable(Bitmaps.get(i));
            holder.txt.setText(Names.get(i));

            return view;
        }
    }

    static class DrawerMainMenuViewHolder {
        ImageView img;
        TextView txt;
    }



}
