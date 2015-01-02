package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;



public class Item {

	public String Name ="";
	public String id ="";
	public String Description = "";
	public int Encumbrance = 0;
    public int Price = 1;
    public int Rarity = 1;
    public Boolean Restricted = false;
    public ArrayList<ItemQuality> Qualities = new ArrayList<ItemQuality>();
    
    
    @Override
    public String toString() {
    	String s = String.format("%s", this.Name);
    	return s;
    }
    
    
    public String toDatabaseString() {
    	String s = "";
    	s +="#GEAR";
    	
    	s += String.format("%s|%s|%d|%d|%d|%d|%s",
    			this.Name,
    			this.Description,
    			this.Encumbrance,
    			this.Price,
    			this.Rarity,
    			(this.Restricted == true ? 1 : 0),
    			ItemQuality.toDatabaseString(Qualities));
    	
    	
    	return s;
    }
    
    public static Item fromDatabaseString(String dbString) {
    	
    	String[] hopla = dbString.split("[|]");
    	Item itm = new Item();
    	itm.Name = hopla[0].replace("GEAR", "");
    	itm.Description = hopla[1];
    	itm.Encumbrance = Integer.valueOf(hopla[2]);
    	itm.Price = Integer.valueOf(hopla[3]);
    	itm.Rarity = Integer.valueOf(hopla[4]);
    	itm.Restricted = (hopla[5].equals("1") ? true : false);
    	itm.Qualities = ItemQuality.fromDatabaseString(hopla[6]);
    	
    	return itm;
    	
    
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
