package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;

import com.dragonrider.SWEotE.R;

public class Characteristic {

	public static int get(NPC npc, int CharacteristicID) {
		return npc.Characteristics.get(CharacteristicID);
	}
	
	public static ArrayList<Integer> initializeCharacteristic(int baseValue) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(baseValue);
		array.add(baseValue);
		array.add(baseValue);
		array.add(baseValue);
		array.add(baseValue);
		array.add(baseValue);
		
		return array;
	}
	
	public static String toDatabaseString(ArrayList<Integer> array) {
		return String.format("%02d%02d%02d%02d%02d%02d", 
				array.get(0),
				 array.get(1),
				 array.get(2),
				 array.get(3),
				 array.get(4),
				 array.get(5));
	}
	public static ArrayList<Integer> fromDatabaseString(String s) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(Integer.valueOf(s.substring(0, 2)));
		array.add(Integer.valueOf(s.substring(2, 4)));
		array.add(Integer.valueOf(s.substring(4, 6)));
		array.add(Integer.valueOf(s.substring(6, 8)));
		array.add(Integer.valueOf(s.substring(8, 10)));
		array.add(Integer.valueOf(s.substring(10, 12)));
		
		return array;
	}
	
	public static String getName(int CharacteristicID) {
		return App.getContext().getString(CharacteristicNames[CharacteristicID]);
	}
	public static String getAbr(int CharacteristicID) {
		return App.getContext().getString(CharacteristicAbrNames[CharacteristicID]);
	}
	
	public enum Characteristics {
		Brawn,
		Agility,
		Cunning,
		Intellect,
		Willpower,
		Presence
	}
	
	private static int[] CharacteristicNames = {
		R.string.brawn,
		R.string.agility,
		R.string.cunning,
		R.string.intellect,
		R.string.willpower,
		R.string.presence
	};
	
	private static int[] CharacteristicAbrNames = {
		R.string.brawn_abr,
		R.string.agility_abr,
		R.string.cunning_abr,
		R.string.intellect_abr,
		R.string.willpower_abr,
		R.string.presence_abr
	};
	
}
