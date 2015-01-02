package com.dragonrider.SWEotE.Classes;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;

public class DiceRoller {

	private Context context;
	private RollResult innerRollResult; 
	private IInterprete interprete;
	private IValidateRoll validate;
	
	Builder alertBuilder;
	View baseView;
	
	public interface IInterprete {
		void interprete(RollResult rr, TextView textView);
	}
	public interface IValidateRoll {
		void validate(RollResult rr);
	}

	public void setValidate(IValidateRoll validate) {
		this.validate = validate;
	}



	public DiceRoller(Context context)
	{
		this.context = context;
		alertBuilder = new Builder(context);

		innerRollResult = new RollResult();
		
		baseView = LayoutInflater.from(context).inflate(R.layout.popup_rolldice, null);

		
		baseView.findViewById(R.id.PopupDiceRollerAddDice0).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerAddDice1).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerAddDice2).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerAddDice3).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerAddDice4).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerAddDice5).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerAddDice6).setOnClickListener(ButtonListener);

		baseView.findViewById(R.id.PopupDiceRollerDelDice0).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerDelDice1).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerDelDice2).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerDelDice3).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerDelDice4).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerDelDice5).setOnClickListener(ButtonListener);
		baseView.findViewById(R.id.PopupDiceRollerDelDice6).setOnClickListener(ButtonListener);

		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		Point displayRectangle = new Point();
		
		display.getSize(displayRectangle);
		
		baseView.setMinimumWidth((int)((float)displayRectangle.x * 0.9f));
		baseView.setMinimumHeight((int)((float)displayRectangle.y * 0.9f));
		
		

		alertBuilder.setView(baseView);
		alertBuilder.setTitle(R.string.diceroller);
		alertBuilder.setPositiveButton(R.string.diceroller_validate_roll, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (validate != null)
					validate.validate(innerRollResult);
				dialog.dismiss();
				
			}
		});
		alertBuilder.setNegativeButton(R.string.diceroller_cancel_roll, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});

	}
	
	
	View.OnClickListener ButtonListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.PopupDiceRollerAddDice0)
				innerRollResult.DiceAbility++;
			if (v.getId() == R.id.PopupDiceRollerAddDice1)
				innerRollResult.UpgradePositivePool();
			if (v.getId() == R.id.PopupDiceRollerAddDice2)
				innerRollResult.DiceDifficulty++;
			if (v.getId() == R.id.PopupDiceRollerAddDice3)
				innerRollResult.UpgradeNegativePool();
			if (v.getId() == R.id.PopupDiceRollerAddDice4)
				innerRollResult.DiceBoost++;
			if (v.getId() == R.id.PopupDiceRollerAddDice5)
				innerRollResult.DiceSetback++;
			if (v.getId() == R.id.PopupDiceRollerAddDice6)
				innerRollResult.DiceForce++;
			
			if (v.getId() == R.id.PopupDiceRollerDelDice0 && innerRollResult.DiceAbility > 0)
				innerRollResult.DiceAbility--;
			if (v.getId() == R.id.PopupDiceRollerDelDice1)
				innerRollResult.DowngradePositivePool();
			if (v.getId() == R.id.PopupDiceRollerDelDice2 && innerRollResult.DiceDifficulty > 0)
				innerRollResult.DiceDifficulty--;
			if (v.getId() == R.id.PopupDiceRollerDelDice3)
				innerRollResult.DowngradeNegativePool();
			if (v.getId() == R.id.PopupDiceRollerDelDice4 && innerRollResult.DiceBoost > 0)
				innerRollResult.DiceBoost--;
			if (v.getId() == R.id.PopupDiceRollerDelDice5 && innerRollResult.DiceSetback > 0)
				innerRollResult.DiceSetback--;
			if (v.getId() == R.id.PopupDiceRollerDelDice6 && innerRollResult.DiceForce > 0)
				innerRollResult.DiceForce--;
		
			RefreshMainView();
		}
	};
	
	
	public DiceRoller setRollResult(RollResult newRollResult) {
		this.innerRollResult = newRollResult.clone();
		RefreshMainView();
		return this;
	}
	

	
	protected void RefreshMainView() {

		
		innerRollResult.PopulateFullView(context, (ViewGroup) baseView.findViewById(R.id.PopupDiceRollerListDice));
		
		innerRollResult.PopulateResult(context, (ViewGroup) baseView.findViewById(R.id.PopupDiceRollerListResults));
		

		if (interprete != null)
			interprete.interprete(innerRollResult, (TextView) baseView.findViewById(R.id.PopupDiceRollerInterpreteText));
		
	}



	public DiceRoller setInterprete(IInterprete inteprete) {
		this.interprete = inteprete;
		interprete.interprete(innerRollResult, (TextView) baseView.findViewById(R.id.PopupDiceRollerInterpreteText));
		return this;
	}
	
	public DiceRoller ShowAlertDialog() {
		
		if (interprete == null)
			baseView.findViewById(R.id.PopupDiceRollerInterpreteText).setVisibility(View.GONE);
		
		alertBuilder.show();
		
		return this;
	}
	
	
	
}
