package com.dragonrider.SWEotE.EditorNPC;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.NPC;

public class OldTalentsAlertDialog {
	
	String innerString = "";
	int talentPosition = -1;
	EditText talentEditText;
	
	public interface ITalentUpdate {

		void UpdateDataSet(String TalentString, int Position);
		
	}
	ITalentUpdate update;

	public OldTalentsAlertDialog(NPC npc, int position,
			ITalentUpdate iTalentUpdate) {
		this.update = iTalentUpdate;
		//this.innerString = npc.Talents.get(position);
		this.talentPosition = position;
	}

	public void Show(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		talentEditText = new EditText(context);
		talentEditText.setText(innerString);
		
		builder.setTitle(R.string.npceditor_edittalent);
		
		builder.setView(talentEditText);
		
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				update.UpdateDataSet(talentEditText.getText().toString(), talentPosition);
				
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
