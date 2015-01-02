package com.dragonrider.SWEotE.Sabacc;

import java.util.List;
import java.util.Random;

import com.dragonrider.SWEotE.Classes.App;
import com.dragonrider.SWEotE.Classes.Characteristic.Characteristics;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.Classes.XmlImport;
import com.dragonrider.SWEotE.Classes.Skill.Skills;


public class SabaccPlayer {

	private String Name = "";
	
	private int TotalScore = 0;
	
	
	private int Credits = 100;

	private int Presence = 1;
	public int getPresence() {
		return Presence;
	}

	public void setPresence(int presence) {
		Presence = presence;
	}

	public int getIntellect() {
		return Intellect;
	}

	public void setIntellect(int intellect) {
		Intellect = intellect;
	}

	public int getCunning() {
		return Cunning;
	}

	public void setCunning(int cunning) {
		Cunning = cunning;
	}

	public int getComputers() {
		return Computers;
	}

	public void setComputers(int computers) {
		Computers = computers;
	}

	public int getDeceit() {
		return Deceit;
	}

	public void setDeceit(int deceit) {
		Deceit = deceit;
	}

	public int getSkulduggery() {
		return Skulduggery;
	}

	public void setSkulduggery(int skulduggery) {
		Skulduggery = skulduggery;
	}

	public int getCool() {
		return Cool;
	}

	public void setCool(int cool) {
		Cool = cool;
	}

	public int getStrainThreshold() {
		return StrainThreshold;
	}

	public void setStrainThreshold(int strainThreshold) {
		StrainThreshold = strainThreshold;
	}

	public int getActualStrain() {
		return ActualStrain;
	}

	public void setActualStrain(int actualStrain) {
		ActualStrain = actualStrain;
	}

	public int getCheating() {
		return Cheating;
	}

	public void setCheating(int cheating) {
		Cheating = cheating;
	}
	
	public void increaseCheating() {
		this.Cheating++;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private int Intellect = 1;
	private int Cunning = 1;

	private int Computers = 0;
	private int Deceit = 0;
	private int Skulduggery = 0;
	private int Cool = 0;
	
	private int StrainThreshold = 1;
	private int ActualStrain = 0;
	
	private int Cheating = 0;
	
	
	private String description = "";
	
	
	public static SabaccPlayer fromNPC(String selectedNPC) {
		
		SabaccPlayer player = new SabaccPlayer();
		Database db = new Database(App.getContext());
		NPC npc = db.GetNPCbyName(selectedNPC);
		db.close();
		
		player.setActualStrain(0);
		player.setCheating(0);
		player.setComputers(npc.GetSkill(Skills.computers.ordinal()).Value);
		player.setCool(npc.GetSkill(Skills.cool.ordinal()).Value);
		player.setCunning(npc.Characteristics.get(Characteristics.Cunning.ordinal()));
		player.setDeceit(npc.GetSkill(Skills.deception.ordinal()).Value);
		player.setDescription(npc.Description);
		player.setIntellect(npc.Characteristics.get(Characteristics.Intellect.ordinal()));
		player.setName(npc.Name);
		player.setPresence(npc.Characteristics.get(Characteristics.Presence.ordinal()));
		player.setSkulduggery(npc.GetSkill(Skills.skulduggery.ordinal()).Value);
		player.setStrainThreshold(npc.TotalStrain);
		if (npc.Credits == 0)
			player.setCredits(new Random().nextInt(100) + 50);
		return player;
	}
	
	public static SabaccPlayer fromPC(String PCName) {
		
		SabaccPlayer player = new SabaccPlayer();

		List<NPC> npcs = XmlImport.ImportPCs();
		
		for (NPC npc : npcs) {
			if (!npc.Name.equals(PCName)) continue;
			
		
		
			
			player.setActualStrain(0);
			player.setCheating(0);
			player.setComputers(npc.GetSkill(Skills.computers.ordinal()).Value);
			player.setCool(npc.GetSkill(Skills.cool.ordinal()).Value);
			player.setCunning(npc.Characteristics.get(Characteristics.Cunning.ordinal()));
			player.setDeceit(npc.GetSkill(Skills.deception.ordinal()).Value);
			player.setDescription(npc.Description);
			player.setIntellect(npc.Characteristics.get(Characteristics.Intellect.ordinal()));
			player.setName(npc.Name);
			player.setPresence(npc.Characteristics.get(Characteristics.Presence.ordinal()));
			player.setSkulduggery(npc.GetSkill(Skills.skulduggery.ordinal()).Value);
			player.setStrainThreshold(npc.TotalStrain);
			player.setCredits(npc.Credits);
			player.setPC(true);
		}
		return player;
	}
	
	

	public int getCredits() {
		return Credits;
	}

	public void setCredits(int credits) {
		Credits = credits;
	}

	public void betCredits(int i) {
		Credits -= i;
		
	}
	
	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}

	private boolean folded = false;
	public void gainCredits(int handPot) {
		Credits += handPot;
		
	}

	private boolean isPC = false;
	
	
	public boolean isPC() {
		return isPC;
	}

	public void setPC(boolean isPC) {
		this.isPC = isPC;
	}

	public int getTotalScore() {
		return TotalScore;
	}

	public void setTotalScore(int totalScore) {
		TotalScore = totalScore;
	}
	
	
}
