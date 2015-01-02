package com.dragonrider.SWEotE.GroundFightActivities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CheckBox;

import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.Classes.GroundFighter;
import com.dragonrider.SWEotE.Classes.PlayerCharacter;


public class StartGroundFightActivity extends Activity {

	List<PlayerCharacter> lst;
	List<Integer> triumphs;
	List<Integer> success;
	List<Integer> advantages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_start_ground_fight);

		triumphs = new ArrayList<Integer>();
		success = new ArrayList<Integer>();
		advantages = new ArrayList<Integer>();
		
		
		Database db = new Database(this);
		lst = db.getAllPC();

		for (int i = 0; i < lst.size(); i++) {
			
			triumphs.add(0);
			success.add(0);
			advantages.add(0);
		}

		BaseAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				convertView = LayoutInflater.from(StartGroundFightActivity.this).inflate(R.layout.listitem_startgroundfightitem, null);
				
				final TextView TTri = (TextView)convertView.findViewById(R.id.ListItemStartGroundFight_TriumphCount);
				final TextView TSuc = (TextView)convertView.findViewById(R.id.ListItemStartGroundFight_SuccessCount);
				final TextView TAdv = (TextView)convertView.findViewById(R.id.ListItemStartGroundFight_AdvantageCount);
				
				TTri.setText(String.format("%d",triumphs.get(position) ));
				TSuc.setText(String.format("%d",success.get(position) ));
				TAdv.setText(String.format("%d",advantages.get(position) ));
				
				((TextView)convertView.findViewById(R.id.ListItemStartGroundFight_CharacterName)).setText(lst.get(position).CharacterName);
				
				((CheckBox)convertView.findViewById(R.id.ListItemStartGroundFight_IsPresent)).setChecked(lst.get(position).Present);
				((CheckBox)convertView.findViewById(R.id.ListItemStartGroundFight_IsPresent)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						lst.get(position).Present = isChecked;
					}
				});
				
				
				((Button)convertView.findViewById(R.id.ListItemStartGroundFight_TriumphPlus)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) { triumphs.set(position, triumphs.get(position) + 1) ; TTri.setText(String.format("%d",triumphs.get(position) )); }
				});
				((Button)convertView.findViewById(R.id.ListItemStartGroundFight_TriumphMinus)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) { triumphs.set(position, triumphs.get(position) - 1) ; TTri.setText(String.format("%d",triumphs.get(position) ));}
				});
				
				((Button)convertView.findViewById(R.id.ListItemStartGroundFight_SuccessPlus)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) { success.set(position, success.get(position) + 1) ; TSuc.setText(String.format("%d",success.get(position) ));}
				});				
				((Button)convertView.findViewById(R.id.ListItemStartGroundFight_SuccessMinus)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) { success.set(position, success.get(position) - 1) ; TSuc.setText(String.format("%d",success.get(position) ));}
				});
				
				((Button)convertView.findViewById(R.id.ListItemStartGroundFight_AdvantagePlus)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) { advantages.set(position, advantages.get(position) + 1) ; TAdv.setText(String.format("%d",advantages.get(position) ));}
				});				
				((Button)convertView.findViewById(R.id.ListItemStartGroundFight_AdvantageMinus)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) { advantages.set(position, advantages.get(position) - 1) ; TAdv.setText(String.format("%d",advantages.get(position) ));}
				});
				
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return lst.get(position);
			}
			
			@Override
			public int getCount() {
				return lst.size();
			}
		};

		((ListView)findViewById(R.id.StartFight_MainListView)).setAdapter(adapter);
		

	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();

        int iCount = 0;


		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).Present) {
                iCount++;
				GroundFighter fighter = new GroundFighter(0);
				fighter.setBase(null);
				fighter.Name = lst.get(i).CharacterName;
				fighter.setMainInitiative(triumphs.get(i));
				fighter.setInitiative(success.get(i));
				fighter.setSubinitiative(advantages.get(i));
				
				GroundFightScene.AddPlayerFighter(fighter);
			}
		}



        for (GroundFighter fighter : GroundFightScene.getFighters()) {
            if (fighter.getBase() != null && fighter.getBase().Type == NPC.NPCTypes.Minion)
                fighter.setPlayerCount(iCount);
        }


		
	}


}
