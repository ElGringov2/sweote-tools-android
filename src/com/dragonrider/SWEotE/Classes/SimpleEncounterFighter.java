package com.dragonrider.SWEotE.Classes;

import java.io.Serializable;

public class SimpleEncounterFighter implements Serializable {
    public String Key;
    public String Name = "";
    public int StartingWounds = 0;
    public int StartingStrain = 0;
    public String StartingWeapon = "";
    public int Count = 1;
    private transient NPC baseNPC;

    public SimpleEncounterFighter setKey(String key) {
        this.Key = key;
        if (this.Name.equals("")) {
            Database db = new Database(App.getContext());
            this.baseNPC = db.GetNPCbyKey(key);
            if (this.baseNPC != null)
                this.Name = this.baseNPC.Name;
            else
                this.Name = "Inconnu";


        }
        return this;
    }


    public SimpleEncounterFighter setBaseNPC(NPC baseNPC) {
        this.baseNPC = baseNPC;
        this.Key = baseNPC.Key;
        this.Name = baseNPC.Name;
        return this;
    }

    public NPC getBaseNPC() {
        return this.baseNPC;
    }


    public String toXML() {
        String sReturn = "    <EncGroup>\n";

        sReturn += "      <AdvKey>" + this.Key + "</AdvKey>\n";
        sReturn += "      <Count>" + String.valueOf(this.Count) + "</Count>\n";
        sReturn += "      <AltName>" + this.Name + "</AltName>\n";
        sReturn += "      <StartingWeapon>" + this.StartingWeapon + "</StartingWeapon>\n";
        sReturn += "      <StartingWounds>" + String.valueOf(this.StartingWounds) + "</StartingWounds>\n";
        sReturn += "      <StartingStrain>" + String.valueOf(this.StartingStrain) + "</StartingStrain>\n";
        sReturn += "    </EncGroup>\n";

        return sReturn;
    }

    public void setAltName(String s) {
        this.Name = s;
    }
}