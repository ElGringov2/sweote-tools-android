package com.dragonrider.SWEotE;



public class MainMenuItem {
    private String name;
    private String description;
    private MainActivity.IMainMenuItemAction action;
    private MainActivity.IMainMenuEnableCheck check;



    public String getName() {
        return name;
    }

    public MainMenuItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MainMenuItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public MainActivity.IMainMenuItemAction getAction() {
        return action;
    }

    public MainMenuItem setAction(MainActivity.IMainMenuItemAction action) {
        this.action = action;
        return this;
    }

    private int SpecialID = -1;

    private int getSpecialID() {
        return SpecialID;
    }

    private MainMenuItem setSpecialID(int specialID) {
        SpecialID = specialID;
        return this;
    }



    public static MainMenuItem PrepareCombatSpecialMenuItem () {
        return new MainMenuItem().setSpecialID(1);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != MainMenuItem.class) return false;

        if (((MainMenuItem)o).getSpecialID() != -1 &&
                ((MainMenuItem)o).getSpecialID() == this.getSpecialID()) return true;

        return super.equals(o);
    }

    public MainMenuItem setCheck(MainActivity.IMainMenuEnableCheck check) {
        this.check = check;
        return this;
    }

    public boolean getIsEnabled() {
        if (check == null) return true;
        return check.Check();

    }
}