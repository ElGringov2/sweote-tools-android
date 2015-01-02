package com.dragonrider.SWEotE.GroundFightActivities;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.Classes.SimpleEncounterFighter;
import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.App;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.Classes.SWGroupListBoxItemAdapter;
import com.dragonrider.SWEotE.Classes.SWListBoxItem;

public class AddFightersActivity extends Activity {

	
	public static void Show(Context context, boolean CloseOnExit) {
		Intent intent = new Intent(context, AddFightersActivity.class);
		intent.putExtra("CLOSEONEXIT", CloseOnExit);
		context.startActivity(intent);
	}

    SWGroupListBoxItemAdapter adapter1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_add_fighters);

		
		
		Database db = new Database(this);


		List<SWListBoxItem> lst1 = db.GetNPCShortList();
		db.close();
		
		adapter1 = new SWGroupListBoxItemAdapter(this, lst1);
		
		((ExpandableListView)findViewById(R.id.AddFighter_MainList)).setAdapter(adapter1);
		((ExpandableListView)findViewById(R.id.AddFighter_MainList)).setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				
				
				final String npcName = ((SWListBoxItem) adapter1.getChild(groupPosition, childPosition)).getName();

				final LinearLayout ll = new LinearLayout(AddFightersActivity.this);
				for (int i = 0; i < 5; i++) {
					ToggleButton toggle = new ToggleButton(AddFightersActivity.this);
					toggle.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							for (int iItm = 0; iItm < ll.getChildCount(); iItm++)	
								((ToggleButton)ll.getChildAt(iItm)).setChecked(false);
							
							((ToggleButton)v).setChecked(true);
							
						}
					});
					toggle.setText(String.format("x%d", i + 1));
					toggle.setTextOn(String.format("x%d", i + 1));
					toggle.setTextOff(String.format("x%d", i + 1));
					if (i == 0) toggle.setChecked(true);
					ll.addView(toggle);
				}
				
			
				if ((Boolean)((SWListBoxItem) adapter1.getChild(groupPosition, childPosition)).getTag())
					AddFighter(npcName, 1);
				else 
				new AlertDialog.Builder(AddFightersActivity.this)
				.setTitle(R.string.dialog_howmuch)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						int count = 0;
						
						
						for (int iItm = 0; iItm < ll.getChildCount(); iItm++)	
							if (((ToggleButton)ll.getChildAt(iItm)).isChecked())
								count = iItm + 1;
						
						
						AddFighter(npcName, count);
						
					}


				})
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}

				})
				.setView(ll)
				.show();
				
				return true;
			}
		});




	
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.npc_viewer_activity_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter1.setFilter(s);
                adapter1.notifyDataSetChanged();
                return true;
            }
        });

        return true;
    }


    private void AddFighter(String npcName, int count) {
		
		npcName = npcName.replace(App.getContext().getString(R.string.minion), "").replace(App.getContext().getString(R.string.nemesis), "").replace(App.getContext().getString(R.string.rival), "");
		npcName = npcName.replace(" []", "");

        if (getIntent().getExtras().getBoolean("EDITOR"))
        {
            Database db = new Database(this);
            NPC npc = db.GetNPCbyName(npcName);
            db.close();

            for (int i = 0; i < count; i++) {
                SimpleEncounterFighter simpleEncounterFighter = new SimpleEncounterFighter();
                simpleEncounterFighter.setBaseNPC(npc);
                GroundFightPrepareActivity.innerFighters.add(simpleEncounterFighter);
            }

            GroundFightPrepareActivity.UpdateFighterLayout();
        }
        else
		    GroundFightScene.AddFighter(npcName, count);
		
		if (getIntent().getExtras().getBoolean("CLOSEONEXIT"))
			finish();
		else 
			Toast.makeText(AddFightersActivity.this, String.format(getString(R.string.name_add_to_fight), npcName), Toast.LENGTH_SHORT).show();
	}





}
