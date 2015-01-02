package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import android.content.Context;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Ship.MountedWeapon;


public class ShipFighter {
	public boolean IsActive;
	public String Name;
	public String id;

	public int TotalHull;
	public int TotalStrain;

	public int ActualHull;
	public int ActualStrain;

	public int ActualSpeed;

	private Ship base;
	private List<String> FiredWeapons = new ArrayList<String>();
	
	

	public List<SpaceFighter> Pilots = new ArrayList<SpaceFighter>();
	public List<SpaceFighter> Copilots = new ArrayList<SpaceFighter>();
	public List<SpaceFighter> Crew = new ArrayList<SpaceFighter>();
	public boolean Played = false;
	public boolean isPlayer = false;
	public String AdvantageOver = App.getContext().getString(
			com.dragonrider.SWEotE.R.string.nobody);
	public int AdvantageOverLevel = 0;
	
	public int CurrentAftDefense;
	public int CurrentForwardDefense;
	public int CurrentPortDefense;
	public int CurrentStarboardDefense;
	public boolean isEvading;
	public boolean isStayingOnTarget;

	public Ship getBase() {

		
		return base;
				
	}

	public void setBase(Ship base) {
		
		
		this.base = base;
		
		
		this.CurrentAftDefense = base.AftDefense;
		this.CurrentStarboardDefense = base.StarboardDefense;
		this.CurrentPortDefense = base.PortDefense;
		this.CurrentForwardDefense = base.ForwardDefense;

		this.TotalHull = base.TotalHull;
		this.TotalStrain = base.TotalStrain;

	}

	public List<SWListBoxItem> getPossibleManeuvers(Context context,
			SpaceFighter fighter) {
		List<SWListBoxItem> kvpReturn = new ArrayList<SWListBoxItem>();

		kvpReturn.add(new SWListBoxItem(context.getString(R.string.none2),
				"Aucune manoeuvre ce tour."));
		kvpReturn.add(new SWListBoxItem("Move",
				"Déplacement à l'intérieur du vaisseau"));

		if (getBase().Silhouette > 4) {
			if (CurrentAftDefense > 0) {
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Aft (%s) to Forward (%s)",
						CurrentAftDefense, CurrentForwardDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Aft (%s) to Port (%s)",
						CurrentAftDefense, CurrentPortDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Aft (%s) to Starboard (%s)",
						CurrentAftDefense, CurrentStarboardDefense),
						"Reassign defense zones"));
			}
			if (CurrentForwardDefense > 0) {
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Forward (%s) to Aft (%s)",
						CurrentForwardDefense, CurrentAftDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Forward (%s) to Port (%s)",
						CurrentForwardDefense, CurrentPortDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Forward (%s) to Starboard (%s)",
						CurrentForwardDefense, CurrentStarboardDefense),
						"Reassign defense zones"));
			}
			if (CurrentPortDefense > 0) {
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Port (%s) to Aft (%s)",
						CurrentPortDefense, CurrentAftDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Port (%s) to Forward (%s)",
						CurrentPortDefense, CurrentForwardDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Port (%s) to Starboard (%s)",
						CurrentPortDefense, CurrentStarboardDefense),
						"Reassign defense zones"));
			}
			if (CurrentStarboardDefense > 0) {
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Starboard (%s) to Aft (%s)",
						CurrentStarboardDefense, CurrentAftDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Starboard (%s) to Forward (%s)",
						CurrentStarboardDefense, CurrentForwardDefense),
						"Reassign defense zones"));
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Starboard (%s) to Port (%s)",
						CurrentStarboardDefense, CurrentPortDefense),
						"Reassign defense zones"));
			}
		} else {
			if (CurrentForwardDefense > 0)
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Forward (%s) to Aft (%s)",
						CurrentForwardDefense, CurrentAftDefense),
						"Reassign defense zones"));
			if (CurrentAftDefense > 0)
				kvpReturn.add(new SWListBoxItem(String.format(
						"Angle Shield Aft (%s) to Forward (%s)",
						CurrentAftDefense, CurrentForwardDefense),
						"Reassign defense zones"));
		}

		if (Pilots.contains(fighter) || Copilots.contains(fighter)) {
			if (ActualSpeed < getBase().Speed)
				kvpReturn.add(new SWListBoxItem("Accelerate",
						"Increase speed by one"));
			if (ActualSpeed > 0)
				kvpReturn.add(new SWListBoxItem("Decelerate",
						"Decrease speed by one"));
			if (ActualSpeed != 0)
				kvpReturn.add(new SWListBoxItem("Fly/Drive",
						"Move or change range band"));
			if (getBase().Silhouette < 5 && ActualSpeed >= 3
					&& isEvading == false)
				kvpReturn.add(new SWListBoxItem("Evasive Maneuvers",
						"Upgrade next difficulty pool"));
			if (getBase().Silhouette < 5 && ActualSpeed >= 3
					&& isStayingOnTarget == false)
				kvpReturn.add(new SWListBoxItem("Stay on Target",
						"Upgrade next combat check"));
			if (getBase().Silhouette < 5 && ActualSpeed < getBase().Speed)
				kvpReturn.add(new SWListBoxItem("Punch It",
						"Accelerate to full throttle"));
		}

		return kvpReturn;
	}

	public List<SWListBoxItem> GetPossibleActions(Context context,
			SpaceFighter npc) {
		List<SWListBoxItem> lReturn = new ArrayList<SWListBoxItem>();

		lReturn.add(new SWListBoxItem(context.getString(R.string.none2),
				"Aucune actions ce tour."));

		lReturn.add(new SWListBoxItem("Damage Control",
				"Reduce System Strain by one"));
		if (Pilots.contains(npc) || Copilots.contains(npc))
			if (this.ActualSpeed >= 4 && getBase().Silhouette < 5) {
				for (Entry<String, ShipFighter> itm : SpaceFightScene.ITEM_MAP.entrySet()) {
				    ShipFighter ship = itm.getValue();
					if (ship.isPlayer) {
						int difficulty = 0;
						if (ship.AdvantageOver == this.Name)
							difficulty = ship.AdvantageOverLevel + 1;
						lReturn.add(new SWListBoxItem(String.format(
								"Gain the Advantage over %s (%d)", ship.getBase().Name,
								difficulty),
								"Ignore penalties from Evasive Maneuvers"));
					}
				}

			}

		lReturn.add(new SWListBoxItem("Plot Course",
				"Reduce terrain difficulty by one Setback Dice"));
		lReturn.add(new SWListBoxItem("Copilot",
				"Downgrade the next Pilot check difficulty"));
		lReturn.add(new SWListBoxItem("Jamming", "Jam ennemy comms"));
		lReturn.add(new SWListBoxItem("Boost Shields", "Increase Defense zone"));
		lReturn.add(new SWListBoxItem("Manual Repairs",
				"Damage Control, with Athletics"));
		lReturn.add(new SWListBoxItem("Fire Discipline",
				"Analyze opponent's tactics to gain Boost Dice"));
		lReturn.add(new SWListBoxItem("Scan the Ennemy",
				"Learn stats from enemy"));
		lReturn.add(new SWListBoxItem("Slice Enemy's System",
				"Reduce Defense, disable weapons"));
		lReturn.add(new SWListBoxItem("Spoofing missiles",
				"upgrade difficulty of guided weapons"));

		for (Entry<String, ShipFighter> itm : SpaceFightScene.ITEM_MAP.entrySet()) {
		    ShipFighter ship = itm.getValue();
			if (ship.isPlayer) {
				for (MountedWeapon weapon : this.getBase().Weapons) {
					if (!FiredWeapons.contains(weapon.Name))
						lReturn.add(new SWListBoxItem(String.format(
								"Attack over %s with %s", ship.getBase().Name,
								weapon.Name), "Attack using gunnery"));

				}
			}
		}
		
		
		
		

		return lReturn;
	}

	
	public static ShipFighter getShipFighter (ShipFighterTemplate template) {
		ShipFighter fighter = new ShipFighter();
		fighter.setBase(template.getBaseShip());
		for (NPC npc : template.getPilots()) {
			SpaceFighter sFighter = new SpaceFighter();
			sFighter.setBase(npc);
			sFighter.Name = npc.Name;
			fighter.Pilots.add(sFighter);
		}
		for (NPC npc : template.getCopilots()) {
			SpaceFighter sFighter = new SpaceFighter();
			sFighter.setBase(npc);
			sFighter.Name = npc.Name;
			fighter.Copilots.add(sFighter);
		}
		for (NPC npc : template.getCrew()) {
			SpaceFighter sFighter = new SpaceFighter();
			sFighter.setBase(npc);
			sFighter.Name = npc.Name;
			fighter.Crew.add(sFighter);
		}
		return fighter;
		

		
	}
	
	
}
