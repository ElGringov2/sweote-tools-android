package com.dragonrider.SWEotE.Classes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.dragonrider.SWEotE.R;




public class NPC {

	
	public String Name = "newNPC";
	public String OwnerName = "";
	public String Description = "";
	public String Category = "";
	public int TotalWounds;
	public int TotalStrain;
	public NPCTypes Type;
	
	public int Soak;
	public int MeleeDefense;
	public int RangedDefense;
	
	public String Race = "Human";
		
	private Bitmap Image = null;
	
	public List<Skill> Skills = new ArrayList<Skill>();
	public List<Integer> Characteristics = new ArrayList<Integer>();
	public List<Talent> Talents = new ArrayList<Talent>();
	public List<String> Abilities = new ArrayList<String>();
	public List<Item> Items = new ArrayList<Item>();
	public int DatabaseID = -1;
	public int Experience;
	public String Key = "";
	public Integer ForceRating = 0;
	public Integer Credits = 0;
    public List<ForcePower> ForcePowers = new ArrayList<ForcePower>();


    public NPC() {
		this.Characteristics =  Characteristic.initializeCharacteristic(2);
		Name = "";
		Description = "";
		TotalWounds = 0;
		TotalStrain = 0;
		Soak =  0;
		MeleeDefense = 0;
		RangedDefense = 0;
		Type = NPCTypes.Minion;
		
	}


    public List<String> getCompiledForcePowers() {
        Hashtable<String, List<ForcePower>> powers = new Hashtable<String, List<ForcePower>>();
        for (ForcePower pow : ForcePowers){
            if (!powers.containsKey(pow.Power))
                powers.put(pow.Power, new ArrayList<ForcePower>());
            powers.get(pow.Power).add(pow);
        }

        ArrayList<String> sReturn = new ArrayList<String>();

        for (String key : powers.keySet())
        {
            String forcePower = key + ": ";


            for (ForcePower pow : powers.get(key))
            {
                forcePower += pow.Name;
                if (pow.Upgrades > 1)
                    forcePower += " (" + String.valueOf(pow.Upgrades) + "): ";
                else
                    forcePower += ": ";
                forcePower += pow.Description;
                forcePower += "\n";

            }




            sReturn.add(forcePower);
        }



        return sReturn;

    }
	
	@Override
	public String toString() {
		return Name;
	}

    public int getTalent(Talent.TalentIDs adversary) {
        for(Talent talent : Talents)
            if (talent.TalentID == adversary.ordinal())
                return talent.TalentValue;

        return 0;

    }


    public enum NPCTypes
    {
        Minion,
        Nemesis,
        Rival;
        
        public String toString() {
        	if (this == Minion) return App.getContext().getString(R.string.minion);
        	if (this == Nemesis) return App.getContext().getString(R.string.nemesis);
        	if (this == Rival) return App.getContext().getString(R.string.rival);
        	return "";
        }
    }


    public void SetSkill(int SkillID, int SkillValue) {
    	for (Skill sk : this.Skills)
    		if (sk.SkillID.ordinal() == SkillID) {
    			sk.Value = SkillValue;
    			return;
    		}
    	
    	Skill sk = Skill.getNewSkill(SkillID);
    	sk.Value = SkillValue;
    	
    	Skills.add(sk);
    		
    }
    
    public Skill GetSkill(int SkillID) {
    	
    	
    	for (Skill sk : this.Skills)
    		if (sk.SkillID.ordinal() == SkillID) {
//    			if (Type == NPCTypes.Minion){
//    				Skill minionSKill = Skill.getNewSkill(SkillID);
//    				minionSKill.Value = 1;
//    				return minionSKill;
//    						
//    			}
    			return sk;
    		}
    	


    	return Skill.getNewSkill(SkillID);
    	
    }
    


    
    public void setItemsFromDatabaseString(String s) {
    	
    	String[] items = s.split("#");
    	Items = new ArrayList<Item>();
    	for (String string : items) {
			if (string.startsWith("ARMO")) Items.add(Armor.fromDatabaseString(string));
			if (string.startsWith("WEAP")) Items.add(Weapon.fromDatabaseString(string));
			if (string.startsWith("GEAR")) Items.add(Item.fromDatabaseString(string));
		}
    	
    	
    }
    
    public String getItemsForDatabaseString() {
    	String s = "";
    	for (Item itm : Items) {
			if (itm.getClass() == Armor.class)
				s += ((Armor)itm).toDatabaseString();
			if (itm.getClass() == Weapon.class)
				s += ((Weapon)itm).toDatabaseString();			
			if (itm.getClass() == Item.class)
					s += ((Item)itm).toDatabaseString();		}
    	
    	return s;
    }
    
    
    public Bitmap getImage() {
    	if (Image == null) {
    		

            Log.i("Hopla", "race=" + Race + ",SpeciesExist=" + String.valueOf(SpeciesBitmapIndex.SpeciesExist(Race)));

    		
    		if (!SpeciesBitmapIndex.SpeciesExist(Race)) return null;
    		
    		return BitmapFactory.decodeResource(App.getContext().getResources(), SpeciesBitmapIndex.getSpeciesIndex(Race));
    		
    	}
		return Image;
	}

	public void setImage(byte[] data) {
		Image = BitmapFactory.decodeByteArray(data, 0, data.length);

	}

	public void setImage(Bitmap b) {
		Image = b;
		
	}
	
	public byte[] getImageData() {
		if (this.Image == null) return new byte[0];
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		this.Image.compress(Bitmap.CompressFormat.PNG, 100, stream);
		return stream.toByteArray();
	}



    
}
