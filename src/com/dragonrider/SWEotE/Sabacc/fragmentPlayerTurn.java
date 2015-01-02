package com.dragonrider.SWEotE.Sabacc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.dragonrider.SWEotE.R;

public class fragmentPlayerTurn extends Fragment{
	
	public static fragmentPlayerTurn newInstance(String playerName) {
		fragmentPlayerTurn frag = new fragmentPlayerTurn();
		Bundle bundle = new Bundle();
		bundle.putString("PLAYERNAME", playerName);
		frag.setArguments(bundle);
		
		return frag;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View returnedView = inflater.inflate(R.layout.fragment_sabacc_playerturn, null);
		
		((Button)returnedView).setText(getArguments().getString("PLAYERNAME"));
		((Button)returnedView).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((ActivitySabaccGame)getActivity()).LaunchPlayerTurn();
				
			}
		});
		
		return returnedView;
	}
	
}
