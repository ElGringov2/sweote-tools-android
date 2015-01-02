package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.GroundFightActivities.GroundFightAttackResolver;
import com.dragonrider.SWEotE.GroundFightActivities.GroundFightForceResolver;
import com.dragonrider.SWEotE.GroundFightActivities.GroundFighterAdapter;

public class GroundFightScene {
	
	public static GroundFighterAdapter MainAdapter;


	
	public static List<GroundFighter> Fighters = new ArrayList<GroundFighter>();
	
	public static boolean isFoesSurprised = false;
	
	public static List<String> PlayerNames = new ArrayList<String>();
	
	public static List<GroundFighter> getFighters() {
		    return MainAdapter.getFighters();
	}

    public static boolean getIsFighting() {
        if (MainAdapter == null || MainAdapter.getFighters() == null || MainAdapter.getFighters().size() == 0) return false;
        return true;
    }
	
	public static void RefreshContext(Context context) {
		MainAdapter = new GroundFighterAdapter(context);
		MainAdapter.setFighters(Fighters);
		
	}
	
	public static void Clear() {
        PlayerFighterCount = 0;
        PlayerNames = new ArrayList<String>();
        Fighters = new ArrayList<GroundFighter>();


    }


	public static void Initialize(Context context) {
	
        Clear();

        MainAdapter = new GroundFighterAdapter(context);
		MainAdapter.setFighters(Fighters);
		
		

	
	}
	
	static int PlayerFighterCount;
	
	public static void AddPlayerFighter(GroundFighter fighter) {
		Fighters.add(fighter);

		PlayerNames.add(fighter.Name);
		
		PlayerFighterCount++;
		
		ReorderFighters();

		MainAdapter.notifyDataSetChanged();
        FighterListChanged.RunWhenFighterListChanged();
	}

    public interface INotifyFighterListChanged{
        void RunWhenFighterListChanged();
    }
    public static INotifyFighterListChanged FighterListChanged;

    public static void AddFighter(GroundFighter groundFighter) {
        int skillID = Skill.Skills.cool.ordinal();
        if (isFoesSurprised)
            skillID = Skill.Skills.vigilance.ordinal();
        groundFighter.setInitiativeRoll(groundFighter.GetSkillRollResult(skillID));




        int iCount = 1;
        for (GroundFighter anotherFighter : Fighters) {

            if (anotherFighter.Name.equals(groundFighter.Name)) {
                anotherFighter.setColor(iCount);
                iCount++;
            }
        }

        if (iCount != 1)
            groundFighter.setColor(iCount);


        Fighters.add(groundFighter);
        android.util.Log.d("hopla", "Nouveau fighter:" + groundFighter.Name);
    }
	
	private static void AddFighter(NPC baseNPC) {

        GroundFighter fighter = new GroundFighter(PlayerFighterCount);
        fighter.setBase(baseNPC);

        AddFighter(fighter);


	}
	
	
	public static void AddFighter(NPC baseNPC, int Count) {
		
		int skillID = Skill.Skills.cool.ordinal();
		if (isFoesSurprised)
			skillID = Skill.Skills.vigilance.ordinal();
			
	   	 
	   	for (int i = 0; i < Count; i++) {
			GroundFighter fighter = new GroundFighter(PlayerFighterCount);
			fighter.setBase(baseNPC);

            AddFighter(fighter);

	   		 
	   	}
	   	
	   	ReorderFighters();
	   	MainAdapter.notifyDataSetChanged();
        FighterListChanged.RunWhenFighterListChanged();
	}
	
	
	public static void AddFighter(String NPCName, int Count) {
		
	   	 Database db = new Database(App.getContext());
	   	 NPC base = db.GetNPCbyName(NPCName);
	   	 
   		 AddFighter(base, Count);
       
	}
	
	
	public static void ReorderFighters() {
		
		Collections.sort(Fighters, new Comparator<GroundFighter>() {
	        @Override
	        public int compare(GroundFighter s1, GroundFighter s2) {
	        	int i1 = s1.getMainInitiative() * 1000 + s1.getInitiative() * 100 + s1.getSubinitiative() * 10 + (s1.isPlayer ? 1 : 0);
	        	int i2 = s2.getMainInitiative() * 1000 + s2.getInitiative() * 100 + s2.getSubinitiative() * 10 + (s2.isPlayer ? 1 : 0);
	        	if (i1 > i2) return -1;
	        	if (i1 < i2) return 1;
	        	return 0;
	        }
	        
	    });
		
		
	}


	public static void setPlayed(int position, String Action, String Maneuver) {
		
		GroundFighter fighter = Fighters.get(position);

		fighter.setDefensive(false);
		
		if (!fighter.isPlayer) {
			applyManeuvers(position, Maneuver);
			applyActions(position, Action);
		}
		
		fighter.Played = true;
		
		
		
		
		MainAdapter.notifyDataSetChanged();
        FighterListChanged.RunWhenFighterListChanged();
	}
	
	private static void applyManeuvers(int Position, String maneuver) {
		GroundFighter fighter = Fighters.get(Position);
		
		if (maneuver.startsWith(App.getContext().getString(R.string.action_equipweapon))) {
			String wpName = maneuver.replace(App.getContext().getString(R.string.action_equipweapon) + " ", "");
			int iCount = 0;
			for (Item itm : fighter.getBase().Items) {
				if (itm.Name.equals(wpName)) {
					fighter.setEquippedWeapon(iCount);
					break;
				}
				iCount++;
			}
			
		}
		else if (maneuver.equals(App.getContext().getString(R.string.action_aim_plusoneboost))) {
			fighter.increaseAimingBonus();
		}
		else if (maneuver.equals(App.getContext().getString(R.string.action_cover_desc))) {
			fighter.setCover(!fighter.isCover);
		}
		else if (maneuver.equals(App.getContext().getString(R.string.action_drop_prone))) {
			fighter.setProne(!fighter.isProne);
		}
		else if (maneuver.equals(App.getContext().getString(R.string.action_defensivestance_desc))) {
			fighter.setDefensive(!fighter.isDefensive);
		}
		
		fighter.setLastManeuver(maneuver);
		
	}
	
	private static void applyActions(int Position, String action) {
		GroundFighter fighter = Fighters.get(Position);

        Context context = MainAdapter.getActualContext();
		
		if (action.startsWith(context.getString(R.string.action_attack_on))) {
			String targetName = action.replace(context.getString(R.string.action_attack_on), "").trim();

            if (PlayerNames.contains(targetName))
			    GroundFightAttackResolver.Resolve(context, Position, targetName);
            else
                for (GroundFighter f : Fighters)
                    if (f.getFullName().equals(targetName))
                        GroundFightAttackResolver.ResolveVersusNPC(context, Position, Fighters.indexOf(f));



			
		}
		if (action.equals(MainAdapter.getActualContext().getString(R.string.action_force_power)))
		{
			GroundFightForceResolver.Resolve(MainAdapter.getActualContext(), Position);
			
		}
		
		fighter.setLastAction(action);
		
	}

	public static void NextRound() {
		for (GroundFighter fighter : Fighters) {
//			android.util.Log.d("hopla", String.format("(%s) (%s)", fighter.LastAction, fighter.selectedAction));
//			fighter.LastAction = fighter.selectedAction;
//			android.util.Log.d("hopla", String.format("(%s) (%s)", fighter.LastAction, fighter.selectedAction));
//			fighter.selectedAction = App.getContext().getString(R.string.action_none);
//			
//			fighter.LastManeuver = fighter.selectedManeuver;
//			fighter.selectedManeuver = App.getContext().getString(R.string.action_none);
			
			fighter.Played = false;
		}
		
	}
    public static void AddFighterRange(List<NPC> EncounterFighters) {
        AddFighterRange(EncounterFighters, false);
    }
	public static void AddFighterRange(List<NPC> EncounterFighters, boolean Silently) {
		for (NPC npc : EncounterFighters) {
			android.util.Log.i("hopla", "Ajout de " + npc.Name);
	   		
			AddFighter(npc);
		}
		
		ReorderFighters();

        if (!Silently) {
            MainAdapter.notifyDataSetChanged();
            FighterListChanged.RunWhenFighterListChanged();
        }
	}
	

}
