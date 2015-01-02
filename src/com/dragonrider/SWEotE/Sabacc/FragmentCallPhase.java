package com.dragonrider.SWEotE.Sabacc;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;

public class FragmentCallPhase extends Fragment {
	
	public static FragmentCallPhase newInstance() {
		FragmentCallPhase frag = new FragmentCallPhase();
		return frag;
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sabacc_play, null);
		

		Sabacc.setRollTextView((TextView)view.findViewById(R.id.fragmentSabaccTotalScore));
		((ListView)view.findViewById(R.id.fragmentSabaccCardListView)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sabacc.getPlayerHand(Sabacc.getActualPlayerID())));
		((ListView)view.findViewById(R.id.fragmentOtherCardListView)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sabacc.getCardsInfos()));
		
		
		((Button)view.findViewById(R.id.fragmentSabaccDrawCard)).setText("Appeler (jet réussi)");
		((Button)view.findViewById(R.id.fragmentSabaccDrawCard)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Sabacc.Call();
				((ActivitySabaccGame)getActivity()).NextPlayer();
				
			}
		});
		((Button)view.findViewById(R.id.fragmentSabaccEndPlayerTurn)).setText("Passer (jet réussi ou echoué)");
		((Button)view.findViewById(R.id.fragmentSabaccEndPlayerTurn)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((ActivitySabaccGame)getActivity()).NextPlayer();
				
			}
		});
		
		
		return view;
	}
}
