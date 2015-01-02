package com.dragonrider.SWEotE.PlayerActivities;

import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.App;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.PlayerCharacter;

public class EditPlayerAdapter extends BaseAdapter {
	
	List<PlayerCharacter> players;
	Context context;
	
	public EditPlayerAdapter(Context context) {
		this.context = context;
		
		Database db = new Database(context);
		
		this.players = db.getAllPC();
		
		
	}
	
	public void AddPC() {
		players.add(new PlayerCharacter());
		
		notifyDataSetChanged();
		
		
	}
	
	public void SaveToDatabase() {
		
		Database db = new Database(context);
		db.EmptyPC();
		for (PlayerCharacter pc : players) {
			db.AddPC(pc);
		}
		
		
	}

	@Override
	public int getCount() {
		return players.size();
	}

	@Override
	public Object getItem(int position) {
		return players.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		final PlayerCharacter pl = (PlayerCharacter) getItem(position);

		
		convertView = LayoutInflater.from(App.getContext()).inflate(R.layout.listitem_editplayer, null);

		((EditText)convertView.findViewById(R.id.EditPlayerlistItemPlayerName)).setText(pl.PlayerName);
		((EditText)convertView.findViewById(R.id.EditPlayerlistItemPlayerName)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				pl.PlayerName = s.toString();
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});

		((EditText)convertView.findViewById(R.id.EditPlayerlistItemCharacterName)).setText(pl.CharacterName);
		((EditText)convertView.findViewById(R.id.EditPlayerlistItemCharacterName)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				pl.CharacterName = s.toString();
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		
		
		return convertView;
	}

}
