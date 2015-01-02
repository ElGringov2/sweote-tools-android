package com.dragonrider.SWEotE.NPCViewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.DiceRoller;
import com.dragonrider.SWEotE.Classes.RollResult;
import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.Item;
import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.Classes.NPC.NPCTypes;
import com.dragonrider.SWEotE.Classes.Skill;
import com.dragonrider.SWEotE.Classes.Talent;
import com.dragonrider.SWEotE.Classes.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowNPCActivity extends Activity {

	
	public static void loadFromNPCName(Context context, String npcName) {
		Intent intent = new Intent(context, ShowNPCActivity.class);
		intent.putExtra("NPCNAME", npcName);
        ShowNPCActivity.StaticNPC = null;
		context.startActivity(intent);
	}
	
	NPC innerNPC;
    public static NPC StaticNPC = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shownpc);
		

        if (StaticNPC == null) {
            Database db = new Database(this);
            innerNPC = db.GetNPCbyName(getIntent().getExtras().getString("NPCNAME"));
            db.close();
        }
        else
        {
            innerNPC = StaticNPC;
        }
		
		((TextView)findViewById(R.id.npcViewerName)).setText(String.format("%s [%s]", innerNPC.Name, innerNPC.Type.toString()).toUpperCase());
		((TextView)findViewById(R.id.npcViewerCategory)).setText(innerNPC.Category);
		
		
		((ImageView)findViewById(R.id.npcViewerImage)).setImageBitmap(innerNPC.getImage());
		
	
		LinearLayout CharValue = (LinearLayout) findViewById(R.id.npcViewerCharacteristicsValue);
		
		for (int i = 0; i < 6; i++) 
			((TextView)CharValue.getChildAt(i)).setText(String.valueOf(innerNPC.Characteristics.get(i)));
			
		
		CharValue = (LinearLayout) findViewById(R.id.npcViewerSecondaryStats);
		((TextView)CharValue.getChildAt(0)).setText(String.valueOf(innerNPC.Soak));
		((TextView)CharValue.getChildAt(1)).setText(String.valueOf(innerNPC.TotalWounds));
		((TextView)CharValue.getChildAt(2)).setText(String.valueOf(innerNPC.TotalStrain));
		((TextView)CharValue.getChildAt(3)).setText(String.valueOf(innerNPC.MeleeDefense));
		((TextView)CharValue.getChildAt(4)).setText(String.valueOf(innerNPC.RangedDefense));
		
		if (innerNPC.Type != NPCTypes.Nemesis) {
			findViewById(R.id.npcViewerStrainImage).setVisibility(View.GONE);
			findViewById(R.id.npcViewerStrainValue).setVisibility(View.GONE);
		}
		
		
		String sHtml = "<b>" + getString(R.string.skills);
		
		
		if(innerNPC.Type == NPCTypes.Minion)
			sHtml += " " + getString(R.string.in_groups);
		sHtml += ":</b>";
		

		
		if (sHtml.endsWith(", "))
			sHtml = sHtml.substring(0, sHtml.length() - 2) + ".";
		
		((TextView)findViewById(R.id.npcViewerSkills)).setText(Html.fromHtml(sHtml));

        LinearLayout skillLayout = (LinearLayout) findViewById(R.id.npcViewerSkillLayout);


        Random rand = new Random();
        for (Skill sk : innerNPC.Skills) {

            final RollResult diffRoll = new RollResult(rand.nextInt(), innerNPC.Characteristics.get(sk.CharacteristicID), sk.Value);
            String text = sk.getName() + ":" + diffRoll.toSymbolFormattableString();
            View rootSkillView = getLayoutInflater().inflate(R.layout.npcviewer_skill_line, skillLayout, false);
            TextView textView = (TextView)rootSkillView.findViewById(R.id.textView);
            Util.setTextViewSymbols(textView, text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DiceRoller(ShowNPCActivity.this).setRollResult(diffRoll).ShowAlertDialog();
                }
            });
            skillLayout.addView(rootSkillView);
        }
		
		String tempString = "";
		for (Talent tal : innerNPC.Talents)
			tempString += tal.toString() + "\n";
		
		if (tempString.length() == 0)
			tempString += getString(R.string.none) + ".";
		else 
			tempString = tempString.substring(0, tempString.length() - 1);
		
		Util.setTextViewSymbols((TextView)findViewById(R.id.npcViewerTalents), tempString);


        //Abilities
		tempString = "";
		for (String str : innerNPC.Abilities)
			tempString += str + "\n";
		if (tempString.length() == 0)
            tempString += getString(R.string.none) + ".";
        else
			tempString = tempString.substring(0, tempString.length() - 1);
		
		Util.setTextViewSymbols((TextView)findViewById(R.id.npcViewerAbilities), tempString);


        //ForcePower
        if (innerNPC.ForcePowers.size() != 0) {
            tempString = "";
            findViewById(R.id.npcViewerForceLayout).setVisibility(View.VISIBLE);
            List<String> forcePowers = innerNPC.getCompiledForcePowers();
            for (String s : forcePowers)
                tempString += s + "\n";

            Util.setTextViewSymbols((TextView)findViewById(R.id.npcViewerForcePower), tempString);
        }


        //Items
		tempString = "";
		for (Item itm : innerNPC.Items)
			tempString += itm.toString() + "\n";
		if (tempString.length() == 0)
			tempString += "Aucun équipement.";
		else 
			tempString = tempString.substring(0, tempString.length() - 1);
		
		Util.setTextViewSymbols((TextView)findViewById(R.id.npcViewerEquipments), tempString);
		
		
		
		((TextView)findViewById(R.id.npcViewerDescription)).setText(innerNPC.Description);
		
	}
}
