package com.dragonrider.SWEotE.EditorNPC;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.NPC;

public class AbilitiesAlertDialog {
	
	String innerString = "";
	int abilityPosition = -1;
	EditText abilityEditText;
	
	public interface IAbilityUpdate {

		void UpdateDataSet(String AbilityString, int Position);
		
	}
	IAbilityUpdate update;

	public AbilitiesAlertDialog(NPC npc, int position,
			IAbilityUpdate iAbilityUpdate) {
		this.update = iAbilityUpdate;
		this.innerString = npc.Abilities.get(position);
		this.abilityPosition = position;
	}

	public void Show(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		abilityEditText = new EditText(context);
		abilityEditText.setText(innerString);
		
		builder.setTitle(R.string.npceditor_editability);
		
		builder.setView(abilityEditText);
		
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				update.UpdateDataSet(abilityEditText.getText().toString(), abilityPosition);
				
			}
		});
		
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		
		builder.show();
		
		
	}

}
