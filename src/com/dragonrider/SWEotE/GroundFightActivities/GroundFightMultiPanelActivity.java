package com.dragonrider.SWEotE.GroundFightActivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.R;

public class GroundFightMultiPanelActivity extends FragmentActivity {

    public static final String NEWFIGHT = "args_newfight";
    public static final String FOESSURPRISES = "args_surprise";
    public static final String ADDPLAYER = "args_addplayers";


    public static void StartFight(final Context context, final boolean NewFight, final boolean AddPlayer, final boolean PlayerSurprise) {
        final Intent intent = new Intent(context,
                GroundFightMultiPanelActivity.class);


        if (NewFight) {

            intent.putExtra(NEWFIGHT, true);
            intent.putExtra(FOESSURPRISES, PlayerSurprise);
            intent.putExtra(ADDPLAYER, true);

            if (GroundFightScene.Fighters.size() == 0) {
                context.startActivity(intent);
            } else {
                new AlertDialog.Builder(context)
                        .setMessage("Cette action terminera le combat en cours.")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                context.startActivity(intent);

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        })
                        .show();
            }

        }
        else {
            intent.putExtra(NEWFIGHT, false);
            intent.putExtra(FOESSURPRISES, PlayerSurprise);
            intent.putExtra(ADDPLAYER, AddPlayer);
            context.startActivity(intent);

        }


    }



    ViewPager mPager;
    CustomPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_fight_multi_panel);


        if (getIntent().getExtras().getBoolean(NEWFIGHT)) {
            GroundFightScene.Initialize(this);
            GroundFightScene.isFoesSurprised = getIntent().getExtras().getBoolean(FOESSURPRISES);
        }
        else
            GroundFightScene.RefreshContext(this);



        if (getIntent().getExtras().getBoolean(ADDPLAYER)) {
            Intent intent = new Intent(this, StartGroundFightActivity.class);
            startActivity(intent);
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        adapter = new CustomPagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(adapter);


        if (this.getActionBar() != null)
            this.getActionBar().setTitle(adapter.getPageTitle(0));

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //getActionBar().setSelectedNavigationItem(position);
                getActionBar().setTitle(adapter.getPageTitle(position));

            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ground_fight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.GroundFightMenu_AddFighter) {
            AddFightersActivity.Show(this, true);
            return true;
        }
        if (id == R.id.GroundFightMenu_AddFighters) {
            AddFightersActivity.Show(this, false);
            return true;
        }
        if (id == R.id.GroundFightMenu_AddEncounter)
            SelectEncounterPopup.Show(this);
        if (id == R.id.GroundFightMenu_NextRound) {
            GroundFightScene.NextRound();
            GroundFightScene.MainAdapter.notifyDataSetChanged();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        GroundFightListFragment listFragment;
        GroundFightPlanFragment planFragment;

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return "Combat (Liste)";
            if (position == 1)
                return "Combat (Plan)";
            return "";
        }

        @Override
        public Fragment getItem(int i) {

            if (i == 0) {
                if (listFragment == null)
                    listFragment = new GroundFightListFragment();
                return listFragment;
            }
            if (i == 1) {
                if (planFragment == null)
                    planFragment = new GroundFightPlanFragment();
                return planFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }


}
