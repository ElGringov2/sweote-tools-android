package com.dragonrider.SWEotE.Sabacc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.app.Fragment;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.RollResult;
import com.dragonrider.SWEotE.Classes.Util;

public class Sabacc {
	
	public static int SabaccPot = 0;
	public static int HandPot = 0;
	public static int StartingBet = 1;
	public static int ActualBet = 0;
	public static int MaxBet = 3;
	public static ArrayList<SabaccPlayer> Players = new ArrayList<SabaccPlayer>();
	public static List<SabaccCard> Deck = new ArrayList<SabaccCard>();
	private static int iCardCount = 0;
	
	private static int drawPlayer = -1;
	private static int FirstPlayer = -1;
	private static int actualPlayer = 0;
	
	private static int Phase = 0;
	private static int Turn = 0;
	
	
	private static int Difficulty = 2;

	
	public static void StartGame() {
		
		
		drawPlayer = -1;
		actualPlayer = 0;
		Phase = 0;
		Turn = 0;
		ActualBet = StartingBet;
		
		StartDrawingPhase();
		
	}
	
	
	private static void InitializeDeck() {
		Deck = new ArrayList<SabaccCard>();
		for (int iSuit = 0; iSuit < 4; iSuit++)
			for (int iValue = 1; iValue <= 14; iValue++ )
				Deck.add(new SabaccCard(iSuit, iValue));
		
		Deck.add(new SabaccCard(4, 0));
		Deck.add(new SabaccCard(4, 0));
		
		Deck.add(new SabaccCard(4, -2));
		Deck.add(new SabaccCard(4, -2));
		
		Deck.add(new SabaccCard(4, -8));
		Deck.add(new SabaccCard(4, -8));
		
		Deck.add(new SabaccCard(4, -11));
		Deck.add(new SabaccCard(4, -11));
		
		Deck.add(new SabaccCard(4, -13));
		Deck.add(new SabaccCard(4, -13));
		
		Deck.add(new SabaccCard(4, -14));
		Deck.add(new SabaccCard(4, -14));
		
		Deck.add(new SabaccCard(4, -15));
		Deck.add(new SabaccCard(4, -15));
		
		Deck.add(new SabaccCard(4, -17));
		Deck.add(new SabaccCard(4, -17));
			
				
	}
	
	private static void StartDrawingPhase() {
		InitializeDeck();
		ShuffleDeck();
		
		HandPot = 0;
		drawPlayer++;
		FirstPlayer = drawPlayer;
		
		iCardCount = 0;
		for (SabaccPlayer player : Players) {
			SabaccPot += StartingBet;
			HandPot += StartingBet;
			player.betCredits(StartingBet * 2);
			Deck.get(iCardCount).setOwnedByPlayer(Players.indexOf(player));
			iCardCount++;
			player.setFolded(false);
		}
		for (SabaccPlayer player : Players) {
			Deck.get(iCardCount).setOwnedByPlayer(Players.indexOf(player));
			iCardCount++;
		}
		Turn = 0;
		StartPlayingPhase();
		
	}
	
	
	private static void NextPhase() {
		
		
		actualPlayer = GetFirstPlayerID();
		if (Phase == 0) {
			StartBettingPhase();
			
		}
		else if (Phase == 1) {
			ShiftDeck();
			StartCallPhase();
		}
		else if (Phase == 2) {
			StartPlayingPhase();
		}
		else if (Phase == 3) {
			setWinner();
		}
		else if (Phase == 5) {
			StartDrawingPhase();
		}
	}
	
	
	private static void StartCallPhase() {
		Phase = 2;
		if (Turn == 1) 
			NextPhase();
		
				
	}
	
	private static void StartPlayingPhase() {
		Phase = 0;
		Turn++;
		
		if (Turn > 1)
			FirstPlayer++;
		actualPlayer = FirstPlayer;
		if (FirstPlayer == Players.size())
			FirstPlayer = 0;
		
	}
	
	
	private static int PlayerAlive = 0;
	
	private static void StartBettingPhase() {
		Phase = 1;
		PlayerFollow = 0;
		lastRaiser = "";
		android.util.Log.d("hopla", "Reinit playeralive");
		PlayerAlive = 0;
		for (SabaccPlayer player : Players) {
			if (!player.isFolded())
				PlayerAlive++;
		}
		
		
	}
	
	
	public static Fragment getActualFragment() {
		if (Phase == 0) {
			//playphase
			return FragmentPlayPhase.newInstance();
		}
		if (Phase == 1) {
			//betphase
			return FragmentBetPhase.newInstance();
		}
		if (Phase == 2) {
			//callphase
			return FragmentCallPhase.newInstance();
				
		}
		if (Phase == 3) {
			//betphase after call
			return FragmentBetPhase.newInstance();
		}
		
		if (Phase == 4)
		{
			Phase = 5;
			return FragmentWinner.newInstance();
		}
		
		return null;
		
	}
	

	
	public static void ShuffleDeck() {

		Collections.shuffle(Deck);

	}
	
	
	
	public static void ShiftDeck() {
		Random rand = new Random();
		for (int i = Deck.size() - 1; i > 0; i--) {
			if (rand.nextInt(2) == 1) {
				int j = rand.nextInt(i);
				if (Deck.get(j).isDiscarded || Deck.get(i).isDiscarded)
					continue;
				if (Deck.get(j).isFaceUp() || Deck.get(i).isFaceUp() )
					continue;				
				int value = Deck.get(i).getCardValue();
				int suit = Deck.get(i).getCardSuit();

				Deck.get(i).setCardSuit(Deck.get(j).getCardSuit());
				Deck.get(i).setCardValue(Deck.get(j).getCardValue());

				Deck.get(j).setCardSuit(suit);
				Deck.get(j).setCardValue(value);
			}
			
		}
		
		
	}

	private static int PlayerFollow = 0;

	public static int GetFirstPlayerID() {
		return FirstPlayer;
	}
	
	public static int GetLastPlayerID() {
		if (FirstPlayer == 0)
			return Players.size()-1;
		else return FirstPlayer - 1;
	}
	
	
	
	public static void nextPlayer() {
		
		if (Phase == 0 || Phase == 2) {
			if (actualPlayer == GetLastPlayerID()) {
				NextPhase();
				return;
			}
			else 
				actualPlayer ++;
		}

		else if (Phase == 1 || Phase == 3) {
			if (PlayerFollow == PlayerAlive) {
				NextPhase();
				return;
			}
			else 
				actualPlayer++;
		}
		
		else if (Phase == 4)
			return;
		else if (Phase == 5) {
			NextPhase();
		}
		

		if (actualPlayer == Players.size())
			actualPlayer = 0;
			
		
		if (getActualPlayer().isFolded())
			nextPlayer();
		
		
		
	}

	
	public static List<String> getPlayerHand(int PlayerID) {
		List<String> cards = new ArrayList<String>();
		
		for (SabaccCard sabaccCard : Deck) {
			if (sabaccCard.getOwnedByPlayer() == PlayerID)
				cards.add(sabaccCard.toString());
		}
		
		return cards;
	}

	public static SabaccPlayer getActualPlayer() {
		
		return Players.get(actualPlayer);
	}


	public static int getActualPlayerID() {
		
		return actualPlayer;
	}


	public static List<String> getFaceupCards() {
		List<String> cards = new ArrayList<String>();
		
		for (SabaccCard sabaccCard : Deck) {
			if (sabaccCard.isFaceUp() == true && sabaccCard.isDiscarded == false) {
				String playerName = Players.get(sabaccCard.getOwnedByPlayer()).getName();
				cards.add(playerName + ": " + sabaccCard.toString());
			}
		}
		
		return cards;
	}


	public static SabaccCard DrawCard() {
		Deck.get(iCardCount).setOwnedByPlayer(actualPlayer);
		iCardCount++;
		return Deck.get(iCardCount - 1);
		
	}
	public static SabaccCard DrawCard(int indexOf) {
		Deck.get(iCardCount).setOwnedByPlayer(indexOf);
		iCardCount++;
		
		return Deck.get(iCardCount - 1);
		
	}


	public static void ExchangeCard(int position) {
		
		
		int iCount = -1;
		for (SabaccCard sabaccCard : Deck) {
			
			if (sabaccCard.getOwnedByPlayer() == actualPlayer)
			{
				iCount++;
				if (iCount != position) continue;
				
				sabaccCard.Discard();
				
				DrawCard();
				
				return;
			}
			
		}
		
	}


	public static void Faceup(int position) {
		
		
		int iCount = -1;
		for (SabaccCard sabaccCard : Deck) {
			
			if (sabaccCard.getOwnedByPlayer() == actualPlayer)
			{
				iCount++;
				if (iCount != position) continue;
				
				sabaccCard.setFaceUp(true);
				return;
			}
			
		}
		
	}


	public static boolean isFaceUp(int position) {
		
		int iCount = -1;
		for (SabaccCard sabaccCard : Deck) {
			
			if (sabaccCard.getOwnedByPlayer() == actualPlayer)
			{
				iCount++;
				if (iCount != position) continue;
				

				return sabaccCard.isFaceUp();
			}
			
		}
		return false;
		
	}


	public static int getScore() {
		int iCount = 0;
		for (SabaccCard sabaccCard : Deck) {
			if (sabaccCard.getOwnedByPlayer() == actualPlayer)
				iCount += sabaccCard.getCardValue();
			
		}
		
		return iCount;
	}
	public static int getScore(int PlayerID) {
		int iCount = 0;
		for (SabaccCard sabaccCard : Deck) {
			if (sabaccCard.getOwnedByPlayer() == PlayerID)
				iCount += sabaccCard.getCardValue();
			
		}
		
		return iCount;
	}
	
	private static RollResult addDifficulty(RollResult initialRollResult, boolean Cheating) {
		
		int Score = getScore();
		
		initialRollResult.DiceDifficulty = Difficulty;
		
		initialRollResult.UpgradeNegativePool(getActualPlayer().getCheating());
		if (Cheating)
			initialRollResult.UpgradeNegativePool(1);
		
		if (Score > 23 || Score == 0 || Score < -23)
			initialRollResult.UpgradeNegativePool(2);
		
		if (Score == 23 || Score == -23 || hasIdiotArray())
			initialRollResult.UpgradePositivePool(2);
		
		
		return initialRollResult;
	}
	
	public static RollResult getDifficulty(boolean Cheating) {
		return addDifficulty(new RollResult(), Cheating);
	}
	
	public static void setRollTextView(TextView tv) {
		String desc = "Votre score: " + String.valueOf(getScore());
		if (getScore() > 23 || getScore() == 0 || getScore() < -23) 
			desc += "(Bombardé)";
		
		if (getScore() == 23 || getScore() == -23)
			desc += "(Sabacc pur)";
		
		if (hasIdiotArray())
			desc += "Suite de l'Idiot";

		if (!getActualPlayer().isPC() ) {
		
			RollResult rr = addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getPresence(), getActualPlayer().getCool()), false);
			desc += String.format("\nNormal:\nSang-froid: %s - Succès %d Avantages %d Triomphe %d Desespoir %d", rr.toSymbolFormattableString(), rr.Succcess() - rr.Failure(), rr.Advantage() - rr.Threat(), rr.Triumph(), rr.Despair());
			rr = addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getCunning(), getActualPlayer().getDeceit()), false);
			desc += String.format("\nMensonge: %s - Succès %d Avantages %d Triomphe %d Desespoir %d", rr.toSymbolFormattableString(), rr.Succcess() - rr.Failure(), rr.Advantage() - rr.Threat(), rr.Triumph(), rr.Despair());
			rr = addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getCunning(), getActualPlayer().getSkulduggery()), true);
			desc += String.format("\nTricherie:\nMagouille: %s - Succès %d Avantages %d Triomphe %d Desespoir %d", rr.toSymbolFormattableString(), rr.Succcess() - rr.Failure(), rr.Advantage() - rr.Threat(), rr.Triumph(), rr.Despair());
			rr = addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getIntellect(), getActualPlayer().getComputers()), true);
			desc += String.format("\nInformatique: %s - Succès %d Avantages %d Triomphe %d Desespoir %d", rr.toSymbolFormattableString(), rr.Succcess() - rr.Failure(), rr.Advantage() - rr.Threat(), rr.Triumph(), rr.Despair());
			
		}
		else {
			desc += "\nNormal:\nSang-froid: " + addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getPresence(), getActualPlayer().getCool()), false).toSymbolFormattableString() ;
			desc += "\nMensonge: " + addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getCunning(), getActualPlayer().getDeceit()), false).toSymbolFormattableString() ;
			desc += "\nTricherie:\nMagouille: " + addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getCunning(), getActualPlayer().getSkulduggery()), true).toSymbolFormattableString() ;
			desc += "\nInformatique: " + addDifficulty(new RollResult(new Random().nextInt(), getActualPlayer().getIntellect(), getActualPlayer().getComputers()), true).toSymbolFormattableString() ;

		}
		Util.setTextViewSymbols(tv, desc);
		
		
	}


	public static void PlayerFollow() {
		getActualPlayer().betCredits(ActualBet);
		HandPot += ActualBet;
		PlayerFollow++;
		android.util.Log.d("hopla", String.format("%s follow: %d/%d suivent", getActualPlayer().getName(), PlayerFollow, PlayerAlive));
		
		
	
	}
	public static String getLastRaiser() {
		return lastRaiser;
	}

	private static String lastRaiser = "";
	
	public static void PlayerRaise() {
		ActualBet += MaxBet;
		
		lastRaiser = getActualPlayer().getName();
		
		android.util.Log.d("hopla", String.format("%s raise: reste %d en lice", getActualPlayer().getName(), PlayerAlive));
		
		PlayerFollow = 0;
		PlayerFollow();
	}
	
	public static void PlayerFold() {
	
		getActualPlayer().setFolded(true);
		PlayerAlive--;
		android.util.Log.d("hopla", String.format("%s fold: reste %d en lice", getActualPlayer().getName(), PlayerAlive));
		
		if (PlayerAlive == 1) {
			setWinner();
		}
		
	}
	
	
	
	
	
	private static void setWinner() {
		Phase = 4;
		if (PlayerAlive == 1) { //tout le monde s'est couché
			for (SabaccPlayer player : Players) {
				if (player.isFolded() == false) {
					player.gainCredits(HandPot);
					HandPot = 0;
					
					
					
					break;
				}
			}
		}
		else {
			
			
			
			for (SabaccPlayer player : Players) {
				if (player.isFolded()) 
				{
					player.setTotalScore(-1);
					continue;
				}
			
				int Score = 0;
				
				if (hasIdiotArray(Players.indexOf(player)))
					Score += 100000;
				if (getScore(Players.indexOf(player)) == 23)
					Score += 10000;
				if (getScore(Players.indexOf(player)) == -23)
					Score += 1000;
				
				
				Score += Math.abs(getScore(Players.indexOf(player)) * 10) + (getScore(Players.indexOf(player)) > 0 ? 1 : 0);
				
				player.setTotalScore(Score);
				
				
			}

		}
	}
	

	
	public static boolean hasIdiotArray() {
		return hasIdiotArray(actualPlayer);
	}
	
	public static boolean hasIdiotArray(int PlayerID) {
		boolean zero = false;
		boolean two = false;
		boolean three = false;
		
		for (SabaccCard card : Deck) 
			if (card.getOwnedByPlayer() == PlayerID) {
				if (card.getCardValue() == 0) zero = true;
				if (card.getCardValue() == 2) two = true;
				if (card.getCardValue() == 3) three = true;
				
			}
		
		
		return (zero && two && three);
		
	}
	
	public static List<String> getCardsInfos() {
		List<String> cards = new ArrayList<String>();
		
		for (SabaccCard sabaccCard : Deck) {
			if (sabaccCard.isFaceUp() == true && sabaccCard.isDiscarded == false) {
				String playerName = Players.get(sabaccCard.getOwnedByPlayer()).getName();
				cards.add(playerName + ": " + sabaccCard.toString());
			}
		}
		
		for (SabaccPlayer player : Players) {
			if (player.isFolded())
				cards.add(player.getName() + " s'est couché.");
		}
		
		cards.add(String.format("Pot de donne: %d. Pot de Sabacc: %d. Vos crédits: %d.", Sabacc.HandPot, Sabacc.SabaccPot, Sabacc.getActualPlayer().getCredits()));
		
		return cards;
	}


	public static void Call() {
		if (new Random().nextInt(3) == 2) 
			ShiftDeck();
		
		StartBettingPhase();
		
		Phase = 3;
		PlayerFollow = 1;
		
	}







//	public static List<String> getCalledGamePlayerStatus() {
//		List<String> strings = new ArrayList<String>();
//		for (SabaccPlayer player : Players)
//			if (!player.isFolded())
//				strings.add(String.format("%s (%d) %s", player.getName(), getScore(Players.indexOf(player)), Players.indexOf(player) == actualPlayer ? "(actuel)" : ""));
//
//		return strings;
//	}
//	
	
	
	
}
