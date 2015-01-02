package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;

import android.content.Context;

import com.dragonrider.SWEotE.R;

public class ItemQuality {
	
	public ItemQuality() {
		
	}
	public ItemQuality(int QualityID, int Value) {
		this.QualityID = QualityID;
		this.Value = Value;
	}

	public int QualityID;
	public int Value;
	public String GetName(Context context){
		return context.getString(qualityRessources[QualityID]);
	}
	public String GetDescription(Context context){
		
		String sString = context.getString(qualityDescriptionRessources[QualityID]);
		if (sString.contains("%d"))
			sString = String.format(sString, Value);
		
		
		return sString;
	}	
	
	public static ArrayList<ItemQuality> fromDatabaseString (String dbString) {
		
		ArrayList<ItemQuality> lst = new ArrayList<ItemQuality>();
		
		if (dbString.equals(" "))
			return lst;
		for (int i = 0; i < dbString.length(); i += 3)
    	{
    		ItemQuality qual = new ItemQuality();
    		qual.QualityID = Integer.valueOf(dbString.substring(i, i+2));
    		qual.Value = Integer.valueOf(dbString.substring(i+2, i+3));
    		lst.add(qual);
    	}
    	
		return lst;
    	
	}
	
	public static String toDatabaseString(ArrayList<ItemQuality> qualities) {
    	String s = "";
		for (ItemQuality quality : qualities) {
			s += String.format("%02d%d", quality.QualityID, quality.Value);
		}
		if (s.isEmpty()) s = " ";
		return s;
	}
	
	public static String getLightDescription(ArrayList<ItemQuality> qualities) {
		String s = "";
		for (ItemQuality quality : qualities) {
			s += quality.toString() + " ";
		}
		s = s.trim();
		return s;
		
	}
	
	public String toDescriptionString() {
		return String.format("%s %s(%s)", 
				this.GetName(App.getContext()),
				this.Value > 0 ? String.valueOf(this.Value) + " " : "",
				this.GetDescription(App.getContext()));
	}
	
	@Override
	public String toString() {
		return String.format("%s %d", this.GetName(App.getContext()), this.Value > 0 ? this.Value : 0).trim();
	}
	
	public enum QualityList {
		accurate, //0
		autofire, // 1
		blast, // 2
		breach, //3
		burn,			//4
		concussive,	//5
		cortosis,		//6
		cumbersome,	//7
		defensive,	//8
		deflection, //9
		disorient,//10
		ensnare,//11
		guided,//12
		knockdown,//13
		inaccurate,//14
		inferior,//15
		ion, //16
		limited_ammo,//17
		linked,//18
		pierce,//19
		prepare,//20
		slow_firing,//21
		stun,//22
		stun_damage,//23
		sunder,//24
		superior,//25
		tractor,//26
		vicious,//27
        stun_setting,//28
		stagger,//29
		stun_droid_only, // 30
		stun_damage_droid_only, //31
		unwieldy, //32
        //additionnal_damage_mod, //33
	}
	
	
	
	final int[] qualityRessources = new int[] {
			R.string.item_accurate, //0
			R.string.item_autofire, // 1
			R.string.item_blast, // 2
			R.string.item_breach, //3
			R.string.item_burn,			//4
			R.string.item_concussive,	//5
			R.string.item_cortosis,		//6
			R.string.item_cumbersome,	//7
			R.string.item_defensive,	//8
			R.string.item_deflection, //9
			R.string.item_disorient,//10
			R.string.item_ensnare,//11
			R.string.item_guided,//12
			R.string.item_knockdown,//13
			R.string.item_inaccurate,//14
			R.string.item_inferior,//15
			R.string.item_ion, //16
			R.string.item_limited_ammo,//17
			R.string.item_linked,//18
			R.string.item_pierce,//19
			R.string.item_prepare,//20
			R.string.item_slow_firing,//21
			R.string.item_stun,//22
			R.string.item_stun_damage,//23
			R.string.item_sunder,//24
			R.string.item_superior,//25
			R.string.item_tractor,//26
			R.string.item_vicious,//27
			R.string.item_stun_settings,//28
            R.string.item_stagger, //29
			R.string.item_stun_droid_only, //30
			R.string.item_stun_damage_droid_only, //31
			R.string.item_unwieldy, //32
            //R.string.item_additionnal_damage_mod,

	};
	final int[] qualityDescriptionRessources = new int[] {
			R.string.item_desc_accurate,
			R.string.item_desc_autofire,
			R.string.item_desc_blast,
			R.string.item_desc_breach,
			R.string.item_desc_burn,
			R.string.item_desc_concussive,
			R.string.item_desc_cortosis,
			R.string.item_desc_cumbersome,
			R.string.item_desc_defensive,
			R.string.item_desc_deflection,
			R.string.item_desc_disorient,
			R.string.item_desc_ensnare,
			R.string.item_desc_guided,
			R.string.item_desc_knockdown,
			R.string.item_desc_inaccurate,
			R.string.item_desc_inferior,
			R.string.item_desc_ion, 
			R.string.item_desc_limited_ammo,
			R.string.item_desc_linked,
			R.string.item_desc_pierce,
			R.string.item_desc_prepare,
			R.string.item_desc_slow_firing,
			R.string.item_desc_stun,
			R.string.item_desc_stun_damage,
			R.string.item_desc_sunder,
			R.string.item_desc_superior,
			R.string.item_desc_tractor,
			R.string.item_desc_vicious,
			R.string.item_desc_stun_settings,
            R.string.item_desc_stagger, //29
			R.string.item_desc_stun_droid_only, //30
			R.string.item_desc_stun_damage_droid_only, //31
			R.string.item_desc_unwieldy, //32
            //R.string.item_desc_additionnal_damage_mod,
	};
	
}
