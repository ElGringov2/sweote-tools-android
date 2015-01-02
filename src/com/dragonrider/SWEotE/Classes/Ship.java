package com.dragonrider.SWEotE.Classes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Ship {
	public String Name= "";
	public String Description = "";
	public String UsedSkill = "";
	public int Silhouette = 1;
	public int TotalHull = 1;
	public int TotalStrain = 1;
	public int Handling = 0;
	public int Pilots = 1;
	public int CoPilots = 0;
	public int AftDefense = 0;
	public int ForwardDefense = 0;
	public int PortDefense = 0;
	public int StarboardDefense = 0;
	public List<MountedWeapon> Weapons = new ArrayList<Ship.MountedWeapon>();
	private Bitmap Image;
	public int Speed =0;
	public int Crew = 0;
	
	

	public class MountedWeapon {
		public final String UsedSkill = "Gunnery";
		public String Name ="";
		public int Damage = 0;
		public int Critical = 0;
		public String Traits ="";
		public String Range = "";

		
		@Override
		public String toString() {
			
			return String.format("%s (D%d C%d %s %s)", Name, Damage, Critical, Range, Traits);
		}
	}



	public Bitmap getImage() {
		return Image;
	}

	public byte[] getImageData() {
		if (this.Image == null) return new byte[0];
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		this.Image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		return stream.toByteArray();
	}

	public void setImage(Bitmap image) {
		Image = image;
	}
	public void setImage(byte[] data) {
		Image = BitmapFactory.decodeByteArray(data, 0, data.length);
	}
	
	public void setImageData(byte[] data)
	{
		Image = BitmapFactory.decodeByteArray(data, 0, data.length); 
	}
	

	public String ToSQLInsertStatement() {
		String SQL = "";
		
		String weapons = "";
		
		SQL = String.format("INSERT INTO SHIPS ('Name', 'Description', 'UsedSkill', 'Silhouette', 'TotalHull', 'TotalStrain', 'Handling', 'Pilots', 'CoPilots', 'AftDefense', 'ForwardDefense', 'PortDefense', 'StarboardDefense', 'Weapons') VALUES ('%s', '%s', '%s', %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, '%s')",
				Name,
				Description,
				UsedSkill,
				Silhouette,
				TotalHull,
				TotalStrain,
				Handling,
				Pilots,
				CoPilots,
				AftDefense,
				ForwardDefense,
				PortDefense,
				StarboardDefense,
				weapons
				);

				
	
		return SQL;
	}


}
