package com.dragonrider.SWEotE.Scenario;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ScenarioItem {
    public String Name;
    public String Adventure;
    public String Location;
    public String Campaign;


    public abstract View getView(LayoutInflater inflater, ViewGroup parent);
}
