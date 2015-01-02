package com.dragonrider.SWEotE.EditorNPC;

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
import com.dragonrider.SWEotE.Classes.Skill;

public class SkillAlertDialog {
	
	NPC npc;
	int skillID;
	
	public interface ISkillUpdate{
		public void UpdateDataSet(int SkillID, int SkillValue);
	}
	ISkillUpdate update;
	public SkillAlertDialog(NPC Npc, int skillID, ISkillUpdate Updater) {
		this.npc = Npc;
		this.skillID = skillID;
		this.update = Updater;
		
	}
	TextView TextValue;
	Spinner SkillSpinner;
	public SkillAlertDialog Show(Context context) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle(R.string.npceditor_editskill);
		
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//npc.SetSkill(AutoText.getText().toString(), Integer.valueOf(TextValue.getText().toString()));
				update.UpdateDataSet(SkillSpinner.getSelectedItemPosition() , Integer.valueOf(TextValue.getText().toString()) );
				dialog.dismiss();
				
				
			}
		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		final View rootView = LayoutInflater.from(context).inflate(R.layout.popup_skilleditor, null);
		
		SkillSpinner = (Spinner)rootView.findViewById(R.id.PopupSkillEditorSkillName);
		ArrayAdapter<String> strings = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, Skill.getAllSkillNames() );
		SkillSpinner.setAdapter(strings);
		
		SkillSpinner.setSelection(skillID);
		
		Skill tempSkill = npc.GetSkill(skillID);
		
		int value = 0;
		if (tempSkill != null)
			value = tempSkill.Value;
		
		
		TextValue = (TextView)rootView.findViewById(R.id.PopupSkillEditorSkillValue);
		
		TextValue.setText(String.valueOf(value));
		

		
		
		
		
		builder.setView(rootView);
		
		builder.show();
		
		return this;
	}

}
