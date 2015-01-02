package com.dragonrider.SWEotE.Scenario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.dragonrider.SWEotE.Classes.EncounterFile;

import com.dragonrider.SWEotE.GroundFightActivities.GroundFightMultiPanelActivity;
import com.dragonrider.SWEotE.R;


public class FightScenarioItem extends ScenarioItem {

    public String encounterFilename;


    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View convertView = inflater.inflate(R.layout.scenarioitem_fight, parent, false);

        final Context context = convertView.getContext();

        convertView.findViewById(R.id.ImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EncounterFile encounterFile = EncounterFile.fromFile(encounterFilename);
                encounterFile.LaunchFight();
                GroundFightMultiPanelActivity.StartFight(context, false, true, false);
            }
        });

        ((TextView)convertView.findViewById(R.id.textView)).setText(Name);


        return convertView;
    }
}
