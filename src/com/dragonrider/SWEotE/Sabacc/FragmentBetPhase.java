package com.dragonrider.SWEotE.Sabacc;

import com.dragonrider.SWEotE.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentBetPhase extends Fragment {

	public static FragmentBetPhase newInstance() {
		
		FragmentBetPhase frag = new FragmentBetPhase();
		return frag;
		
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final View myView = inflater.inflate(R.layout.fragment_sabaccbet, null);
		
		updateListViews(myView);
		((Button)myView.findViewById(R.id.fragmentSabaccFollow)).setText(String.format("Suivre %s (%d crédits)", Sabacc.getLastRaiser(), Sabacc.ActualBet));
		((Button)myView.findViewById(R.id.fragmentSabaccFollow)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Sabacc.PlayerFollow();

				((ActivitySabaccGame)getActivity()).NextPlayer();
				
			}
		});
		
		((Button)myView.findViewById(R.id.fragmentSabaccRaise)).setText(String.format("Relancer (%d crédits) (nécessite au moins une réussite)", Sabacc.ActualBet + Sabacc.MaxBet));
		((Button)myView.findViewById(R.id.fragmentSabaccRaise)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Sabacc.PlayerRaise();
				((ActivitySabaccGame)getActivity()).NextPlayer();
				
			}
		});
		((Button)myView.findViewById(R.id.fragmentSabaccFold)).setText(String.format("Se coucher (Obligatoire si > 2 echecs)", Sabacc.ActualBet + Sabacc.MaxBet));

		((Button)myView.findViewById(R.id.fragmentSabaccFold)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Sabacc.PlayerFold();
				((ActivitySabaccGame)getActivity()).NextPlayer();
				
			}
		});
		
		return myView;
	}
	
	
	private void updateListViews(final View v) {

		Sabacc.setRollTextView((TextView)v.findViewById(R.id.fragmentSabaccTotalScore));
		((ListView)v.findViewById(R.id.fragmentSabaccCardListView)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sabacc.getPlayerHand(Sabacc.getActualPlayerID())));
		((ListView)v.findViewById(R.id.fragmentOtherCardListView)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sabacc.getCardsInfos()));
	}
}
