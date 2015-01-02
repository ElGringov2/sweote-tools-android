package com.dragonrider.SWEotE.EditorNPC;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Item;
import com.dragonrider.SWEotE.Classes.Skill;
import com.dragonrider.SWEotE.Classes.Weapon;

public class ItemEditor {

	
	public ItemEditor(Context context, Item itemToEdit) {
		this.context = context;
		this.innerItem = itemToEdit;

	}
	
	
	Context context;

	Item innerItem;
	

	private IItemUpdate _update;
	public interface IItemUpdate{
		public void Update(Item EditedItem) ; 
	}
	
	public ItemEditor setUpdater(IItemUpdate updater)
	{
		this._update = updater;
		
		return this;
	}
	
	
	public void show() {
		Builder builder = new Builder(context);
		
		if (innerItem.getClass() == Weapon.class) updateWeaponBuilder(builder);
		
		
		
		
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (innerItem.getClass() == Weapon.class) validateWeapon();
				
				if (_update != null)
					_update.Update(innerItem);
				dialog.dismiss();
			}

		});
		builder.show();
	}
	

	private void validateWeapon() {
		((Weapon)innerItem).Name = ((EditText)rootView.findViewById(R.id.weaponeditor_weaponname)).getText().toString();
		
		int position = ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).getSelectedItemPosition();
        if (position == 0) ((Weapon)innerItem).SkillID = Skill.Skills.brawl.ordinal();
        if (position == 1) ((Weapon)innerItem).SkillID = Skill.Skills.melee.ordinal();
        if (position == 2) ((Weapon)innerItem).SkillID = Skill.Skills.ranged_light.ordinal();
        if (position == 3) ((Weapon)innerItem).SkillID = Skill.Skills.ranged_heavy.ordinal();
        if (position == 4) ((Weapon)innerItem).SkillID = Skill.Skills.gunnery.ordinal();
        if (position == 5) ((Weapon)innerItem).SkillID = Skill.Skills.lightsaber.ordinal();
        
        ((Weapon)innerItem).Damage = Integer.valueOf(((EditText)rootView.findViewById(R.id.weaponeditor_weapondamage)).getText().toString());

	
        ((Weapon)innerItem).CriticalValue = Integer.valueOf(((EditText)rootView.findViewById(R.id.weaponeditor_weaponcritical)).getText().toString());
        
        
        ((Weapon)innerItem).RangeID = ((Spinner)rootView.findViewById(R.id.weaponeditor_weaponsrange)).getSelectedItemPosition();
        
}
	
	
	View rootView;
	private void updateWeaponBuilder(Builder builder) {
		Weapon weapon = (Weapon)innerItem;
		
		
		builder.setTitle(R.string.itemeditor_weapontitle);
		
		rootView = LayoutInflater.from(context).inflate(R.layout.popup_weaponeditor, null);
		
		((EditText)rootView.findViewById(R.id.weaponeditor_weaponname)).setText(weapon.Name);
		
		
		String[] skills = { context.getString(R.string.skill_brawl),
				context.getString(R.string.skill_melee),
				context.getString(R.string.skill_ranged_light),
				context.getString(R.string.skill_ranged_heavy),
				context.getString(R.string.skill_gunnery),
				context.getString(R.string.skill_lightsaber)
				
		};
		
		ArrayAdapter<String> SkillAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, skills);
		
		((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setAdapter(SkillAdapter);
		
		
		int ressource = weapon.SkillID;
		if (ressource == Skill.Skills.brawl.ordinal()) ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setSelection(0);
		if (ressource == Skill.Skills.melee.ordinal()) ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setSelection(1);
		if (ressource == Skill.Skills.ranged_light.ordinal()) ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setSelection(2);
		if (ressource == Skill.Skills.ranged_heavy.ordinal()) ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setSelection(3);
		if (ressource == Skill.Skills.gunnery.ordinal()) ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setSelection(4);
		if (ressource == Skill.Skills.lightsaber.ordinal()) ((Spinner)rootView.findViewById(R.id.weaponeditor_usedskill)).setSelection(5);
		
		
		
		
		((EditText)rootView.findViewById(R.id.weaponeditor_weapondamage)).setText(String.valueOf(weapon.Damage));
		
		
		((EditText)rootView.findViewById(R.id.weaponeditor_weaponcritical)).setText(String.valueOf(weapon.CriticalValue));
		

		
		String[] ranges = { context.getString(R.string.range_engaged),
				context.getString(R.string.range_short),
				context.getString(R.string.range_medium),
				context.getString(R.string.range_long),
				context.getString(R.string.range_extreme)
				
		};
		
		ArrayAdapter<String> RangeAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, ranges);
		
		((Spinner)rootView.findViewById(R.id.weaponeditor_weaponsrange)).setAdapter(RangeAdapter);
		
		((Spinner)rootView.findViewById(R.id.weaponeditor_weaponsrange)).setSelection(weapon.RangeID);

		
		
		
		builder.setView(rootView);

	}
	

	
}
