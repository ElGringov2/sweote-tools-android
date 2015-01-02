package com.dragonrider.SWEotE.PlayerActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.dragonrider.SWEotE.Classes.XmlImport;
import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.Classes.PlayerCharacter;

public class EditPlayerActivity extends Activity {

	EditPlayerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_player);


		 adapter = new EditPlayerAdapter(this);
		
		((ListView)findViewById(R.id.EditPlayer_MainList)).setAdapter(adapter);
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_player, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.editplayer_addplayer) {
			adapter.AddPC();
			return true;
		}
		if (id == R.id.editplayer_saveplayer) {
			adapter.SaveToDatabase();
			finish();
		}
		if (id == R.id.editplayer_importplayers) {
			Database db = new Database(this);
			db.EmptyPC();
			for (NPC npc : XmlImport.ImportPCs()) {
				PlayerCharacter pc = new PlayerCharacter();
				pc.CharacterName = npc.Name;
				pc.PlayerName = npc.OwnerName;
                pc.setIsPresent(true);
                pc.setImage(npc.getImage());
				db.AddPC(pc);
				
			}

            adapter = new EditPlayerAdapter(this);

            ((ListView)findViewById(R.id.EditPlayer_MainList)).setAdapter(adapter);
		}
		return super.onOptionsItemSelected(item);
	}



}
