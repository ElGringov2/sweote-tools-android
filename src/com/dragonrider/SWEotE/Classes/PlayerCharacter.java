package com.dragonrider.SWEotE.Classes;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class PlayerCharacter {
	public String CharacterName = "";
	public String PlayerName = "";
	public Boolean Present = true;
    private byte[] image;
//	public String Obligation;
//	public String Career;
//	public String Motivation;
//	public String FalseFrontMotivation;
//	public String Species;
//	public int TotalXP;
//	public int SpendedXP;
//	public int ForceRating;
//	public int[] Characteristics = new int[6];
//	public int ActualWounds = 0;
//	public int TotalWounds = 0;
//	public int ActualStrain = 0;
//	public int TotalStrain = 0;
//	
//	public int ObligationValue = 0;
//	public int DatabaseID = -1;
//
//	public int Credits;
//	public int GroupCredits;
	
	public PlayerCharacter setIsPresent(boolean isPresent) {
		this.Present = isPresent;
		return this;
	}

    private Bitmap Image = null;

    public void setImage(Bitmap b) {
        Image = b;

    }

    public byte[] getImageData() {
        if (this.Image == null) return new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        this.Image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public Bitmap getImage() {

        return Image;
    }



    public void setImage(byte[] data) {
        Image = BitmapFactory.decodeByteArray(data, 0, data.length);

    }
//	
//	
//	public PlayerCharacter setInfos(JSONObject data) {
//		
//		try {
//			CharacterName = data.getString("Name");
//			Obligation = data.getString("Obligation");
//			Career = data.getString("Career");
//			Motivation = data.getString("Motivation");
//			FalseFrontMotivation = data.getString("FalseFrontMotivation");
//			Species = data.getString("Species");
//			TotalXP = data.getInt("TotalXP");
//			SpendedXP = data.getInt("SpendedXP");
//			Characteristics[0] = data.getInt("Brawn");
//			Characteristics[1] = data.getInt("Agility");
//			Characteristics[2] = data.getInt("Intellect");
//			Characteristics[3] = data.getInt("Cunning");
//			Characteristics[4] = data.getInt("Willpower");
//			Characteristics[5] = data.getInt("Presence");
//			TotalStrain = data.getInt("TotalStrain");
//			TotalWounds = data.getInt("TotalWounds");
//			ActualStrain = data.getInt("ActualStrain");
//			ActualWounds = data.getInt("ActualWounds");
//			ForceRating = data.getInt("ForceRating");
//			Credits = data.getInt("credits1");
//			GroupCredits = data.getInt("credits2");
//			DatabaseID = data.getInt("id");
//			Present = data.getInt("IsPresent") == 0 ? false : true;
//			ObligationValue = data.getInt("ObligationValue");
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//
//		return this;
//	}

}
