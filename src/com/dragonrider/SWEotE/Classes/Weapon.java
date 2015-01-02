package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.dragonrider.SWEotE.R;


public class Weapon extends Item {

    public String Key = "";
	public String UsedSkill;
    public int Damage = 0;
    public int CriticalValue;
    public String Range= "Engaged";
    public int RangeID;
    public String Traits = "";

    public int Encumbrance;
    public int HardPoints;
    public String Category;
    
    public int SkillID;
    
    public int DatabaseID = -1;
    
    public Weapon() {
    	
    }
    public Weapon (String name, int Critical, int Damage, int SkillID, String Range, int Price, int Encumbrance, int HardPoints, int Rarity, boolean Restricted, String Qualities ) {
    	this.Name = name;
    	this.CriticalValue = Critical;
    	this.Damage = Damage;
    	this.SkillID = SkillID;
    	this.Range = Range;
    	this.Price = Price;
    	this.Encumbrance = Encumbrance;
    	this.HardPoints = HardPoints;
    	this.Rarity  = Rarity;
    	this.Restricted = Restricted;
    	this.Qualities = new ArrayList<ItemQuality>();
		for (String string : Qualities.split("[*]")) {
			if (string.isEmpty()) continue;
			ItemQuality quality = new ItemQuality();
			quality.QualityID = Integer.valueOf(string.substring(0, 2));
			quality.Value = Integer.valueOf(string.substring(2));
			this.Qualities.add(quality);
		}
    }
    

	
	public String toDatabaseString() {
    	String s = "";
    	s +="#WEAP";
    	
    	s += String.format("%s|%s|%d|%d|%d|%d|%d|%d|%d|%d|%s",
    			this.Name,
    			this.Description,
    			this.Encumbrance,
    			this.Price,
    			this.Rarity,
    			this.CriticalValue,
    			this.Damage,
    			this.RangeID,
    			this.SkillID,
    			(this.Restricted == true ? 1 : 0),
    			ItemQuality.toDatabaseString(Qualities));
		return s;
	}
	
    public static Weapon fromDatabaseString(String dbString) {
    	
    	String[] hopla = dbString.split("[|]");
    	Weapon itm = new Weapon();
    	itm.Name = hopla[0].replace("WEAP", "");
    	itm.Description = hopla[1];
    	itm.Encumbrance = Integer.valueOf(hopla[2]);
    	itm.Price = Integer.valueOf(hopla[3]);
    	itm.Rarity = Integer.valueOf(hopla[4]);
    	itm.CriticalValue = Integer.valueOf(hopla[5]);
    	itm.Damage = Integer.valueOf(hopla[6]);
    	itm.RangeID = Integer.valueOf(hopla[7]);
    	//itm.Range = App.getContext().getString(itm.RangeID);
    	itm.SkillID = Integer.valueOf(hopla[8]);
    	itm.Restricted = (hopla[9].equals("1") ? true : false);

    	
    	itm.Qualities = ItemQuality.fromDatabaseString(hopla[10]);
    	
    	
    	
    	return itm;
    	
    
    }
    
    public static String getAbilityDescription (String Traits) {
    	String s = "";

    	if (Traits.contains("Defensive")) s += "Defensive: Increase target�s melee defense by Defensive rating.";
    	if (Traits.contains("Deflection"))  s += "Deflection: Increase target�s ranged defense by Deflection rating.";
    	if (Traits.contains("Concussive"))  s += "Concussive: Spend to make target staggered/disoriented/immobilized for number of rounds equal to rating. Ensnared target may make Hard (3) Athletics check as action to break free.";
    	if (Traits.contains("Disorient"))  s += "Disorient: Spend AA to make target staggered/disoriented/immobilized for number of rounds equal to rating. Ensnared target may make Hard (3) Athletics check as action to break free.";
    	if (Traits.contains("Ensnare"))  s += "Ensnare: Spend AA to make target staggered/disoriented/immobilized for number of rounds equal to rating. Ensnared target may make Hard (3) Athletics check as action to break free.";
    	if (Traits.contains("Knockdown"))  s += "Knockdown: Spend AA (plus per silhouette of target above 1) to knock target prone.";
    	if (Traits.contains("Guided"))  s += "Guided: If attack misses, spend AAA to make check at end of round with equal to rating and difficulty calculated by comparing 0 silhouette to silhouette of target. If successful, weapon hits target.";
    	if (Traits.contains("Blast"))  s += "Blast: If attack successful, spend AA to deal damage equal to rating to characters engaged with target. If attack misses, spend AAA to deal damage equal to Blast rating to target and characters engaged with target.";
    	if (Traits.contains("Burn"))  s += "Burn: Spend AA to make target suffer weapon�s base damage for a number of rounds equal to rating at the start of turn. Burning target may make Coordination check, Average (2) difficulty on hard surfaces, Easy (1) difficulty on soft surfaces, automatic if immersed, to extinguish.";
    	if (Traits.contains("Stun"))  s += "Stun: Spend AA to inflict strain equal to rating.";
    	if (Traits.contains("Sunder"))  s += "Sunder: Damage item openly wielded by target one step per A spent: Minor -> Moderate -> Major -> Destroyed.";
    	if (Traits.contains("Cortosis"))  s += "Cortosis: Ignores the Sunder weapon quality.";
    	if (Traits.contains("Inferior"))  s += "Inferior: Automatically generates T on all checks, base damage decreased/increased by 1.";
    	if (Traits.contains("Superior"))  s += "Superior: Automatically generates A on all checks, base damage decreased/increased by 1.";
    	if (Traits.contains("Limited Ammo"))  s += "Limited Ammo: Expends ammo and requires maneuver to reload after rating number of attacks.";
    	if (Traits.contains("Slow Firing"))  s += "Slow Firing: Must wait rating number of rounds after firing weapon before it can fire again.";
    	return s;
    }
    
    public static Weapon getFists() {
    	Weapon wp = new Weapon();
    	wp.Name = App.getContext().getString(R.string.weapon_fists);
    	wp.Category="Melee";
    	wp.Damage=0;
    	wp.CriticalValue = 5;
    	wp.Range = App.getContext().getString(R.string.range_engaged);
    	wp.Traits = "Disorient 1 Knockdown";
    	ItemQuality quality = new ItemQuality();
		quality.QualityID = 10;
		quality.Value = 1;
		wp.Qualities.add(quality);
		quality = new ItemQuality();
		quality.QualityID = 13;
		quality.Value = 0;
		wp.Qualities.add(quality);
    	wp.SkillID = Skill.Skills.brawl.ordinal();
    	wp.UsedSkill = "Brawl";
    	return wp;
    			
    }
    
    
    @Override
    public String toString() {
    	

    	String s = String.format("%s (%s, %s %d, %s %d, %s %s %s",
    			Name,
    			App.getContext().getString(Skill.SkillRessources[SkillID]),
    			App.getContext().getString(R.string.weapon_damage),
    			Damage, 
    			App.getContext().getString(R.string.weapon_critical),
    			CriticalValue,
    			App.getContext().getString(R.string.range),
    			ranges[RangeID],
    			ItemQuality.getLightDescription(Qualities)
    	).trim();
    	
    	return s + ")";
    	
    	
    }
    
    
    private String[] ranges = { App.getContext().getString(R.string.range_engaged),
			App.getContext().getString(R.string.range_short),
			App.getContext().getString(R.string.range_medium),
			App.getContext().getString(R.string.range_long),
			App.getContext().getString(R.string.range_extreme)
			
	};
    
    public static Weapon[] BaseWeapons = new Weapon[] {
    	new Weapon("Blaster de poche", 4, 5, 26, "Courte", 200, 1, 1, 4, false, "280"),
    	new Weapon("Pistolet blaster l�ger", 4, 5, 26, "Moyenne", 300, 1, 2, 4, false, "280"),
    	new Weapon("Pistolet blaster", 3, 6, 26, "Moyenne", 400, 1, 3, 4, false, "280"),
    	new Weapon("Pistolet blaster lourd", 3, 7, 26, "Moyenne", 700, 2, 3, 6, false, "280"),
    	new Weapon("Carabine Blaster", 3, 9, 27, "Moyenne", 850, 3, 4, 5, false, "280"),
    	new Weapon("Fusil blaster", 3, 9, 27, "Longue", 900, 4, 4, 5, false, "280"),
    	new Weapon("Fusil blaster lourd", 3, 10, 27, "Longue", 1500, 6, 4, 6, false, "010*073"),
    	new Weapon("Mitrailleuse blaster", 3, 11, 27, "Longue", 2250, 7, 4, 7, true, "010*074*191"),
    	new Weapon("Mitrailleuse blaster lourde", 2, 15, 23, "Longue", 6000, 9, 4, 8, true, "010*075*192*271"),
    	new Weapon("Arbalete laser", 3, 10, 27, "Moyenne", 1250, 5, 2, 7, false, "073*130"),
    	new Weapon("Blaster � Ion", 5, 10, 26, "Courte", 250, 3, 3, 3, false, "105*230"),
    	new Weapon("Pistolet Disrupteur", 2, 10, 26, "Courte", 3000, 2, 2, 6, true, "274"),
    	new Weapon("Fusil Disrupteur", 2, 10, 27, "Longue", 5000, 5, 4, 6, true, "072*275"),
    	new Weapon("Pistolet � balles", 5, 4, 26, "Courte", 100, 1, 0, 3, false, ""),
    	new Weapon("Fusil � balle", 5, 7, 27, "Moyenne", 250, 5, 1, 3, false, "072"),
    	new Weapon("Bola", 99, 2, 26, "Courte", 20, 1, 2, 2, false, "113*130*171"),
    	new Weapon("Lance-flamme", 2, 8, 27, "Courte", 1000, 6, 2, 6, false, "034*022"),
    	new Weapon("Lance-missile", 2, 20, 23, "Extreme", 7500, 7, 4, 8, true, "024*073*123*031*201*176"),
    	new Weapon("Grenade � Fragmentation", 4, 8, 26, "Courte", 50, 1, 0, 5, false, "020*171"),
    	new Weapon("Grenade incapacitante", 99, 8, 26, "Courte", 75, 1, 0, 4, false, "103*230*022*171"),
    	new Weapon("D�tonateur Thermique", 2, 20, 26, "Courte", 2000, 1, 0, 8, true, "029*031*274*171"),
    	new Weapon("Coup de Poing", 4, 1, 22, "Engagn�", 25, 1, 0, 0, false, "103"),
    	new Weapon("Gants Choc", 5, 0, 22, "Engagn�", 300, 0, 1, 2, false, "223"),
    	new Weapon("Couteau", 3, 1, 25, "Engagn�", 25, 1, 0, 1, false, ""),
    	new Weapon("B�ton Gaffi", 3, 2, 25, "Engagn�", 100, 3, 0, 2, false, "081*103"),
    	new Weapon("Pique de force", 2, 3, 25, "Engagn�", 500, 3, 3, 4, false, "192*280"),
    	new Weapon("Sabrolaser", 1, 10, 24, "Engagn�", 10000, 1, 0, 10, true, "031*240*272"),
    	new Weapon("Matraque", 5, 2, 25, "Engagn�", 15, 2, 0, 1, false, "102"),
    	new Weapon("Vibro-Hache", 2, 3, 25, "Engagn�", 750, 4, 3, 5, false, "192*240*273"),
    	new Weapon("Vibro-Couteau", 2, 1, 25, "Engagn�", 250, 1, 2, 3, false, "192*271"),
    	new Weapon("Vibro-Ep�e", 2, 2, 25, "Engagn�", 750, 3, 3, 5, false, "192*271*081")



    };

	public static Weapon fromJSON(JSONObject object) {
		Weapon item = new Weapon();
		
		try {
			item.Price = object.getInt("Price");
			item.Encumbrance = object.getInt("Encumbrance");
			
			item.HardPoints = object.getInt("HardPoints");
			String[] qualities = object.getString("Qualities").split("[*]");
			for (String string : qualities) {
				if (string.isEmpty()) continue;
				ItemQuality quality = new ItemQuality();
				quality.QualityID = Integer.valueOf(string.substring(0, 2));
				quality.Value = Integer.valueOf(string.substring(2));
				item.Qualities.add(quality);
			}
			item.Rarity = object.getInt("Rarity");
			item.Name = object.getString("Name");
			item.Restricted = object.getInt("Restricted") == 0 ? false : true;
			
			item.Damage = object.getInt("Damage");
			item.SkillID = object.getInt("SkillID");
			item.CriticalValue = object.getInt("Critical");
			item.Range = object.getString("Range");
			
			item.DatabaseID = object.getInt("id");

			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		return item;
	}
	
	
	
	
	public ArrayList<NameValuePair> toNameValueArray(int PlayerID) {

		
		ArrayList<NameValuePair> array = new ArrayList<NameValuePair>();
		array.add(new BasicNameValuePair("CharID", String.valueOf(PlayerID)));
		array.add(new BasicNameValuePair("Name", Name));
		array.add(new BasicNameValuePair("Critical", String.valueOf(CriticalValue)));
		array.add(new BasicNameValuePair("Damage", String.valueOf(Damage)));
		array.add(new BasicNameValuePair("SkillID", String.valueOf(SkillID)));
		array.add(new BasicNameValuePair("Range", Range));
		array.add(new BasicNameValuePair("Price", String.valueOf(Price)));
		array.add(new BasicNameValuePair("Encumbrance", String.valueOf(Encumbrance)));
		array.add(new BasicNameValuePair("HardPoints", String.valueOf(HardPoints)));
		array.add(new BasicNameValuePair("Rarity", String.valueOf(Rarity)));
		array.add(new BasicNameValuePair("Restricted", Restricted ? "1" : "0"));
		String sQualities = "";
		for (ItemQuality quality : Qualities) {
			sQualities += String.format(Locale.getDefault(),"%02d%d*", quality.QualityID, quality.Value);
			
		}
		if (sQualities.endsWith("*")) sQualities = sQualities.substring(0, sQualities.length() - 1);
		array.add(new BasicNameValuePair("Qualities", sQualities));
		
		return array;
	}
    
}
