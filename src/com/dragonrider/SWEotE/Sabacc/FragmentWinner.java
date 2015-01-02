package com.dragonrider.SWEotE.Sabacc;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;


public class FragmentWinner extends Fragment {

	public static FragmentWinner newInstance() {
		FragmentWinner frag = new FragmentWinner();
		return frag;
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View mainView = inflater.inflate(R.layout.fragment_sabacc_win, null);
		
		
		LinearLayout ll = (LinearLayout) mainView.findViewById(R.id.fragmentWinnerMainLayout);
		for (final SabaccPlayer player : Sabacc.Players) {
			if (player.isFolded()) continue;
			final View playerView = inflater.inflate(R.layout.item_sabaccwin, null);
			
			((TextView)playerView.findViewById(R.id.textView1)).setText(player.getName());
			TextView descTextView = (TextView) playerView.findViewById(R.id.textView2);
			String description = "";
			
			for (String card : Sabacc.getPlayerHand(Sabacc.Players.indexOf(player))) {
				description += card + "<br/>";
			}
			description += String.format("<br/>Score: %s", Sabacc.getScore(Sabacc.Players.indexOf(player)));
			
			descTextView.setText(Html.fromHtml(description));
			
			((Button)playerView.findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					v.setEnabled(false);
					
					Sabacc.DrawCard(Sabacc.Players.indexOf(player));
					TextView descTextView = (TextView) playerView.findViewById(R.id.textView2);
					String description = "";
					
					for (String card : Sabacc.getPlayerHand(Sabacc.Players.indexOf(player))) {
						description += card + "<br/>";
					}
					description += String.format("<br/>Score: %s", Sabacc.getScore(Sabacc.Players.indexOf(player)));
					
					descTextView.setText(Html.fromHtml(description));
					
				}
			});
			
			((Button)playerView.findViewById(R.id.button2)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int amount = Sabacc.HandPot;
					Sabacc.HandPot = 0;
					player.gainCredits(amount);
					((ActivitySabaccGame)getActivity()).NextPlayer();
				}
			});
			
			((Button)playerView.findViewById(R.id.button3)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int amount = Sabacc.HandPot + Sabacc.SabaccPot;
					Sabacc.HandPot = 0;
					Sabacc.SabaccPot = 0;
					player.gainCredits(amount);
					((ActivitySabaccGame)getActivity()).NextPlayer();
				}
			});
			
			ll.addView(playerView);
		}
		
		
		
		
//		TextView mainTextView = (TextView) mainView.findViewById(R.id.fragmentWinnerMainTextView);
//		String sText = "<b>Résultat:</b><br/>";
//
//		
//		ArrayList<SabaccPlayer> winners = new ArrayList<SabaccPlayer>();
//		for (SabaccPlayer sabaccPlayer : Sabacc.Players) {
//			winners.add(sabaccPlayer);
//		}
//
//		
//		Collections.sort(winners, new Comparator<SabaccPlayer>() {
//
//			@Override
//			public int compare(SabaccPlayer lhs, SabaccPlayer rhs) {
//				
//				return ((Integer)lhs.getTotalScore()).compareTo((Integer)rhs.getTotalScore());
//			}
//		});
//		
//		
//		ArrayList<SabaccPlayer> ShortList = new ArrayList<SabaccPlayer>();
//		int TopScore = winners.get(0).getTotalScore();
//		for (SabaccPlayer sabaccPlayer : winners) {
//			if (sabaccPlayer.getTotalScore() == TopScore)
//				ShortList.add(sabaccPlayer);
//		}
//		
//
//		//TODO Traiter la shortlist.
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		List<SabaccPlayer> IdiotWinner = new ArrayList<SabaccPlayer>();
//		
//		for (SabaccPlayer player : Sabacc.Players) {
//			if (player.isFolded()) continue;
//			int PlayerID = Sabacc.Players.indexOf(player);
//			if (Sabacc.hasIdiotArray(PlayerID)){
//				
//				IdiotWinner.add(player);
//			}
//		}
//		
//		if (IdiotWinner.size() == 1) {
//			sText += String.format("<b>Suite de l'Idiot:</b><br/>1 gagnant: %s, qui gagne %d", IdiotWinner.get(0).getName(), Sabacc.SabaccPot + Sabacc.HandPot);
//			IdiotWinner.get(0).gainCredits(Sabacc.SabaccPot + Sabacc.HandPot);
//			Sabacc.SabaccPot = 0;
//			Sabacc.HandPot = 0;
//			
//			
//		}
//		else if (IdiotWinner.size() > 1) {
//			sText += String.format("<b>Suite de l'Idiot:</b><br/>%s gagnants",
//					IdiotWinner.size());
//			
//			
//			
//			
//			for (SabaccPlayer sabaccPlayer : IdiotWinner) {
//				SabaccCard card = Sabacc.DrawCard(Sabacc.Players.indexOf(sabaccPlayer));
//				sText += String.format("<br/>%s, qui tire un %s", sabaccPlayer.getName(), card.toString());
//				sabaccPlayer.setTotalScore(card.getCardValue());
//			}
//			
//			if (IdiotWinner.get(0).getTotalScore() == IdiotWinner.get(1).getTotalScore()) {
//				int iAmount = (Sabacc.SabaccPot + Sabacc.HandPot) / 2;
//				sText += "<br/>Equalité: Chacun remporte " + String.valueOf(iAmount) + "<br/>";
//				Sabacc.SabaccPot = 0;
//				Sabacc.HandPot = 0;
//				IdiotWinner.get(0).gainCredits(iAmount);
//				IdiotWinner.get(1).gainCredits(iAmount);
//			}
//			else {
//				SabaccPlayer winner = IdiotWinner.get(0).getTotalScore() > IdiotWinner.get(1).getTotalScore() ? IdiotWinner.get(0) : IdiotWinner.get(1);
//				sText += String.format("<br/>C'est %s qui gagne %d", winner.getName(), Sabacc.SabaccPot + Sabacc.HandPot);
//				winner.gainCredits(Sabacc.SabaccPot + Sabacc.HandPot);
//				Sabacc.SabaccPot = 0;
//				Sabacc.HandPot = 0;
//			}
//			
//			
//			
//			
//		}
//		else {
//			//TODO Pure Sabacc
//			List<SabaccPlayer> PureSabaccWinner = new ArrayList<SabaccPlayer>();
//			
//			for (SabaccPlayer player : Sabacc.Players) {
//				if (player.isFolded()) continue;
//				int PlayerID = Sabacc.Players.indexOf(player);
//				if (Sabacc.getScore(PlayerID) == 23)
//					PureSabaccWinner.add(player);
//				
//			}
//			
//			if (PureSabaccWinner.size() > 0) {
//				sText += String.format("<br/>Sabacc pur: <br/>");
//				SabaccPlayer winner = PureSabaccWinner.get(0);
//				int iMax = -1;
//				int iPositionMax = -1;
//				if (PureSabaccWinner.size() > 1) {
//					for (SabaccPlayer sabaccPlayer : PureSabaccWinner) {
//						SabaccCard card = Sabacc.DrawCard(Sabacc.Players.indexOf(sabaccPlayer));
//						sText += String.format("<br/>%s, qui tire un %s", sabaccPlayer.getName(), card.toString());
//					}
//					
//				}
//			}
//			else {
//				List<SabaccPlayer> NegativePureSabaccWinner = new ArrayList<SabaccPlayer>();
//				
//				for (SabaccPlayer player : Sabacc.Players) {
//					if (player.isFolded()) continue;
//					int PlayerID = Sabacc.Players.indexOf(player);
//					if (Sabacc.getScore(PlayerID) == 23)
//						NegativePureSabaccWinner.add(player);
//					
//				}
//				
//				if (NegativePureSabaccWinner.size() > 0) {
//					
//				}
//				else {
//					
//					//TODO Gain Standards
//					
//					
//					
//				}
//			}
//			
//			
//		}
//		sText += "<br/><br/>Bombardé:<br/>";
//		for (SabaccPlayer sabaccPlayer : Sabacc.Players) {
//			if (sabaccPlayer.isFolded()) continue;
//			int PlayerID = Sabacc.Players.indexOf(sabaccPlayer);
//			if (Sabacc.getScore(PlayerID) > 23 || Sabacc.getScore(PlayerID) < -23 || Sabacc.getScore(PlayerID) == 0) {
//				sText += String.format("<br/> - %s", sabaccPlayer.getName());
//				sabaccPlayer.betCredits(Sabacc.HandPot);
//				Sabacc.SabaccPot += Sabacc.HandPot;
//			}
//				
//		}
//		
//		
////		
////		
////
////
////	    while (it.hasNext()) {
////	    	Entry<SabaccPlayer, Integer> pairs = (Entry<SabaccPlayer, Integer>)it.next();
////	    	
////	    	SabaccPlayer player = pairs.getKey();
////	    	int indexOf = Sabacc.Players.indexOf(player);
////	    	sText += "<br/><i>" + player.getName() + "</i>";
////	    	
////	    	if (Sabacc.hasIdiotArray(indexOf)) {
////	    		sText += String.format("<br/>Suite de l'Idiot: gagne le pot de donne ainsi que le pot de Sabbac, soit %d crédits.<br/>", Sabacc.SabaccPot + Sabacc.HandPot);
////	    		
////	    	}
////	    	else if (Sabacc.getScore(indexOf) == 23) {
////	    		sText += String.format("<br/>Sabacc pur: gagne le pot de donne ainsi que le pot de Sabbac, soit %d crédits.<br/>", Sabacc.SabaccPot + Sabacc.HandPot);
////	    	}
////	    	else if (Sabacc.getScore(indexOf) == -23) {
////	    		sText += String.format("<br/>Sabacc pur: gagne le pot de donne ainsi que le pot de Sabbac, soit %d crédits.<br/>", Sabacc.SabaccPot + Sabacc.HandPot);
////	    	}
////	    	else if (Sabacc.getScore(indexOf) > 23 || Sabacc.getScore(indexOf) <-23 || Sabacc.getScore(indexOf) == 0){
////	    		sText += String.format("<br/>Bombardé: Doit payer %d crédits au pot de Sabacc.<br/>",Sabacc.HandPot);
////	    	}
////	    	else {
////	    		sText += String.format("<br/>Score: %d<br/>", Sabacc.getScore(indexOf));
////	    	}
////
////	        
////	    }
////	    
//
//	    
//	    
//	
//	    mainTextView.setText(Html.fromHtml(sText));
	    
	    
	    ((Button)mainView.findViewById(R.id.fragmentWinnerMainButton)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				((ActivitySabaccGame)getActivity()).NextPlayer();
				
			}
		});
		
		return mainView;
	}
	
	

}
