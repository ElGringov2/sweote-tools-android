package com.dragonrider.SWEotE.EditorNPC;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.Classes.Talent;
import com.dragonrider.SWEotE.Classes.Talent.TalentIDs;

public class TalentsAlertDialog {
	
	NPC npc;
	int position;
	
	public interface ITalentUpdate{
		public void UpdateDataSet(int TalentID, int TalentValue);
	}
	ITalentUpdate update;
	public TalentsAlertDialog(NPC Npc, int position, ITalentUpdate Updater) {
		this.npc = Npc;
		this.position = position;
		this.update = Updater;
		
	}
	TextView TextValue;
	Spinner TalentSpinner;
	public TalentsAlertDialog Show(Context context) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle(R.string.npceditor_edittalent);
		
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//npc.SetTalent(AutoText.getText().toString(), Integer.valueOf(TextValue.getText().toString()));
				//npc.Talents.get(TalentID).setTalentValue(Integer.valueOf(TextValue.getText().toString())
				update.UpdateDataSet(TalentSpinner.getSelectedItemPosition() , Integer.valueOf(TextValue.getText().toString()) );
				dialog.dismiss();
				
				
			}
		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		final View rootView = LayoutInflater.from(context).inflate(R.layout.popup_talent, null);
		
		TalentSpinner = (Spinner)rootView.findViewById(R.id.PopupTalentEditorTalentName);
		List<String> talentList = new ArrayList<String>();
		for (TalentIDs talent : Talent.TalentIDs.values()) {
			talentList.add(Talent.GetTalentName(talent.ordinal()));
		}
		
		ArrayAdapter<String> strings = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, talentList );
		TalentSpinner.setAdapter(strings);
		
		
		Talent tempTalent = npc.Talents.get(position);
		TalentSpinner.setSelection(tempTalent.TalentID);
		
		
		int value = 0;
		if (tempTalent != null)
			value = tempTalent.TalentValue;
		
		
		TextValue = (TextView)rootView.findViewById(R.id.PopupTalentEditorTalentValue);
		
		TextValue.setText(String.valueOf(value));
		

		
		
		
		
		builder.setView(rootView);
		
		builder.show();
		
		return this;
	}

}
