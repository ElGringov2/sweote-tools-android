package com.dragonrider.SWEotE.GroundFightActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.GroundFightScene;


public class GroundFightActivity extends Activity {

	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ground_fight);


	
		if (getIntent().getExtras().getBoolean("NEWFIGHT")) {
			
			GroundFightScene.Initialize(this);
			
			Intent intent = new Intent(this, StartGroundFightActivity.class);
			
			startActivity(intent);
		}
		else
		{
			
			GroundFightScene.RefreshContext(this);

		}

        if (getIntent().getExtras().containsKey("STARTFROMPREPARE"))
        {
            Intent intent = new Intent(this, StartGroundFightActivity.class);

            startActivity(intent);
        }
		

		
		ListView mainView = (ListView) findViewById(R.id.GroundFightActivyty_MainList);
				
		mainView.setAdapter(GroundFightScene.MainAdapter);
		
		
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
	


}
