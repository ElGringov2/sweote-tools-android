package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.List;

public class ForcePower {
    public String Name;
    public String Description;
    public String Power;
    public ForceType Type;

    public static ArrayList<ForcePower> getPowersFromDB(String forcePowers) {
        String[] data = forcePowers.split("[*]");

        ArrayList<ForcePower> powers = new ArrayList<ForcePower>();
        for (String s : data)
        {

            if (s.isEmpty()) continue;

            ForcePower pow = ForcePower.fromDatabaseString(s);

            powers.add(pow);





        }
        return powers;
    }

    private String toDatabaseString() {
        if (this.Name == null)
            return "";
        return String.format("%s|%s|%s|%d|%d",
                this.Name,
                this.Description,
                this.Power,
                this.Upgrades,
                this.Type.ordinal());
    }

    public static ForcePower fromDatabaseString(String string) {
        String[] split = string.split("[|]");
        ForcePower pow = new ForcePower();
        pow.Name = split[0];
        pow.Description = split[1];
        pow.Power = split[2];
        pow.Upgrades = Integer.valueOf(split[3]);
        pow.Type = ForceType.values()[Integer.valueOf(split[4])];

        return pow;
    }

    public static String toDatabaseString(List<ForcePower> forcePowers) {
        String string = "";
        for (ForcePower pow : forcePowers)
            string += "*" + pow.toDatabaseString();
        return string;
    }

    public enum ForceType {
        basic,
        control,
        duration,
        range,
        magnitude,
        strength
    }

    public int Upgrades = 0;


}
