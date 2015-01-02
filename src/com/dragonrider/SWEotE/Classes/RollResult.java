package com.dragonrider.SWEotE.Classes;


import java.util.Random;

import com.dragonrider.SWEotE.R;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;




public class RollResult implements Cloneable {


    public int DiceAbility;
    public int DiceProficiency;
    public int DiceBoost;
    public int DiceDifficulty;
    public int DiceChallenge;
    public int DiceSetback;
    public int DiceForce;
    public int Seed ;


    private int[] DiceAbilityRoll = new int[30];
    private int[] DiceProficiencyRoll = new int[30];
    private int[] DiceBoostRoll = new int[30];
    private int[] DiceDifficultyRoll = new int[30];
    private int[] DiceChallengeRoll = new int[30];
    private int[] DiceSetbackRoll = new int[30];
    private int[] DiceForceRoll = new int[30];
    
    

    final int DICE_RESULT_BLANK = 0;
    final int DICE_RESULT_SUCCESS = 1;
    final int DICE_RESULT_ADVANTAGE = 2;
    final int DICE_RESULT_ADVANTAGE_SUCCESS = 3;
    final int DICE_RESULT_DOUBLEADVANTAGE = 4;
    final int DICE_RESULT_DOUBLESUCCESS = 5;
    final int DICE_RESULT_TRIUMPH = 6;
    final int DICE_RESULT_FAILURE = 11;
    final int DICE_RESULT_DOUBLEFAILURE = 12;
    final int DICE_RESULT_THREAT = 13;
    final int DICE_RESULT_DOUBLETHREAT = 14;
    final int DICE_RESULT_FAILURETHREAT = 15;
    final int DICE_RESULT_DESPAIR = 16;
    final int DICE_RESULT_LIGHT = 20;
    final int DICE_RESULT_DOUBLELIGHT = 21;
    final int DICE_RESULT_DARK = 22;
    final int DICE_RESULT_DOUBLEDARK = 23;
    		
    
    
	final int[] BOOSTDICE = new int[] { 
			DICE_RESULT_BLANK,
			DICE_RESULT_BLANK,
			DICE_RESULT_SUCCESS,
			DICE_RESULT_ADVANTAGE_SUCCESS,
			DICE_RESULT_DOUBLEADVANTAGE,
			DICE_RESULT_ADVANTAGE };
	final int[] SETBACKDICE = new int[] { 
			DICE_RESULT_BLANK,
			DICE_RESULT_BLANK,
			DICE_RESULT_FAILURE, 
			DICE_RESULT_FAILURE, 
			DICE_RESULT_THREAT,
			DICE_RESULT_THREAT };
	final int[] ABILITYDICE = new int[] { 
			DICE_RESULT_BLANK,
			DICE_RESULT_SUCCESS,
			DICE_RESULT_SUCCESS, 
			DICE_RESULT_DOUBLESUCCESS,
			DICE_RESULT_ADVANTAGE,
			DICE_RESULT_ADVANTAGE, 
			DICE_RESULT_ADVANTAGE_SUCCESS,
			DICE_RESULT_DOUBLEADVANTAGE };
	final int[] DIFFICULTYDICE = new int[] { 
			DICE_RESULT_BLANK,
			DICE_RESULT_FAILURE,
			DICE_RESULT_DOUBLEFAILURE, 
			DICE_RESULT_THREAT,
			DICE_RESULT_THREAT, 
			DICE_RESULT_THREAT, 
			DICE_RESULT_DOUBLETHREAT,
			DICE_RESULT_FAILURETHREAT };
	final int[] PROFICIENCYDICE  = new int[] {
			DICE_RESULT_BLANK,
			DICE_RESULT_SUCCESS,
			DICE_RESULT_SUCCESS,
			DICE_RESULT_DOUBLESUCCESS,
			DICE_RESULT_DOUBLESUCCESS,
			DICE_RESULT_ADVANTAGE,
			DICE_RESULT_ADVANTAGE_SUCCESS,
			DICE_RESULT_ADVANTAGE_SUCCESS,
			DICE_RESULT_ADVANTAGE_SUCCESS,
			DICE_RESULT_DOUBLEADVANTAGE,
			DICE_RESULT_DOUBLEADVANTAGE,
			DICE_RESULT_TRIUMPH
	};
	final int[] CHALLENGEDICE = new int[] {
			DICE_RESULT_BLANK,
			DICE_RESULT_FAILURE,
			DICE_RESULT_FAILURE,
			DICE_RESULT_DOUBLEFAILURE,
			DICE_RESULT_DOUBLEFAILURE,
			DICE_RESULT_THREAT,
			DICE_RESULT_THREAT,
			DICE_RESULT_FAILURETHREAT,
			DICE_RESULT_FAILURETHREAT,
			DICE_RESULT_DOUBLETHREAT,
			DICE_RESULT_DOUBLETHREAT,
			DICE_RESULT_DESPAIR
	};
	final int[] FORCEDICE = new int[] {
			DICE_RESULT_DARK,
			DICE_RESULT_DARK,
			DICE_RESULT_DARK,
			DICE_RESULT_DARK,
			DICE_RESULT_DARK,
			DICE_RESULT_DARK,
			DICE_RESULT_DOUBLEDARK,
			DICE_RESULT_LIGHT,
			DICE_RESULT_LIGHT,
			DICE_RESULT_DOUBLELIGHT,
			DICE_RESULT_DOUBLELIGHT,
			DICE_RESULT_DOUBLELIGHT
	};

	private int getSuccess(int Result) {
		
	    if (Result == DICE_RESULT_BLANK) return 0;
	    if (Result == DICE_RESULT_SUCCESS) return 1;
	    if (Result == DICE_RESULT_ADVANTAGE) return 0;
	    if (Result == DICE_RESULT_ADVANTAGE_SUCCESS) return 1;
	    if (Result == DICE_RESULT_DOUBLEADVANTAGE) return 0;
	    if (Result == DICE_RESULT_DOUBLESUCCESS) return 2;
	    if (Result == DICE_RESULT_TRIUMPH) return 1;
	    if (Result == DICE_RESULT_FAILURE) return -1;
	    if (Result == DICE_RESULT_DOUBLEFAILURE) return -2;
	    if (Result == DICE_RESULT_THREAT) return 0;
	    if (Result == DICE_RESULT_DOUBLETHREAT) return 0;
	    if (Result == DICE_RESULT_FAILURETHREAT) return -1;
	    if (Result == DICE_RESULT_DESPAIR) return -1;
	    if (Result == DICE_RESULT_LIGHT) return 0;
	    if (Result == DICE_RESULT_DOUBLELIGHT) return 0;
	    if (Result == DICE_RESULT_DARK) return 0;
	    if (Result == DICE_RESULT_DOUBLEDARK) return 0;
	    
	    return 0;
	}
	private int getAdvantage(int Result) {
		
	    if (Result == DICE_RESULT_BLANK) return 0;
	    if (Result == DICE_RESULT_SUCCESS) return 0;
	    if (Result == DICE_RESULT_ADVANTAGE) return 1;
	    if (Result == DICE_RESULT_ADVANTAGE_SUCCESS) return 1;
	    if (Result == DICE_RESULT_DOUBLEADVANTAGE) return 2;
	    if (Result == DICE_RESULT_DOUBLESUCCESS) return 0;
	    if (Result == DICE_RESULT_TRIUMPH) return 0;
	    if (Result == DICE_RESULT_FAILURE) return 0;
	    if (Result == DICE_RESULT_DOUBLEFAILURE) return 0;
	    if (Result == DICE_RESULT_THREAT) return -1;
	    if (Result == DICE_RESULT_DOUBLETHREAT) return -2;
	    if (Result == DICE_RESULT_FAILURETHREAT) return -1;
	    if (Result == DICE_RESULT_DESPAIR) return 0;
	    if (Result == DICE_RESULT_LIGHT) return 0;
	    if (Result == DICE_RESULT_DOUBLELIGHT) return 0;
	    if (Result == DICE_RESULT_DARK) return 0;
	    if (Result == DICE_RESULT_DOUBLEDARK) return 0;
	    
	    return 0;
	}
	
	private void checkMax() {
		if (DiceBoost > 30) DiceBoost = 30;
		if (DiceBoost < 0) DiceBoost = 0;
		
		if (DiceAbility > 30) DiceAbility = 30;
		if (DiceAbility < 0) DiceAbility = 0;
		
		if (DiceProficiency > 30) DiceProficiency = 30;
		if (DiceProficiency < 0) DiceProficiency = 0;
		
		if (DiceDifficulty > 30) DiceDifficulty = 30;
		if (DiceDifficulty < 0) DiceDifficulty = 0;
		
		if (DiceChallenge > 30) DiceChallenge = 30;
		if (DiceChallenge < 0) DiceChallenge = 0;
		
		if (DiceSetback > 30) DiceSetback = 30;
		if (DiceSetback < 0) DiceSetback = 0;
		
		if (DiceForce > 30) DiceForce = 30;
		if (DiceForce < 0) DiceForce = 0;
	}
	
	
    private int success()
    {
    	checkMax();
	

        int iResult = 0;

        for (int i = 0; i < DiceBoost; i++)
        {
            iResult += getSuccess(DiceBoostRoll[i]);
        }
        for (int i = 0; i < DiceSetback; i++)
        {
            iResult += getSuccess(DiceSetbackRoll[i]);
        }
        for (int i = 0; i < DiceAbility; i++)
        {
            iResult += getSuccess(DiceAbilityRoll[i]);
        }
        for (int i = 0; i < DiceDifficulty; i++)
        {
            iResult += getSuccess(DiceDifficultyRoll[i]); 
        }
        for (int i = 0; i < DiceProficiency; i++)
        {
            iResult += getSuccess(DiceProficiencyRoll[i]);
        }
        for (int i = 0; i < DiceChallenge; i++)
        {
            iResult += getSuccess(DiceChallengeRoll[i]);
        }


        return iResult;


        
    
    }



    private int advantage()
    {
    	checkMax();

        int iResult = 0;

        for (int i = 0; i < DiceBoost; i++)
        {
            iResult += getAdvantage(DiceBoostRoll[i]);
        }
        for (int i = 0; i < DiceSetback; i++)
        {
            iResult += getAdvantage(DiceSetbackRoll[i]);
        }
        for (int i = 0; i < DiceAbility; i++)
        {
            iResult += getAdvantage(DiceAbilityRoll[i]);
        }
        for (int i = 0; i < DiceDifficulty; i++)
        {
            iResult += getAdvantage(DiceDifficultyRoll[i]); 
        }
        for (int i = 0; i < DiceProficiency; i++)
        {
            iResult += getAdvantage(DiceProficiencyRoll[i]);
        }
        for (int i = 0; i < DiceChallenge; i++)
        {
            iResult += getAdvantage(DiceChallengeRoll[i]);
        }


        return iResult;

        
    }



    public int DiceResult(int position) {
    	if (position < DiceAbility) {
    		return DiceAbilityRoll[position];
    	}
    	position -= DiceAbility;
    	
    	if (position < DiceProficiency) {
    		return DiceProficiencyRoll[position];
    	}
    	position -= DiceProficiency;
    	
    	if (position < DiceDifficulty) {
    		return DiceDifficultyRoll[position];
    	}
    	position -= DiceDifficulty;
    	
    	if (position < DiceChallenge) {
    		return DiceChallengeRoll[position];
    	}
    	position -= DiceChallenge;
    	
    	if (position < DiceBoost) {
    		return DiceBoostRoll[position];
    	}
    	position -= DiceBoost;
    	
    	if (position < DiceSetback) {
    		return DiceSetbackRoll[position];
    	}
    	position -= DiceSetback;
    	
    	
    	if (position < DiceForce) {
    		return DiceForceRoll[position];
    	}

    	
    	
    	return -1;
    }
    
    public static int getResultImageResource(Context context, int Result) {
    	String[] translate = new String[] { "bla", "suc", "adv", "as", "ad2", "su2", "tri", "", "", "", "",
		"fai", "fa2", "thr", "th2", "ft", "des", "", "", "",
		"fo1", "li2", "fo2", "da2"};
    	return context.getResources().getIdentifier(translate[Result], "drawable", context.getPackageName());
		
    }
    
    
    public int Advantage()
    { 
    	if (advantage() > 0) return advantage();
    	else return 0; 
    } 
    public int Threat() { if (advantage() < 0) return -advantage(); else return 0; } 
    public int Succcess()  { if (success() > 0) return success(); else return 0; } 
    public int Failure() { if (success() < 0) return -success(); else return 0; } 
    public int Triumph()
    {

    	checkMax();
        int iResult = 0;

        for (int i = 0; i < DiceProficiency; i++)
            if (DiceProficiencyRoll[i] == DICE_RESULT_TRIUMPH) iResult++;

        return iResult;

        
    }
    public int Despair()
    {
    	checkMax();
        int iResult = 0;

        for (int i = 0; i < DiceChallenge; i++)
            if (DiceChallengeRoll[i] == DICE_RESULT_DESPAIR) iResult++;

        return iResult;

        
    }
    public int LightForcePoint()
    {
    	checkMax();
        int iResult = 0;

        for (int i = 0; i < DiceForce; i++)
        {
            
            if (DiceForceRoll[i] == DICE_RESULT_LIGHT) iResult++;
            if (DiceForceRoll[i] == DICE_RESULT_DOUBLELIGHT) iResult += 2;
        }

        return iResult;

        
    }
    public int DarkForcePoint()
    {
    	checkMax();
        int iResult = 0;

        for (int i = 0; i < DiceForce; i++)
        {
            
            if (DiceForceRoll[i] == DICE_RESULT_DARK) iResult++;
            if (DiceForceRoll[i] == DICE_RESULT_DOUBLEDARK) iResult += 2;
        }

        return iResult;

        
    }
    public int ForcePoint()
    {
    	
        return LightForcePoint() + DarkForcePoint();
        
    }

    public RollResult()
    {

        SetNewSeed();


    }

    public void SetNewSeed()
    {
        Random R = new Random();
        SetSeed(R.nextInt());
    }

    public void SetSeed(int seed)
    {
        this.Seed = seed;
        Random r = new Random(Seed);
        
        for (int dice = 0; dice < 30; dice++)
        {
            DiceAbilityRoll[dice] = ABILITYDICE[r.nextInt(8)];
            DiceBoostRoll[dice] = BOOSTDICE[r.nextInt(6)];
            DiceChallengeRoll[dice] = CHALLENGEDICE[r.nextInt(12)];
            DiceDifficultyRoll[dice] = DIFFICULTYDICE[r.nextInt(8)];
            DiceForceRoll[dice] = FORCEDICE[r.nextInt(12)];
            DiceProficiencyRoll[dice] = PROFICIENCYDICE[r.nextInt(12)];
            DiceSetbackRoll[dice] = SETBACKDICE[r.nextInt(6)];
        }

    }


    public RollResult(int Seed, int Characteristic, int Skill)
    {
        this.Seed = Seed;
        Random r = new Random(Seed);


        for (int dice = 0; dice < 30; dice++)
        {
            DiceAbilityRoll[dice] = ABILITYDICE[r.nextInt(8)];
            DiceBoostRoll[dice] = BOOSTDICE[r.nextInt(6)];
            DiceChallengeRoll[dice] = CHALLENGEDICE[r.nextInt(12)];
            DiceDifficultyRoll[dice] = DIFFICULTYDICE[r.nextInt(8)];
            DiceForceRoll[dice] = FORCEDICE[r.nextInt(12)];
            DiceProficiencyRoll[dice] = PROFICIENCYDICE[r.nextInt(12)];
            DiceSetbackRoll[dice] = SETBACKDICE[r.nextInt(6)];
        }

        DiceAbility = Characteristic;

        while (Skill > 0)
        {
            if (DiceAbility > 0)
            {
                DiceAbility--;
                DiceProficiency++;
            }
            else
                DiceAbility++;

            Skill--;
        }


    }




    public void UpgradePositivePool()
    {
        UpgradePositivePool(1);
    }
    public void UpgradePositivePool(int Amount)
    {
        for (int i = 0; i < Amount; i++)
        {
            if (DiceAbility > 0)
            {
                DiceAbility--;
                DiceProficiency++;
            }
            else
                DiceAbility++;
        }
    }
    public void DowngradePositivePool()
    {
        DowngradePositivePool(1);
    }
    public void DowngradePositivePool(int Amount)
    {
        for (int i = 0; i < Amount; i++)
        {
            if (DiceProficiency > 0)
            {
                DiceProficiency--;
                DiceAbility++;
            }
        }
    }


    public void UpgradeNegativePool()
    {
        UpgradeNegativePool(1);
    }
    public void UpgradeNegativePool(int Amount)
    {
        for (int i = 0; i < Amount; i++)
        {
            if (DiceDifficulty > 0)
            {
                DiceDifficulty--;
                DiceChallenge++;
            }
            else
                DiceDifficulty++;
        }
    }
    public void DowngradeNegativePool()
    {
        DowngradeNegativePool(1);
    }
    public void DowngradeNegativePool(int Amount)
    {
        for (int i = 0; i < Amount; i++)
        {
            if (DiceChallenge > 0)
            {
                DiceChallenge--;
                DiceDifficulty++;
            }
        }
    }
    public void DecreasePositivePool(){
    	DecreasePositivePool(1);
    }
    public void DecreasePositivePool(int Amount){
    	DiceAbility-= Amount;
    	if (DiceAbility < 0) DiceAbility = 0; 
    }
    public void DecreaseNegativePool(){
    	DecreaseNegativePool(1);
    }
    public void DecreaseNegativePool(int Amount){
    	DiceDifficulty-= Amount;
    	if (DiceDifficulty < 0) DiceDifficulty = 0; 
    }
    public void IncreasePositivePool() {
    	
    	DiceAbility++;
    }
    public void IncreasePositivePool(int Amount) {
    	
    	DiceAbility += Amount;
    }
    public void IncreaseNegativePool() {
    	
    	DiceDifficulty++;
    }
    public void IncreaseNegativePool(int Amount) {
    	
    	DiceDifficulty += Amount;
    }
    
    
    
    public void PopulateFullView(Context context, ViewGroup diceGroup) {
    	
    	
    	diceGroup.removeAllViews();
    	
		int iPosition = 0;
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 45);
		
		
    	Integer[] values1 = { DiceAbility, DiceProficiency, DiceDifficulty, DiceChallenge, DiceBoost, DiceSetback, DiceForce };
		
		for (int i = 0; i <= 6; i++)
		{
			int imageID = context.getResources().getIdentifier(String.format("dice_%d", i), "drawable", context.getPackageName());
			for (int iDice = 0; iDice < values1[i]; iDice++) {
				ImageView iv = new ImageView(context);
				iv.setLayoutParams(params);
				iv.setImageResource(imageID);
				iv.setAdjustViewBounds(true);
				diceGroup.addView(iv);
				
				iv = new ImageView(context);
				iv.setLayoutParams(params);
				iv.setAdjustViewBounds(true);
				iv.setImageResource(getResultImageResource(context, DiceResult(iPosition)));
				diceGroup.addView(iv);
				iPosition++;
				
			}
			
			
		}
    }
    
    public void PopulateLightView(Context context, ViewGroup diceGroup) {
    	
    	
    	diceGroup.removeAllViews();
    	

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 45);
		
		
    	Integer[] values1 = { DiceAbility, DiceProficiency, DiceDifficulty, DiceChallenge, DiceBoost, DiceSetback, DiceForce };
		
		for (int i = 0; i <= 6; i++)
		{
			int imageID = context.getResources().getIdentifier(String.format("dice_%d", i), "drawable", context.getPackageName());
			for (int iDice = 0; iDice < values1[i]; iDice++) {
				ImageView iv = new ImageView(context);
				iv.setLayoutParams(params);
				iv.setImageResource(imageID);
				iv.setAdjustViewBounds(true);
				diceGroup.addView(iv);

				
			}
			
			
		}
    }



    public final static String DiceCodeAbility = "[ABILITY]";
    public final static String DiceCodeAdvantage = "[ADVANTAGE]";
    public final static String DiceCodeBoost = "[BOOST]";
    public final static String DiceCodeChallenge = "[CHALLENGE]";
    public final static String DiceCodeDark = "[DARK]";
    public final static String DiceCodeDespair = "[DESPAIR]";
    public final static String DiceCodeDifficulty = "[DIFFICULTY]";
    public final static String DiceCodeFailure = "[FAILURE]";
    public final static String DiceCodeForce = "[FORCE]";
    public final static String DiceCodeLight = "[LIGHT]";
    public final static String DiceCodeProficiency = "[PROFICIENCY]";
    public final static String DiceCodeSetback = "[SETBACK]";
    public final static String DiceCodeSuccess = "[SUCCESS]";
    public final static String DiceCodeThreat = "[THREAT]";
    public final static String DiceCodeTriumph = "[TRIUMPH]";


    public String toSymbolFormattableString() {
    	Integer[] values1 = { DiceAbility, DiceProficiency, DiceDifficulty, DiceChallenge, DiceBoost, DiceSetback, DiceForce };
		String[] values2 = { DiceCodeAbility, DiceCodeProficiency, DiceCodeDifficulty, DiceCodeChallenge, DiceCodeBoost, DiceCodeSetback, DiceCodeForce };
    	
		String finalString = "";
		
		for (int i = 0; i <= 6; i++)
			for (int iDice = 0; iDice < values1[i]; iDice++) {
				finalString += values2[i];

				
			}
			
			
		  
		
		return finalString;
	}
    
    
    public void PopulateResult(Context context, ViewGroup diceGroup) {
    	diceGroup.removeAllViews();
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 45);
		
		
		Integer[] values2 = { Triumph(), Succcess(), Advantage(), Despair(), Failure(), Threat(), DarkForcePoint(), LightForcePoint()};
		Integer[] Ressources = { R.drawable.tri, R.drawable.suc, R.drawable.adv, R.drawable.des, R.drawable.fai, R.drawable.thr, R.drawable.fo2, R.drawable.fo1};
		
		for (int i = 0; i < Ressources.length; i++) {
			for (int iCount = 0; iCount < values2[i]; iCount++) {
				ImageView iv = new ImageView(context);
				iv.setLayoutParams(params);
				iv.setImageResource(Ressources[i]);
				iv.setAdjustViewBounds(true);
				diceGroup.addView(iv);
			}
		}
		
		
    }
    
    public RollResult clone() {

		try {
			return (RollResult) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	
		return null;
    	
    }
    
}


