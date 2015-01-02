package com.dragonrider.SWEotE.GroundFightActivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


import com.dragonrider.SWEotE.Classes.Talent;
import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Characteristic.Characteristics;
import com.dragonrider.SWEotE.Classes.DiceRoller;
import com.dragonrider.SWEotE.Classes.DiceRoller.IInterprete;
import com.dragonrider.SWEotE.Classes.DiceRoller.IValidateRoll;
import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.Classes.GroundFighter;
import com.dragonrider.SWEotE.Classes.RollResult;
import com.dragonrider.SWEotE.Classes.Skill.Skills;
import com.dragonrider.SWEotE.Classes.Weapon;

public class GroundFightAttackResolver extends Activity {

	public static void Resolve(Context context, int AttackerID, String targetName) {
		Intent intent = new Intent(context, GroundFightAttackResolver.class);
		intent.putExtra("ATTACKER", AttackerID);
		intent.putExtra("DEFENDER", targetName);
		context.startActivity(intent);
		
	}

    public static void ResolveVersusNPC(Context context, int AttackerID, int targetID) {
        Intent intent = new Intent(context, GroundFightAttackResolver.class);
        intent.putExtra("ATTACKER", AttackerID);
        intent.putExtra("DEFENDERID", targetID);
        context.startActivity(intent);

    }
	
	
	GroundFighter attacker;
	String defender;
    GroundFighter _defender;
    int adversaryCount = 0;
	
	RollResult innerRollResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ground_fight_attack_resolver);
		
		Bundle intentBundle = getIntent().getExtras();
		attacker = GroundFightScene.Fighters.get(intentBundle.getInt("ATTACKER"));

        innerRollResult = attacker.GetSkillRollResult(attacker.getEquippedWeapon().SkillID);
        innerRollResult.DiceDifficulty += 2;
        innerRollResult.DiceBoost = attacker.getAimingBonus();
        attacker.setAimingBonus(0);


        if (getIntent().getExtras().containsKey("DEFENDERID")) {
            _defender = GroundFightScene.Fighters.get(intentBundle.getInt("DEFENDERID"));
            ((TextView)findViewById(R.id.GroundFightResolver_DefenderName)).setText(_defender.getFullName());
            //defense
            Weapon wp = attacker.getEquippedWeapon();
            if (wp.RangeID == 0)
                innerRollResult.DiceSetback = _defender.getBase().MeleeDefense;
            else
                innerRollResult.DiceSetback = _defender.getBase().RangedDefense;


            adversaryCount = _defender.getBase().getTalent(Talent.TalentIDs.adversary);

            findViewById(R.id.GroundFightResolver_NPCDamageLayout).setVisibility(View.VISIBLE);

            findViewById(R.id.GroundFightResolver_NPCDamageButton).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    _defender.DamageUI(GroundFightAttackResolver.this, GroundFightScene.MainAdapter);
                }
            });
            findViewById(R.id.GroundFightResolver_NPCCriticalButton).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    _defender.CriticalUI(GroundFightAttackResolver.this);
                }
            });
            _defender.addDefends(attacker.getFullName());
            attacker.addAttack(_defender.getFullName());
        }
		else {
            defender = intentBundle.getString("DEFENDER");
            ((TextView)findViewById(R.id.GroundFightResolver_DefenderName)).setText(defender);
            attacker.addAttack(defender);

        }
		((TextView)findViewById(R.id.GroundFightResolver_AttackerName)).setText(attacker.Name);
		



		
		RefreshDiceView();
		
		
		findViewById(R.id.GroundFightResolver_Difficulty_one).setOnClickListener(SetDifficultyListener);
		findViewById(R.id.GroundFightResolver_Difficulty_two).setOnClickListener(SetDifficultyListener);
		findViewById(R.id.GroundFightResolver_Difficulty_three).setOnClickListener(SetDifficultyListener);
		findViewById(R.id.GroundFightResolver_Difficulty_four).setOnClickListener(SetDifficultyListener);
		findViewById(R.id.GroundFightResolver_Difficulty_five).setOnClickListener(SetDifficultyListener);
		
		
		findViewById(R.id.GroundFightResolver_EditRollResult).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DiceRoller roller = new DiceRoller(GroundFightAttackResolver.this);
                roller.setRollResult(innerRollResult);
                roller.setValidate(new IValidateRoll() {

                    @Override
                    public void validate(RollResult rr) {
                        innerRollResult = rr;
                        RefreshDiceView();

                    }
                });
                roller.setInterprete(new IInterprete() {

                    @Override
                    public void interprete(RollResult rr, TextView textView) {
                        UpdateTextResult(rr, textView);

                    }
                });
                roller.ShowAlertDialog();

            }
        });
		
		
	}
	
	
	private View.OnClickListener SetDifficultyListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.GroundFightResolver_Difficulty_one) 
				innerRollResult.DiceDifficulty = 1;
			if (v.getId() == R.id.GroundFightResolver_Difficulty_two) 
				innerRollResult.DiceDifficulty = 2;
			if (v.getId() == R.id.GroundFightResolver_Difficulty_three) 
				innerRollResult.DiceDifficulty = 3;
			if (v.getId() == R.id.GroundFightResolver_Difficulty_four) 
				innerRollResult.DiceDifficulty = 4;
			if (v.getId() == R.id.GroundFightResolver_Difficulty_five) 
				innerRollResult.DiceDifficulty = 5;

            innerRollResult.UpgradePositivePool(adversaryCount);
			
			RefreshDiceView();
			
		}
	};

	private void RefreshDiceView() {
		innerRollResult.PopulateFullView(this, ((ViewGroup)findViewById(R.id.GroundFightResolver_AttackResult)));
		innerRollResult.PopulateResult(this, ((ViewGroup)findViewById(R.id.GroundFightResolver_AttackResult2)));
		

		
		
		
		UpdateTextResult(innerRollResult, (TextView)findViewById(R.id.GroundFightResolver_ResultText));
		
	}
	
	private void UpdateTextResult(RollResult rr, TextView tv)  {
		
		Weapon wp = attacker.getEquippedWeapon();
		String textResult = "Attaque echouée";
		
		if (rr.Succcess() > 0) {
			textResult = "Attaque avec " + attacker.getEquippedWeapon().toString();
			textResult += "\n";
			int iAddDamage = rr.Succcess() > 0 ? rr.Succcess() - 1 : 0;
			int iMeleeDamage = 0;
			if (wp.SkillID == Skills.melee.ordinal() || wp.SkillID == Skills.brawl.ordinal())
				iMeleeDamage += attacker.getBase().Characteristics.get(Characteristics.Brawn.ordinal());
				
			
			textResult += String.format("%d dégâts",
					wp.Damage + iAddDamage + iMeleeDamage);

		}
		if (rr.Advantage() > 0) {
			textResult += "\n" + String.format("%d avantages (%d necessaire pour critique)", rr.Advantage(), wp.CriticalValue);
			
		}
		tv.setText(textResult);
	}
	
}
