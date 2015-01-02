package com.dragonrider.SWEotE.Sabacc;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Sabacc.FragmentInitialize.IFragmentInitializeCallBack;

public class ActivitySabaccGame extends Activity {
	

	
	/*
	 * 1. définir les jets (chaque joueur décide du jet de dé a utiliser entre Cool, Deceit, Computer, ou Skullduggery)
	 * 2. jet de dé standard.
	 * 
	 */
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTitle(R.string.title_sabacc_game);
		
		setContentView(R.layout.activity_sabacc_game);
		
		
		if (getIntent().getExtras().getBoolean("NEWGAME")) {

			SwitchFragment(FragmentInitialize.newInstance(new IFragmentInitializeCallBack() {
				
				@Override
				public void StartGame() {
					Sabacc.StartGame();
					SwitchFragment(fragmentPlayerTurn.newInstance(Sabacc.getActualPlayer().getName()));
					
				}
			}));
			
		}
		else {
			SwitchFragment(Sabacc.getActualFragment());
		}
		
	}
	

	Fragment actualFragment;
	
	private void SwitchFragment(Fragment frag) {
		actualFragment = frag;
		getFragmentManager().beginTransaction().replace(R.id.sabaccFragmentContenair, actualFragment).commit();
	}

	public void LaunchPlayerTurn() {
		SwitchFragment(Sabacc.getActualFragment());
	}
	
	
	public void ShowNextPhase(String phaseName) {
		SwitchFragment(fragmentPlayerTurn.newInstance(phaseName));
	}
	
	public void NextPlayer() {
		Sabacc.nextPlayer();
		SwitchFragment(fragmentPlayerTurn.newInstance(Sabacc.getActualPlayer().getName()));
	}
	
	
}
