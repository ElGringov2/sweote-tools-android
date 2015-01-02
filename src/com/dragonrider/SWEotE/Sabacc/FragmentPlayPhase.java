package com.dragonrider.SWEotE.Sabacc;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;

public class FragmentPlayPhase extends Fragment {
	
	public static FragmentPlayPhase newInstance() {
		FragmentPlayPhase frag = new FragmentPlayPhase();
		return frag;
	}
	
	private boolean played = false;
	private boolean faceup = false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final View myView = inflater.inflate(R.layout.fragment_sabacc_play, null);
		
		updateListViews(myView);
		
		((Button)myView.findViewById(R.id.fragmentSabaccDrawCard)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Sabacc.DrawCard();
				v.findViewById(R.id.fragmentSabaccDrawCard).setVisibility(View.GONE);
				played = true;
				updateListViews(myView);
				
			}
		});
		
		((Button)myView.findViewById(R.id.fragmentSabaccEndPlayerTurn)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				((ActivitySabaccGame)getActivity()).NextPlayer();

			}
		});


		return myView;
	}


	private void updateListViews(final View v) {
		
		Sabacc.setRollTextView((TextView)v.findViewById(R.id.fragmentSabaccTotalScore));
		((ListView)v.findViewById(R.id.fragmentSabaccCardListView)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sabacc.getPlayerHand(Sabacc.getActualPlayerID())));


		((ListView)v.findViewById(R.id.fragmentSabaccCardListView)).setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				
				if (Sabacc.isFaceUp(position)) return;
				
				Builder builder = new AlertDialog.Builder(getActivity())
				.setTitle("Menu carte")
				.setMessage("Carte:\n" + (String)parent.getItemAtPosition(position));
				
				if (played == false)
					builder.setPositiveButton("Echanger la carte", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Sabacc.ExchangeCard(position);
							played = true;
							updateListViews(v);
							v.findViewById(R.id.fragmentSabaccDrawCard).setVisibility(View.GONE);
							
						}
					});
				if (faceup == false)
				builder.setNegativeButton("Placer la carte face découverte", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Sabacc.Faceup(position);
						updateListViews(v);
					}
				});
				builder.show();
				
			}
		});
		
		((ListView)v.findViewById(R.id.fragmentOtherCardListView)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sabacc.getCardsInfos()));
	}

	
}
