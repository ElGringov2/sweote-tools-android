package com.dragonrider.SWEotE.Classes;

import com.dragonrider.SWEotE.R;




public class SpaceFighter {

	public boolean IsActive;
	public String Name;
	public int ActualStrain;
	public int TotalStrain;
	
	
	public String SelectedManeuver = App.getContext().getString(R.string.none2);
	public String SelectedAction = App.getContext().getString(R.string.none2);
	

	private NPC base;

	
	private RollResult InitiativeRoll = new RollResult();
	private int initiative = -1;
	private int subinitiative = -1;
	
	
	public NPC getBase() {
		return base;
	}
	public void setBase(NPC base) {
		this.base = base;
		this.TotalStrain = base.TotalStrain;


	}
	

	public int getInitiative() {
		if (this.initiative == -1) return InitiativeRoll.Succcess();
		return initiative;
	}
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}


	public int getSubinitiative() {
		if (this.subinitiative == -1) return InitiativeRoll.Advantage();
		return subinitiative;
	}
	public void setSubinitiative(int subinitiative) {
		
		this.subinitiative = subinitiative;
	}


	public RollResult getInitiativeRoll() {
		return InitiativeRoll;
	}
	public void setInitiativeRoll(RollResult initiativeRoll) {
		initiativeRoll.DiceDifficulty = 0;
		initiativeRoll.DiceChallenge = 0;
		InitiativeRoll = initiativeRoll;
	}


	public String ShipID;

	public String id;
	public Boolean isPlayer=false;
	public boolean Played =false;
	public boolean hasPlayed;


	public RollResult GetSkillRollResult(int skillID) {
		Skill sk = getBase().GetSkill(skillID);
		
		
		int skValue = sk.Value;

        RollResult rr = new RollResult();
        rr.DiceDifficulty = 2;
        




        int iCharValue = getBase().Characteristics.get(sk.CharacteristicID);

        if (iCharValue > skValue)
        {
            rr.DiceAbility = iCharValue;
            rr.UpgradePositivePool(skValue);

        }
        else
        {
            rr.DiceAbility = skValue;
            rr.UpgradePositivePool(iCharValue);
        }


        return rr;

	}

	
}
