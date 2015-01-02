package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.dragonrider.SWEotE.R;



public class Armor extends Item {
	public int Defense;
	public int Soak;
	public int HardPoints;
	public int DatabaseID;
	
	
	public Armor() {
		
	}
	
	public Armor(String Name, int Defense, int Soak, int Price, int Encumbrance, int HardPoints, int Rarity, boolean Restricted, String Qualities){
		this.Name = Name;
		this.Defense = Defense;
		this.Soak = Soak;
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
	
	@Override
	public String toString() {
		String s = this.Name;
		
		String infoString = "";
		
		if (this.Soak > 0) infoString += App.getContext().getString(R.string.soak) + " " + String.valueOf(Soak) + " ";
		if (this.Defense > 0) infoString += App.getContext().getString(R.string.defense) + " " + String.valueOf(Defense) + " ";

		infoString = infoString.trim();
		
		if (!infoString.isEmpty())
			s += String.format(" (%s)", infoString);
		
		return s;
		
		
	}
	
	
	
	

	
	public String toDatabaseString() {
    	String s = "";
    	s +="#ARMO";
    	
    	s += String.format("%s|%s|%d|%d|%d|%d|%d|%d|%s",
    			this.Name,
    			this.Description,
    			this.Encumbrance,
    			this.Price,
    			this.Rarity,
    			this.Soak,
    			this.Defense,
    			this.Restricted ? 1 : 0,
    			ItemQuality.toDatabaseString(Qualities));
		return s;
	}
	
    public static Armor fromDatabaseString(String dbString) {
    	
    	String[] hopla = dbString.split("[|]");
    	Armor itm = new Armor();
    	itm.Name = hopla[0].replace("ARMO", "");
    	itm.Description = hopla[1];
    	itm.Encumbrance = Integer.valueOf(hopla[2]);
    	itm.Price = Integer.valueOf(hopla[3]);
    	itm.Rarity = Integer.valueOf(hopla[4]);
    	itm.Soak = Integer.valueOf(hopla[5]);
    	itm.Defense = Integer.valueOf(hopla[6]);
    	itm.Restricted = hopla[7].equals("1");

    	
    	itm.Qualities = ItemQuality.fromDatabaseString(hopla[8]);
    	
    	return itm;
    	
    
    }
	
	
//	public static Armor fromJSON(JSONObject object) {
//		Armor armor = new Armor();
//		
//		try {
//			armor.Price = object.getInt("Price");
//			armor.Defense = object.getInt("Defense");
//			armor.Encumbrance = object.getInt("Encumbrance");
//			armor.HardPoints = object.getInt("HardPoints");
//			String[] qualities = object.getString("Qualities").split("[*]");
//			for (String string : qualities) {
//				if (string.isEmpty()) continue;
//				ItemQuality quality = new ItemQuality();
//				quality.QualityID = Integer.valueOf(string.substring(0, 2));
//				quality.Value = Integer.valueOf(string.substring(2));
//				armor.Qualities.add(quality);
//			}
//			armor.Rarity = object.getInt("Rarity");
//			armor.Name = object.getString("Name");
//			armor.Restricted = object.getInt("Restricted") == 0 ? false : true;
//			armor.Soak = object.getInt("Soak");
//			armor.DatabaseID = object.getInt("id");
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		return armor;
//	}

	
	
	public ArrayList<NameValuePair> toNameValueArray(int PlayerID) {

		
		ArrayList<NameValuePair> array = new ArrayList<NameValuePair>();
		array.add(new BasicNameValuePair("CharID", String.valueOf(PlayerID)));
		array.add(new BasicNameValuePair("Name", Name));
		array.add(new BasicNameValuePair("Soak", String.valueOf(Soak)));
		array.add(new BasicNameValuePair("Defense", String.valueOf(Defense)));
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
	
	
	
	public static Armor[] BaseArmors = new Armor[] {
		new Armor("Armure Environnementale", 0, 1, 500, 2, 2, 1, false, ""),
		new Armor("Vetements blindés", 1, 1, 1000, 3, 1, 6, false, ""),
		new Armor("Armure lourde de combat", 1, 2, 5000, 6, 4, 7, true, ""),
		new Armor("Vetements renforcés", 0, 1, 50, 1, 0, 0, false, ""),
		new Armor("Armure Laminée", 0, 2, 2500, 4, 3, 5, false, ""),
		new Armor("Bouclier personnel", 2, 0, 10000, 3, 0, 8, false, ""),
		new Armor("Armure de Combat", 0, 2, 500, 2, 0, 1, false, ""),
	};
    
}
