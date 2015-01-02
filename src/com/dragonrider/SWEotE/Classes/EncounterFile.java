package com.dragonrider.SWEotE.Classes;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class EncounterFile {








    public String Filename;
    public String EncounterName = "";

    public String Description = "";
    public String Campaign = "";
    public String Adventure = "";
    public String Location = "";
    public String Notes = "";

    public List<SimpleEncounterFighter> Fighters;


    public static EncounterFile fromFile(String filename) {
        EncounterFile file ;


        file = XmlImport.ImportEncounterFile(filename);


        if (file != null)
            file.Filename = filename;
        else
            Log.i("hopla", "Erreur sur le fichier " + filename);


        return file;
    }







    public void LaunchFight() {



        for (final SimpleEncounterFighter fighter : this.Fighters) {
            GroundFighter groundFighter = new GroundFighter(GroundFightScene.PlayerFighterCount);
            groundFighter.setBase(fighter.getBaseNPC());
            if (!fighter.Name.isEmpty())
                groundFighter.setName(fighter.Name);
            groundFighter.ActualStrain = fighter.StartingStrain;
            groundFighter.ActualWounds = fighter.StartingWounds;

            int startingWeapon = -1;

            for (Item itm : groundFighter.getBase().Items)
                if (itm.Name.equals(fighter.StartingWeapon))
                    startingWeapon = groundFighter.getBase().Items.indexOf(itm);

            if (startingWeapon != -1)
                groundFighter.setEquippedWeapon(startingWeapon);


            GroundFightScene.AddFighter(groundFighter);
        }

    }



    private String toXml() {
        String sReturn = "<?xml version=\"1.0\"?>\n" +
                "<Encounter xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";


        sReturn += "  <Name>" + this.EncounterName + "</Name>\n";
        sReturn += "  <Description>" + this.Description + "</Description>\n";
        sReturn += "  <Campaign>" + this.Campaign + "</Campaign>\n";
        sReturn += "  <Adventure>" + this.Adventure + "</Adventure>\n";
        sReturn += "  <Location>" + this.Location + "</Location>\n";
        sReturn += "  <Notes>" + this.Location + "</Notes>\n";
        sReturn += "  <EncGroups>\n";


        for (SimpleEncounterFighter fighter : Fighters)
            sReturn += fighter.toXML();

        sReturn += "  </EncGroups>\n";
        sReturn += "</Encounter>";

        return sReturn;
    }


    public void Save() {
        if (Filename == null || Filename.isEmpty())
        {
            if (this.EncounterName == null || this.EncounterName.isEmpty())
                this.EncounterName = "Rencontre";


            File sdCardRoot = Environment.getExternalStorageDirectory();
            File dir = new File(sdCardRoot, "SWEotE/Encounters");
            Filename = dir.getPath() + "/" + this.EncounterName.replaceAll("\\W+", "") + ".xml";




        }

        try {
            FileOutputStream fos = new FileOutputStream(this.Filename, false);

            Log.i("hopla", this.toXml());

            byte[] buffer = this.toXml().getBytes();
            fos.write(buffer);
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void SerialisationSave() {
//        if (Filename == null || Filename.isEmpty())
//        {
//            if (this.EncounterName == null || this.EncounterName.isEmpty())
//                this.EncounterName = "Rencontre";
//
//
//            File sdCardRoot = Environment.getExternalStorageDirectory();
//            File dir = new File(sdCardRoot, "SWEotE/Encounters");
//            Filename = dir.getPath() + "/" + this.EncounterName.replaceAll("\\W+", "");
//
//
//
//        }
//
//        try {
//
//            Log.i("hoplaName", this.Filename);
//            //FileOutputStream fos = App.getContext().openFileOutput(this.Filename, Context.MODE_PRIVATE);
//            FileOutputStream fos = new FileOutputStream(this.Filename, false);
//            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
//            outputStream.writeObject(this);
//            outputStream.close();
//
//
//
//
//
//        } catch (FileNotFoundException e) {
//
//
//
//        } catch (IOException e)
//        {
//
//
//        }
//
//    }

    public String getDescription() {

        String sReturn = "";
        String oldName = "";


        int iCount = 1;
        for (SimpleEncounterFighter npc : Fighters) {
            if (!oldName.equals(npc.Name)) {
                oldName = npc.Name;
                iCount = 1;
                sReturn += String.valueOf(iCount) + "x" + oldName + ", ";
            }
            else
                iCount++;
        }

        return sReturn;

    }
}