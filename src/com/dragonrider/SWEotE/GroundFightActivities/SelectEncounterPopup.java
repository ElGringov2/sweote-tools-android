package com.dragonrider.SWEotE.GroundFightActivities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Encounter;
import com.dragonrider.SWEotE.Classes.SWGroupListBoxItemAdapter;
import com.dragonrider.SWEotE.Classes.SWListBoxItem;

public class SelectEncounterPopup {

	
	private static AlertDialog dialog;
	
	public static void Show(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		ExpandableListView mainView = new ExpandableListView(context);
		List<SWListBoxItem> data = Encounter.getAllEncounters();
		final SWGroupListBoxItemAdapter adapter = new SWGroupListBoxItemAdapter(context, data);
		mainView.setAdapter(adapter);
		mainView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				try {
					Encounter enc = Encounter.fromFile((String) ((SWListBoxItem)adapter.getChild(groupPosition, childPosition)).getTag());
					enc.InitializeFight();
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (XmlPullParserException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				
				
				dialog.dismiss();
				return true;
			}
		});
		
		
		
		builder.setView(mainView);
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		

		
		
		
		dialog = builder.show();
		
		
	}
	
	
}
