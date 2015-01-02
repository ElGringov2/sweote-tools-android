package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Characteristic.Characteristics;

public class Skill {

	public Skills SkillID;
	public String getName() {
		return App.getContext().getString(SkillRessources[SkillID.ordinal()]);
	}
	public int Value;
	public int CharacteristicID;
	
	
	public Skill setValue(int Value) {
        this.Value = Value;
        return this;
    }
	
	public Skill(int SkillID, int Value, Characteristics characteristic) {
		this.SkillID = Skills.values()[SkillID];
		this.Value = Value;
		this.CharacteristicID = characteristic.ordinal();
		
		
	}
	

	@Override
	public String toString() {
		return getName() + " (" + Characteristic.getAbr(CharacteristicID) + "): " + String.valueOf(Value);
	}
	
	
	public static Skill getNewSkill(int SkillID) {
		if (SkillID == Skills.astrogation.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.athletics.ordinal()) return new Skill(SkillID, 0, Characteristics.Brawn);
		if (SkillID == Skills.brawl.ordinal()) return new Skill(SkillID, 0, Characteristics.Brawn);
		if (SkillID == Skills.charm.ordinal()) return new Skill(SkillID, 0, Characteristics.Presence);
		if (SkillID == Skills.coercion.ordinal()) return new Skill(SkillID, 0, Characteristics.Willpower);
		if (SkillID == Skills.computers.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.cool.ordinal()) return new Skill(SkillID, 0, Characteristics.Presence);
		if (SkillID == Skills.coordination.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.core_worlds.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.deception.ordinal()) return new Skill(SkillID, 0, Characteristics.Cunning);
		if (SkillID == Skills.discipline.ordinal()) return new Skill(SkillID, 0, Characteristics.Willpower);
		if (SkillID == Skills.education.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.gunnery.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.leadership.ordinal()) return new Skill(SkillID, 0, Characteristics.Presence);
		if (SkillID == Skills.lore.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.mechanics.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.medicine.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.melee.ordinal()) return new Skill(SkillID, 0, Characteristics.Brawn);
		if (SkillID == Skills.negotiation.ordinal()) return new Skill(SkillID, 0, Characteristics.Presence);
		if (SkillID == Skills.outer_rim.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.perception.ordinal()) return new Skill(SkillID, 0, Characteristics.Cunning);
		if (SkillID == Skills.piloting_planetary.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.piloting_space.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.ranged_heavy.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.ranged_light.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.resilience.ordinal()) return new Skill(SkillID, 0, Characteristics.Brawn);
		if (SkillID == Skills.skulduggery.ordinal()) return new Skill(SkillID, 0, Characteristics.Cunning);
		if (SkillID == Skills.stealth.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.streetwise.ordinal()) return new Skill(SkillID, 0, Characteristics.Cunning);
		if (SkillID == Skills.survival.ordinal()) return new Skill(SkillID, 0, Characteristics.Cunning);
		if (SkillID == Skills.underworld.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.vigilance.ordinal()) return new Skill(SkillID, 0, Characteristics.Willpower);
		if (SkillID == Skills.xenology.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);
		if (SkillID == Skills.lightsaber.ordinal()) return new Skill(SkillID, 0, Characteristics.Agility);
		if (SkillID == Skills.warfare.ordinal()) return new Skill(SkillID, 0, Characteristics.Intellect);


		return null;
	}
	
	public static ArrayList<String> getAllSkillNames() {
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i < Skills.values().length; i++) 
			array.add(App.getContext().getString(SkillRessources[i]));
		
		return array;
			
	}
    
	
	public enum Skills {
		astrogation,
		athletics,
		brawl,
		charm,
		coercion,
		computers,
		cool,
		coordination,
		core_worlds,
		deception,
		discipline,
		education,
		gunnery,
		leadership,
		lore,
		mechanics,
		medicine,
		melee,
		negotiation,
		outer_rim,
		perception,
		piloting_planetary,
		piloting_space,
		ranged_heavy,
		ranged_light,
		resilience,
		skulduggery,
		stealth,
		streetwise,
		survival,
		underworld,
		vigilance,
		xenology,
		lightsaber,
		warfare

	}
	
	
	public static int[] SkillRessources = {
			R.string.skill_astrogation,
			R.string.skill_athletics,
			R.string.skill_brawl,
			R.string.skill_charm,
			R.string.skill_coercion,
			R.string.skill_computers,
			R.string.skill_cool,
			R.string.skill_coordination,
			R.string.skill_core_worlds,
			R.string.skill_deception,
			R.string.skill_discipline,
			R.string.skill_education,
			R.string.skill_gunnery,
			R.string.skill_leadership,
			R.string.skill_lore,
			R.string.skill_mechanics,
			R.string.skill_medicine,
			R.string.skill_melee,
			R.string.skill_negotiation,
			R.string.skill_outer_rim,
			R.string.skill_perception,
			R.string.skill_piloting_planetary,
			R.string.skill_piloting_space,
			R.string.skill_ranged_heavy,
			R.string.skill_ranged_light,
			R.string.skill_resilience,
			R.string.skill_skulduggery,
			R.string.skill_stealth,
			R.string.skill_streetwise,
			R.string.skill_survival,
			R.string.skill_underworld,
			R.string.skill_vigilance,
			R.string.skill_xenology,
			R.string.skill_lightsaber,
			R.string.skill_warfare
	};

	
	
	public static String toDatabaseString(ArrayList<Skill> skills) {
		String s = "";
		
		for (Skill skill : skills) {
			s += String.format("%02d%02d", skill.SkillID.ordinal(), skill.Value);
		}
		
		
		
		return s;
	}
	
	public static ArrayList<Skill> fromDatabaseString(String s) {
		ArrayList<Skill> array = new ArrayList<Skill>();
		for (int i = 0; i < s.length(); i += 4)
		{
			Skill sk = Skill.getNewSkill(Integer.valueOf(s.substring(i, i+2)));
			sk.Value = Integer.valueOf(s.substring(i+2, i+4));
			array.add(sk);
		}
		
		
		return array;
	}
	
}
