package com.dragonrider.SWEotE.Sabacc;





public class SabaccCard {

	private int OwnedByPlayer = -1;
	
	
	
		public SabaccCard(int Suit, int Value) {
			this.CardSuit = Suit;
			this.CardValue = Value;
		}
		
		private int CardValue = 0;

		public int getCardValue() {
			return CardValue;
		}


		public void setCardValue(int cardValue) {
			CardValue = cardValue;
		}


		public int getCardSuit() {
			return CardSuit;
		}


		public void setCardSuit(int cardSuit) {
			CardSuit = cardSuit;
		}

		private int CardSuit = 0;
		
		@Override
		public String toString() {
			String str = "";
			

			if (CardValue < 12)
				str += String.valueOf(CardValue);
			else if (CardValue == 12)
				str += "Commandant";
			else if (CardValue == 13)
				str += "Maitresse";
			else if (CardValue == 14)
				str += "Maitre";
			else if (CardValue == 15)
				str += "As";			


			if (CardSuit == 0)
				str += " d'Epée";
			else if (CardSuit == 1)
				str += " de Baton";
			else if (CardSuit == 2)
				str += " de Fiole";
			else if (CardSuit == 3)
				str += " de Monnaie";			
			else if (CardSuit == 4) {
				if (CardValue == 0)
					str = "L'Idiot (0)";
				if (CardValue == -2)
					str = "La Reine de l'Air et des Ténèbres (-2)";
				if (CardValue == -8)
					str = "Endurance (-8)";
				if (CardValue == -11)
					str = "La Balance (-11)";
				if (CardValue == -13)
					str = "La Mort (-13)";
				if (CardValue == -14)
					str = "Modération (-14)";
				if (CardValue == -15)
					str = "Le Malin (-15)";
				if (CardValue == -17)
					str = "L'Etoile (-17)";
			}
				
			if (isFaceUp)
				str += "*";
			
			return str;
		}
		
		
		public int getOwnedByPlayer() {
			return OwnedByPlayer;
		}


		public void setOwnedByPlayer(int ownedByPlayer) {
			if (isDiscarded) OwnedByPlayer = -1;
			else OwnedByPlayer = ownedByPlayer;
		}

		public boolean isFaceUp() {
			return isFaceUp;
		}


		public void setFaceUp(boolean isFaceUp) {
			this.isFaceUp = isFaceUp;
		}

		public boolean isDiscarded = false;
		private boolean isFaceUp = false;



		public void Discard() {
			isDiscarded = true;
			OwnedByPlayer = -1;
		}
		
		
	
	}