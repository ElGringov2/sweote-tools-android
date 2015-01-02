package com.dragonrider.SWEotE.Sabacc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.ActivitySelectNPC;
import com.dragonrider.SWEotE.Classes.ActivitySelectNPC.ISelectNPC;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.PlayerCharacter;

public class FragmentInitialize extends Fragment {

	public interface IFragmentInitializeCallBack {
		public void StartGame();
	}
	IFragmentInitializeCallBack callback;
	
	public static FragmentInitialize newInstance(IFragmentInitializeCallBack callback)
	{
		FragmentInitialize init = new FragmentInitialize();
		init.callback = callback;
		Sabacc.Players = new ArrayList<SabaccPlayer>();
		
		return init;
		
	}
	
	
	View mainView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mainView = inflater.inflate(R.layout.fragment_sabacc_initialize, null);
		
		
		((EditText)mainView.findViewById(R.id.InitSabaccStartingSabaccPot)).setText(String.valueOf(Sabacc.SabaccPot));
		((EditText)mainView.findViewById(R.id.InitSabaccHandBet)).setText(String.valueOf(Sabacc.StartingBet));
		((EditText)mainView.findViewById(R.id.InitSabaccMaxBet)).setText(String.valueOf(Sabacc.MaxBet));
		
		


		Database db = new Database(getActivity());
		List<PlayerCharacter> pcs = db.getAllPC();
		db.close();
		
		Sabacc.Players = new ArrayList<SabaccPlayer>();
		for (PlayerCharacter playerCharacter : pcs) {
			
			final SabaccPlayer pl = SabaccPlayer.fromPC(playerCharacter.CharacterName);
			

			if (pl.getName().isEmpty()) {
				pl.setPC(true);
				pl.setName(playerCharacter.CharacterName);
				pl.setDescription(playerCharacter.PlayerName);
			}
			View view = inflater.inflate(R.layout.item_initsabacc_player, null);
			((TextView)view.findViewById(R.id.itemSabaccInitPlayerName)).setText(playerCharacter.CharacterName);
			for (int i = 0; i < ((LinearLayout)view).getChildCount(); i++) {
				if (((LinearLayout)view).getChildAt(i).getClass() == EditText.class) {
					setEditTextValue(pl, (EditText)((LinearLayout)view).getChildAt(i));
					((EditText)((LinearLayout)view).getChildAt(i)).addTextChangedListener(new MyTextWatcher(((LinearLayout)view).getChildAt(i).getId(), pl));
				}
			}
			((Button)view.findViewById(R.id.itemSabaccInitPlayerDelete)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Sabacc.Players.remove(pl);
					RefreshPlayers();
				}
			});
			Sabacc.Players.add(pl);
			((LinearLayout)mainView.findViewById(R.id.InitSabaccMainLayout)).addView(view);
		}

		
		((Button)mainView.findViewById(R.id.InitSabaccAddPlayer)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addPlayer();
			}
		});
		
		
		((Button)mainView.findViewById(R.id.InitSabaccStart)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				callback.StartGame();
			}
		});
		
		
		
		return mainView;
	}
	
	private class MyTextWatcher implements TextWatcher {

		private int viewID;
		private SabaccPlayer player;
		
		public MyTextWatcher(int viewID, SabaccPlayer player) {
			this.viewID = viewID;
			this.player = player;
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
	

			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
			int i;
			try {
				i = Integer.parseInt(s.toString());
			}
			catch (NumberFormatException e) {
				return;
			}
			if (viewID == R.id.itemSabaccInitPlayerComputers)
				player.setComputers(i);
			if (viewID == R.id.itemSabaccInitPlayerCool)
				player.setCool(i);
			if (viewID == R.id.itemSabaccInitPlayerCunning)
				player.setCunning(i);
			if (viewID == R.id.itemSabaccInitPlayerDeceit)
				player.setDeceit(i);
			if (viewID == R.id.itemSabaccInitPlayerIntellect)
				player.setIntellect(i);
			if (viewID == R.id.itemSabaccInitPlayerPresence)
				player.setPresence(i);
			if (viewID == R.id.itemSabaccInitPlayerSkulduggery)
				player.setSkulduggery(i);
			if (viewID == R.id.itemSabaccInitPlayerCredits)
				player.setCredits(i);		
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			
			
		}
		
	}
	
	private void setEditTextValue(SabaccPlayer player, EditText et) {
		int viewID = et.getId();
		if (viewID == R.id.itemSabaccInitPlayerComputers)
			et.setText(String.valueOf(player.getComputers()));
		if (viewID == R.id.itemSabaccInitPlayerCool)
			et.setText(String.valueOf(player.getCool()));
		if (viewID == R.id.itemSabaccInitPlayerCunning)
			et.setText(String.valueOf(player.getCunning()));
		if (viewID == R.id.itemSabaccInitPlayerDeceit)
			et.setText(String.valueOf(player.getDeceit()));
		if (viewID == R.id.itemSabaccInitPlayerIntellect)
			et.setText(String.valueOf(player.getIntellect()));
		if (viewID == R.id.itemSabaccInitPlayerPresence)
			et.setText(String.valueOf(player.getPresence()));
		if (viewID == R.id.itemSabaccInitPlayerSkulduggery)
			et.setText(String.valueOf(player.getSkulduggery()));
		if (viewID == R.id.itemSabaccInitPlayerCredits)
			et.setText(String.valueOf(player.getCredits()));
	}
	
	
	private void addPlayer() {
		AlertDialog.Builder builder= new Builder(getActivity());
		
		builder.setTitle("Ajouter un joueur");
		
		
		
		builder.setAdapter(PlayerAdapter , new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == 11) {
					ActivitySelectNPC.Show(getActivity(), true, new ISelectNPC() {

						@Override
						public void OnNPCSelected(String SelectedNPC, int Count) {
							for (int i = 0; i < Count; i++)
								Sabacc.Players.add(SabaccPlayer.fromNPC(SelectedNPC));
							Toast.makeText(getActivity(), SelectedNPC + " ajouté", Toast.LENGTH_SHORT).show();
							RefreshPlayers();
						}
						
					});
				}
				else {
					SabaccPlayer pl = getNPC(which);
					Sabacc.Players.add(pl);
					Toast.makeText(getActivity(), pl.getName() + " ajouté", Toast.LENGTH_SHORT).show();
					RefreshPlayers();
				}
				
				RefreshPlayers();
				
			}
		});
		
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				dialog.cancel();
				
			}
		});
		
		builder.show();
		
	}
	
	
	private void RefreshPlayers() {
		LinearLayout ll = (LinearLayout) mainView.findViewById(R.id.InitSabaccMainLayout);
		ll.removeAllViews();
		
		
		for (final SabaccPlayer pl : Sabacc.Players) {
			
		
			View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_initsabacc_player, null);
			((TextView)view.findViewById(R.id.itemSabaccInitPlayerName)).setText(pl.getName());
			for (int i = 0; i < ((LinearLayout)view).getChildCount(); i++) {
				if (((LinearLayout)view).getChildAt(i).getClass() == EditText.class) {
					setEditTextValue(pl, (EditText)((LinearLayout)view).getChildAt(i));
					((EditText)((LinearLayout)view).getChildAt(i)).addTextChangedListener(new MyTextWatcher(((LinearLayout)view).getChildAt(i).getId(), pl));
				}
			}
			((Button)view.findViewById(R.id.itemSabaccInitPlayerDelete)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Sabacc.Players.remove(pl);
					RefreshPlayers();
				}
			});
			((LinearLayout)mainView.findViewById(R.id.InitSabaccMainLayout)).addView(view);

		}
	}
	
	
	private SabaccPlayer getNPC(int Difficulty) {
		SabaccPlayer player = new SabaccPlayer();
		
		
		String[] str = new String[] {"Lyra Sode", 
				"Jol Aruru", 
				"Charlena Rost", 
				"Elgar Killdarn", 
				"Ubinaarisan Katar", 
				"Estefan Quee", 
				"Mikal Dalledos", 
				"Verdian Derricote", 
				"Zechs Aah'valia", 
				"Kida Xen", 
				"Lucia Ker", 
				"Giles Drackon", 
				"Raihane Jade", 
				"Janeth Omega", 
				"Jarvis Groman", 
				"Bystran Ryen", 
				"Zolar Moraal", 
				"Brodie Ktrame", 
				"Marcus Kurn", 
				"Jake Sontebren", 
				"X'lor Novan", 
				"Bobecc Kelrian", 
				"Maath Tagratt", 
				"Jaster Selzen", 
				"Elias Wynnopin", 
				"Clph Rev", 
				"Diona Mallix", 
				"Vara Cruz", 
				"Grooda Carn", 
				"Gale Froud", 
				"Stuart Shenuri", 
				"Yet Clash", 
				"Rizann Novan", 
				"Groznik Foreman", 
				"Minn Anjek", 
				"Ylenia Eclissu", 
				"Fama Kosokhan", 
				"Zark Daemon", 
				"Halron Bahr", 
				"Kahar Starfire", 
				"Kalle Durame", 
				"Kavis Caranthyr", 
				"Hugh Ginn", 
				"Kelen Jorel", 
				"Zentoo Thont", 
				"Darren Jiktar", 
				"Zulia Garallia", 
				"Sab Guga", 
				"Azmo Yeh", 
				"Xerda Agneta", 
				"Mith Ryra", 
				"Gael Calway", 
				"Kazic Nyine", 
				"Isabeau Sontebren", 
				"Julan Evnairis", 
				"Zulara Rueb", 
				"Tumnar Bastra", 
				"Firacomo Duey", 
				"Selan Corr", 
				"Voba Vekarr", 
				"Jope Melkans", 
				"Coryn Quanera", 
				"Comdo Tallav", 
				"Hersh Sesslyn", 
				"Marneg Creel", 
				"Soryn Ericton", 
				"Jorus Holgor", 
				"Tanaris Darkrose", 
				"Desiraye Serv'laya", 
				"Callista Garamonde", 
				"Maleena Quee", 
				"Tay Visputin", 
				"Benson Sepsom", 
				"Olothorin Skyff", 
				"Avix Tisa", 
				"Tisha Silero", 
				"Cypher Leqarna", 
				"Risa Reumar", 
				"Strata Tess", 
				"Indiglo Belami", 
				"Kya Beshenal", 
				"Tokrin Onone", 
				"Plet Flynn", 
				"Timerck Lonarcch", 
				"Vivi Zherooh", 
				"Brev Dlamard", 
				"Gavyn Renz", 
				"Mossi Dontin", 
				"Anya Dulovic", 
				"Deena Starkos", 
				"Mission Sprax", 
				"Jem Drackon", 
				"Risshik Bathens", 
				"Taza McConnell", 
				"Giles Carlstein", 
				"Alona Tymon", 
				"Neala Crow", 
				"Dermot Fallwen", 
				"Aaran Bokete", 
				"Anesa Tokani"
		};
		
		Random r = new Random();
		int i = r.nextInt(str.length);
		player.setName(str[i]);
		player.setPC(false);
		player.setCredits(new Random().nextInt((Difficulty + 1) * 100) + (Difficulty / 2 * 50) );
		
		
		player.setPresence((int) Math.ceil(1 + (Difficulty * Difficulty) / 30));

		player.setCunning((int) Math.ceil(1 + (Difficulty * Difficulty) / 25));
		
		player.setIntellect((int) Math.ceil(1 + (Difficulty * Difficulty) / 35));
		
		
		
		player.setCool((int) Math.ceil(Difficulty  / 2.5F));

		player.setDeceit((int) Math.ceil(Difficulty  / 2));

		player.setComputers((int) Math.floor(Difficulty * Difficulty  / 30));

		player.setCool((int) Math.floor(Difficulty * Difficulty  / 20));

		
		player.setDescription("Joueur de sabacc générique de difficulté " + String.valueOf(Difficulty));
		
		
		
		
		
		return player;
		
		
		
	}
	
	
	private BaseAdapter PlayerAdapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_2, null);
			
			
			if (position == 11) {
				((TextView) convertView.findViewById(android.R.id.text1)).setText("PNJ");
				((TextView) convertView.findViewById(android.R.id.text2)).setText("Selectionnez un PNJ dans la base de donnée");
					
			}
			else {
			
				((TextView) convertView.findViewById(android.R.id.text1)).setText(getNPC(position).getName());
				((TextView) convertView.findViewById(android.R.id.text2)).setText("Joueur de sabacc difficulté " + String.valueOf(position));
			
			}
			
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public Object getItem(int position) {
			return getNPC(position);
		}
		
		@Override
		public int getCount() {
			return 12;
		}
	};

}




