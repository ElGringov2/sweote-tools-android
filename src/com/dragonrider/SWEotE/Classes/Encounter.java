package com.dragonrider.SWEotE.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Environment;

public class Encounter {

	public String Key;
	public String Name;
	public String Description;
	public String Campaign;
	public String Adventure;
	public String Location;
	public String Notes;
	public String Filename;
	public List<NPC> Fighters;


	private Encounter() {
		Fighters = new ArrayList<NPC>();
	}






	public static Encounter fromFile (String Filename) throws XmlPullParserException, IOException {

		return fromFile(Filename, false);


	}


	private static Encounter fromFile (String Filename, Boolean LightMode) throws XmlPullParserException, IOException {

		Encounter encounter =new Encounter();
		encounter.Filename = Filename;

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();

		xpp.setInput(new FileReader(Filename));

		int eventType = xpp.getEventType();
		String tagName = "";
		String mode = "";

		Database db = new Database(App.getContext());


		NPC someNPC = null;
		int count = 0;

		while (eventType != XmlPullParser.END_DOCUMENT) {
			int depth = xpp.getDepth();
			if (xpp.getName() != null)
				tagName = xpp.getName();


			if (depth == 2 && eventType == XmlPullParser.START_TAG)
				mode = tagName;

			if (tagName.equals("EncGroup") && eventType == XmlPullParser.START_TAG)  {
				someNPC = new NPC();
			}
			if (tagName.equals("EncGroup") && eventType == XmlPullParser.END_TAG)
			{
				for (int i = 0; i < count; i++)
					encounter.Fighters.add(someNPC);
			}


			if (eventType == XmlPullParser.TEXT) {

				String text = xpp.getText().trim();
				if (!text.equals("\n") && !text.isEmpty()) {

					if (mode.equals("Key"))
						encounter.Key = text;
					if (mode.equals("Name"))
						encounter.Name = text;
					if (mode.equals("Description"))
						encounter.Description = text;
					if (mode.equals("Campaign"))
						encounter.Campaign = text;
					if (mode.equals("Adventure"))
						encounter.Adventure = text;
					if (mode.equals("Location"))
						encounter.Location = text;
					if (mode.equals("Notes"))
						encounter.Notes = text;



					if (mode.equals("EncGroups") && !LightMode) {

						if (tagName.equals("AdvKey")) {
							someNPC = db.GetNPCbyKey(text);
							if (someNPC == null)
								someNPC = new NPC();
						}
						if (tagName.equals("AltName"))
							someNPC.Name = text;
						if (tagName.equals("Count"))
							count = Integer.valueOf(text);

					}
				}
			}

			eventType = xpp.next();
		}

		db.close();

		return encounter;
	}

	public void InitializeFight() {

        GroundFightScene.Clear();
		GroundFightScene.AddFighterRange(this.Fighters, true);




	}




	public static List<SWListBoxItem> getAllEncounters() {
		ArrayList<SWListBoxItem> items = new ArrayList<SWListBoxItem>();

		File sdCardRoot = Environment.getExternalStorageDirectory();
		File yourDir = new File(sdCardRoot, "SWEotE/Encounters");

		File[] files = yourDir.listFiles();
		for (File f : files) {
		    if (f.isFile()) {
		    	try {
					Encounter enc = fromFile(f.getPath(), true);
					items.add(new SWListBoxItem(enc.Name, enc.Location).setCategory(enc.Adventure).setTag(enc.Filename));
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (XmlPullParserException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}

		    }
		}


		return items;

	}

	
	
}
