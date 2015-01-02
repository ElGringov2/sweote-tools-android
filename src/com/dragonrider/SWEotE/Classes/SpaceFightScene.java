package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonrider.SWEotE.R;





public class SpaceFightScene {

	
	//public static List<ShipFighter> ITEMS = new ArrayList<ShipFighter>();
	
	public static Map<String, ShipFighter> ITEM_MAP = new HashMap<String, ShipFighter>();
	
	public static List<SpaceFighter> FIGHTERS = new ArrayList<SpaceFighter>();
	
	public static Map<String, SpaceFighter> FIGHTER_MAP = new HashMap<String, SpaceFighter>();

	public static ArrayList<String> PlayerNames;


	
	
	public static void AddShip(ShipFighter s) {
		s.id = String.valueOf(ITEM_MAP.size());
		//ITEMS.add(s);
		ITEM_MAP.put(s.id, s);


		for (SpaceFighter fighter : s.Pilots) {
			fighter.ShipID = s.id;
			fighter.id = String.valueOf(FIGHTERS.size());
			if (fighter.isPlayer == false)
				fighter.setInitiativeRoll(fighter.GetSkillRollResult(Skill.Skills.cool.ordinal()));
			FIGHTERS.add(fighter);
			FIGHTER_MAP.put(fighter.id, fighter);
		}
		
		for (SpaceFighter fighter : s.Copilots) {
			fighter.ShipID = s.id;
			fighter.id = String.valueOf(FIGHTERS.size());
			if (fighter.isPlayer == false)
				fighter.setInitiativeRoll(fighter.GetSkillRollResult(Skill.Skills.cool.ordinal()));
			FIGHTERS.add(fighter);
			FIGHTER_MAP.put(fighter.id, fighter);
		}
		
		for (SpaceFighter fighter : s.Crew) {
			fighter.ShipID = s.id;
			fighter.id = String.valueOf(FIGHTERS.size());
			if (fighter.isPlayer == false)
				fighter.setInitiativeRoll(fighter.GetSkillRollResult(Skill.Skills.cool.ordinal()));
			FIGHTERS.add(fighter);
			FIGHTER_MAP.put(fighter.id, fighter);
		}
		
		Sort();
		
		

		
	}



	public static void Sort() {
		Collections.sort(FIGHTERS, new SpaceFighterComparer());
		
		
		for (SpaceFighter f : FIGHTERS) {
			f.hasPlayed = false;
			f.IsActive = false;
		}
		
		FIGHTERS.get(0).IsActive = true;
	}
	
	private static class SpaceFighterComparer implements Comparator<SpaceFighter> {

		@Override
		public int compare(SpaceFighter arg0, SpaceFighter arg1) {
			int iCompare = ((Integer)arg1.getInitiative()).compareTo(arg0.getInitiative());
			if (iCompare == 0)
				iCompare = ((Integer)arg1.getSubinitiative()).compareTo(arg0.getSubinitiative());
			if (iCompare == 0)
				iCompare = ((Boolean)arg1.isPlayer).compareTo(arg1.isPlayer);
			return iCompare;
		}
		
	}
	
	public static void initialize(Context context) {
		Database db = new Database(App.getContext());
		PlayerNames = new ArrayList<String>();
		int i = 0;
		for (PlayerCharacter pl : db.getAllPC()) {
			if (pl.Present) {
				SpaceFighter fighter = new SpaceFighter();
				fighter.isPlayer = true;
				fighter.Name = pl.CharacterName;
				fighter.id = String.format("playerCharacter%d", i);
				i++;
				PlayerNames.add(pl.CharacterName);
			}
		}
		getPCShips(context);
	}
	

//	
//	public static void initializeFromScenario(Context context, SpaceFightEntry entry) {
//		clear();
//		
//		Database db = new Database(context);
//		for (String npcName : entry.ShipTemplateNames) {
//			String[] split = npcName.split("[|]");
//			ShipFighterTemplate template = db.GetShipFighterTemplate(split[0]);
//			ShipFighter fighter = ShipFighter.getShipFighter(template);
//			fighter.Name = split[1];
//			AddShip(fighter);
//		}
//		//initialize(context);
//	}
//	
	
	
	private static int _xDelta;
	private static int _yDelta;
	static RelativeLayout.LayoutParams lParams;
	
	private static void getPCShips (final Context context) {
		final RelativeLayout rootView = new RelativeLayout(context);
		

		
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 75);
		params2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		TextView ship = new TextView(context);
		ship.setId(10);
		ship.setBackgroundResource(R.drawable.border_violet);
		ship.setText("Vehicule 1");
		rootView.addView(ship, params2);
		
		params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 75);
		params2.addRule(RelativeLayout.BELOW, 10);
		ship = new TextView(context);
		ship.setId(11);
		ship.setBackgroundResource(R.drawable.border_violet);
		ship.setText("Vehicule 2");
		rootView.addView(ship, params2);
		
		params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 75);
		params2.addRule(RelativeLayout.BELOW, 11);
		ship = new TextView(context);
		ship.setId(12);
		ship.setBackgroundResource(R.drawable.border_violet);
		ship.setText("Vehicule 3");
		rootView.addView(ship, params2);
		
		params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 75);
		params2.addRule(RelativeLayout.BELOW, 12);
		ship = new TextView(context);
		ship.setId(13);
		ship.setBackgroundResource(R.drawable.border_violet);
		ship.setText("Vehicule 4");
		rootView.addView(ship, params2);
		
		int id = -1;
		for (String name : PlayerNames) {
			id++;
			TextView txt = new TextView(context);
			txt.setText(name);
			txt.setId(id);
			txt.setBackgroundColor(Color.CYAN);

			txt.setTag(0);
			

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

			params.topMargin = 35;
			
		
		
			txt.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View view, MotionEvent event) {
					final int X = (int) event.getRawX();
				    final int Y = (int) event.getRawY();
				    switch (event.getAction() & MotionEvent.ACTION_MASK) {
				        case MotionEvent.ACTION_DOWN:
				            lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
				            
				            _xDelta = X - lParams.leftMargin;
				            _yDelta = Y - lParams.topMargin;
				            break;
				        case MotionEvent.ACTION_UP:
				        	view.setTag((lParams.topMargin / 75));


				        	lParams.topMargin = (lParams.topMargin / 75) * 75;
				        	view.setLayoutParams(lParams);
				        	
				            break;
				        case MotionEvent.ACTION_POINTER_DOWN:
				            break;
				        case MotionEvent.ACTION_POINTER_UP:
				            break;
				        case MotionEvent.ACTION_MOVE:
				        	lParams.leftMargin = X - _xDelta;
				        	lParams.topMargin = Y - _yDelta;
				        	lParams.rightMargin = -250;
				        	lParams.bottomMargin = -250;
				            view.setLayoutParams(lParams);
				            break;
				    }
				    rootView.invalidate();
				    return true;


				}
			});
			
			rootView.addView(txt, params);
		
		}
		
		
		
		
		
		
		
		new AlertDialog.Builder(context)
			.setTitle(R.string.dialog_playership)
			.setView(rootView)
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			})
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ShipFighter[] ships = new ShipFighter[4];
					
					
					for (int i = 0; i < rootView.getChildCount(); i++)
					{
						if (rootView.getChildAt(i).getClass() == TextView.class)
							if (((TextView)rootView.getChildAt(i)).getTag() != null) {
								int iShipID = (Integer)((TextView)rootView.getChildAt(i)).getTag();
								if (ships[iShipID] == null) 
									ships[iShipID] = new ShipFighter();
								SpaceFighter f = new SpaceFighter();
								f.Name = ((TextView)rootView.getChildAt(i)).getText().toString();
								f.isPlayer = true;
								ships[iShipID].Crew.add(f);
								
							}
						
						
					}
					int iPlayerShip = -1;
					List<ShipFighter> FinalList = new ArrayList<ShipFighter>();
					for (ShipFighter shipFighter : ships) {
						if (shipFighter == null) continue;
						iPlayerShip++;
						Ship ship = new Ship();
						ship.Name = "ENTER_NEW_NAME";
						shipFighter.id = "playership" + String.valueOf(iPlayerShip);
						shipFighter.setBase(ship);
						shipFighter.Name = "-";
						shipFighter.isPlayer = true;
						FinalList.add(shipFighter);

						
					}
					//updatePlayerShipName(FinalList, context);

				}
			})
			.show();
			
	}
//	private static void updatePlayerShipName(final List<ShipFighter> lst, final Context context) {
//		
//		
//		
//		
//		for (final ShipFighter shipFighter : lst) {
//			if (shipFighter.getBase().Name == "ENTER_NEW_NAME") {
//				
//				final EditText text= new EditText(context);
//				
//				String sTitle = context.getString(R.string.newPlayerShip) + " " + String.valueOf(lst.indexOf(shipFighter));
//				new AlertDialog.Builder(context)
//				.setTitle(sTitle)
//				.setView(text)
//				.setCancelable(false)
//				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						shipFighter.getBase().Name = text.getText().toString();
//						updatePlayerShipName(lst, context);
//
//					}
//				})
//				.show();
//				
//				
//				return;
//			}
//		}
//		for (ShipFighter shipFighter : lst) 
//			AddShip(shipFighter);
//		
//		((BaseAdapter) ((SpaceFighterListFragment) ((FragmentActivity) context).getSupportFragmentManager()
//				.findFragmentById(R.id.spacefighter_list))
//				.getListAdapter()).notifyDataSetChanged();
//	}
	
	
	public static void clear() {
		//ITEMS.clear();
		ITEM_MAP.clear();
		FIGHTER_MAP.clear();
		FIGHTERS.clear();
		
	}

	static int iActivePlayer = 0;

	private static Boolean surprise;
	
	public static void NextPlayer() {
		FIGHTERS.get(iActivePlayer).IsActive = false;
		iActivePlayer++;
		if (iActivePlayer == FIGHTERS.size()) {
			iActivePlayer = 0;
			for (SpaceFighter fighter : FIGHTERS) {
				fighter.Played = false;
				
			}
			Toast.makeText(App.getContext(), App.getContext().getString(R.string.newturn), Toast.LENGTH_SHORT).show();
			
		}
		FIGHTERS.get(iActivePlayer).IsActive = true;
		
	}
	public static Boolean getSurprise() {
		return surprise;
	}
	public static void setSurprise(Boolean Surprise) {
		surprise = Surprise;
	}

//	private HashMap<String, String> GetTheAdvantage = new HashMap<String, String>();
//	
//	
//	public void getAdvantage(ShipFighter Attacker, ShipFighter Target) {
//		GetTheAdvantage.put(Attacker.Name, Target.Name);		
//	}
//	
//	public int GetAdvantageDifficulty(ShipFighter Attacker, ShipFighter Target) {
//		int iReturn = 0;
//	
//		Boolean bContinue = true;
//		while (bContinue) {
//			if (GetTheAdvantage.containsKey(Attacker.Name)) {
//				if (GetTheAdvantage.get(Attacker.Name) == Target.Name)
//				
//			}
//			if (GetTheAdvantage.containsValue(Target.Name))
//			{
//				
//			}
//			
//			
//		}
//		
//		
//		return iReturn;
//	}
	
}
