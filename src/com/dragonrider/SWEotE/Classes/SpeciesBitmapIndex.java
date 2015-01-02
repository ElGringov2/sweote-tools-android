package com.dragonrider.SWEotE.Classes;

import java.util.HashMap;

import com.dragonrider.SWEotE.R;

public class SpeciesBitmapIndex {

	static HashMap<String, Integer> SPECIES = new HashMap<String, Integer>();
	
	static {
		SPECIES.put("Amani", R.drawable.amani);
		SPECIES.put("Anzat", R.drawable.anzat);
		SPECIES.put("Aqualish", R.drawable.aqualish);
		//SPECIES.put("Arcona", R.drawable.arcona);
		SPECIES.put("Balosar", R.drawable.balosar);
		SPECIES.put("Bantha", R.drawable.bantha);
		SPECIES.put("Barabel", R.drawable.barabel);
		SPECIES.put("Besalisk", R.drawable.besalisk);
		SPECIES.put("Bith", R.drawable.bith);
		SPECIES.put("Bothan", R.drawable.bothan);
		SPECIES.put("Caamasi", R.drawable.caamasi);
		SPECIES.put("Cerean", R.drawable.cerean);
		SPECIES.put("Chadra-Fan", R.drawable.chadrafan);
		SPECIES.put("Chagrian", R.drawable.chagrian);
		SPECIES.put("Chevin", R.drawable.chevin);
		SPECIES.put("Chiss", R.drawable.chiss);
		SPECIES.put("Clawdite", R.drawable.clawdite);
		SPECIES.put("Defel", R.drawable.defel);
		SPECIES.put("Devaronian", R.drawable.devaronian);
		SPECIES.put("Dewback", R.drawable.dewback);
		//SPECIES.put("Dianoga", R.drawable.dianoga);
		SPECIES.put("Dressellian", R.drawable.dresselian);
		SPECIES.put("Dug", R.drawable.dug);
		SPECIES.put("Duros", R.drawable.duros);
		SPECIES.put("Elomin", R.drawable.elomin);
		//SPECIES.put("Ewok", R.drawable.ewok);
		SPECIES.put("Falleen", R.drawable.falleen);
		SPECIES.put("Fosh", R.drawable.fosh);
		SPECIES.put("Gamorrean", R.drawable.gamorrean);
		SPECIES.put("Gand", R.drawable.gand);
		SPECIES.put("Geonosian", R.drawable.geonosian);
		SPECIES.put("Givin", R.drawable.givin);
		SPECIES.put("Gotal", R.drawable.gotal);
		SPECIES.put("Gran", R.drawable.gran);
		SPECIES.put("Gundark", R.drawable.gundark);
		SPECIES.put("Gungan", R.drawable.gungan);
		SPECIES.put("Herlgic", R.drawable.herglic);
		SPECIES.put("H'nemthe", R.drawable.hnemthe);
		SPECIES.put("Human", R.drawable.human);
		SPECIES.put("Hutt", R.drawable.hutt);
		SPECIES.put("Iktotchi", R.drawable.iktotchi);
		SPECIES.put("Ishi Tib", R.drawable.ishitib);
		SPECIES.put("Ithorian", R.drawable.ithorian);
		SPECIES.put("Jawa", R.drawable.jawa);
		SPECIES.put("Kaleesh", R.drawable.kaleesh);
		SPECIES.put("Kaminoan", R.drawable.kaminoan);
		SPECIES.put("Kel Dor", R.drawable.keldor);
		SPECIES.put("Killik", R.drawable.killik);
		SPECIES.put("Kitonak", R.drawable.kintonak);
		SPECIES.put("Klatooinian", R.drawable.klatooinian);
		SPECIES.put("Koorivar", R.drawable.koorivar);
		SPECIES.put("Kowakian Monkey-Lizard", R.drawable.kowakian);
		SPECIES.put("Kubaz", R.drawable.kubaz);
		SPECIES.put("Mon Calamari", R.drawable.moncalamari);
		SPECIES.put("Mrlssi", R.drawable.mrlssi);
		SPECIES.put("Mustafarian", R.drawable.mustafarian);
		SPECIES.put("Mynock", R.drawable.mynock);
		SPECIES.put("Nautolan", R.drawable.nautolan);
		SPECIES.put("Neimoidian", R.drawable.neimoidian);
		SPECIES.put("Nelvaanian", R.drawable.nelvaanian);
		SPECIES.put("Nerf", R.drawable.nerf);
		SPECIES.put("Nexu", R.drawable.nexu);
		SPECIES.put("Nikto", R.drawable.nikto);
		SPECIES.put("Noghri", R.drawable.noghri);
		SPECIES.put("Ortolan", R.drawable.ortolan);
		SPECIES.put("Pa'lowick", R.drawable.palowick);
		SPECIES.put("Pau'an", R.drawable.pauan);
		SPECIES.put("Quarren", R.drawable.quarren);
		SPECIES.put("Quermian", R.drawable.xexto);
		SPECIES.put("Rancor", R.drawable.rancor);
		SPECIES.put("Reek", R.drawable.reek);
		SPECIES.put("Rodian", R.drawable.rodian);
		SPECIES.put("Ryn", R.drawable.ryn);
		SPECIES.put("Selkath", R.drawable.selkath);
		SPECIES.put("Shistavanen", R.drawable.shistavanen);
		SPECIES.put("Ssi-Ruu", R.drawable.ssiruu);
		SPECIES.put("Sullustan", R.drawable.sullustan);
		SPECIES.put("Talz", R.drawable.talz);
		SPECIES.put("Tauntaun", R.drawable.tauntaun);
		SPECIES.put("Thisspiasian", R.drawable.thisspian);
		SPECIES.put("Togruta", R.drawable.togruta);
		SPECIES.put("Toydarian", R.drawable.toydarian);
		SPECIES.put("Trandoshan", R.drawable.trandoshan);
		SPECIES.put("Tusken Raider", R.drawable.tusken);
		SPECIES.put("Twi'lek", R.drawable.twilek);
		SPECIES.put("Ugnaught", R.drawable.ugnaught);
		SPECIES.put("Utai", R.drawable.utai);
		SPECIES.put("Varactyl", R.drawable.varactyl);
		SPECIES.put("Vor", R.drawable.vor);
		SPECIES.put("Wampa", R.drawable.wamp);
		SPECIES.put("Weequay", R.drawable.weequay);
		SPECIES.put("Whiphid", R.drawable.whiphid);
		SPECIES.put("Wookiee", R.drawable.wookie);
		SPECIES.put("Xexto", R.drawable.xexto2);
		SPECIES.put("Yarkora", R.drawable.yarkora);
		SPECIES.put("Yevetha", R.drawable.yevetha);
		SPECIES.put("Yuuzhan Vong", R.drawable.yuuzhanvong);
		SPECIES.put("Zabrak", R.drawable.zabrak);
		SPECIES.put("Zeltron", R.drawable.zeltron);

	}

	public static boolean SpeciesExist(String race) {
		if (SPECIES.containsKey(race)) return true;
		return false;
	}

	public static int getSpeciesIndex(String race) {
		if (SpeciesExist(race) == false)
		return 0;
		
		return SPECIES.get(race);
	}
}
