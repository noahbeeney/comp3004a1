package core;

public class Deck {
	private int index = 0;
	private Card[] deck = new Card[52];
	
	public Deck() {	//Default initializer creates new legal unshuffled deck of 52 cards in ascending order
		for(int i = 0; i < deck.length;) {		//loop through 52 unique cards
			for(int r = 0; r < 13; r++) {		//loop through 13 unique ranks
				for(int s = 0; s < 4; s++) {	//loop through 4 unique suits
					String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
					String[] suits = {"C", "D", "H", "S"};
					
					setCard(i, new Card(suits[s], ranks[r]));
					
					i++;
				}
			}
		}
		//System.out.println(this);
	}
	public Deck(Deck d) { //Copy initializer
		for(int i = 0; i < 52; i++) {
			deck[i] = d.getCard(i);
		}
	}
	
	//Guaranteed to return the next unique card in the deck, ignoring "null" cards
	//Returns "null" when end of deck has been reached
	public Card nextCard() {
		if(index >= 52) {
			return null;
		} else {
			Card nextCard = deck[index];
			index++;
			if(nextCard == null) { //card may have been removed
				return nextCard();
			}
			return nextCard;
		}
	}
	
	//NOTE: these three methods should be used carefully so as not to unintentionally compromise the legality of the deck
	public Card getCard(int i) { //can change card's properties; use with caution
		return deck[i];
	}
	public void setCard(int i, Card c) { //can create duplicate cards; use with caution
		if(i >= 0 && i < 52) {
			deck[i] = c;
		}
		//System.out.println(this);
	}
	public void removeCard(int i) { //leaves "null" hole in deck; use with caution
		deck[i] = null;
		//System.out.println(this);
	}

	//NOTE: Shuffle only works on complete decks. That is, there are no "null" cards in either deck.
	public void shuffle() {
		//System.out.println("Unshuffled: " + this);
		int cardsShuffled = 0;
		Card[] shuffledDeck = new Card[52];
		
		while(cardsShuffled < 52) {
			int rand = (int)(Math.random()*52);
			Card currCard = deck[rand];
			int currPosition = cardsShuffled;
			if(currCard != null) {
				shuffledDeck[currPosition] = currCard;
				this.removeCard(rand);
				cardsShuffled++;
			}
		}
		
		deck = shuffledDeck;
		index = 0;
		//System.out.println("Shuffled:   " + this);
	}
	
	public String toString() {
		String deckString = "";
		
		for(int i = 0; i < 52; i++) {
			if(deck[i] != null) {
				deckString += deck[i] + " ";
			} else {
				deckString += "X" + " ";
			}
		}
		
		return deckString;
	}
	
	public boolean equals(Deck d) {
		for(int i = 0; i < 52; i++) {
			//System.out.print(d.getCard(i).toString() + " == " + deck[i].toString() + " -> ");
			Card card1 = deck[i];
			Card card2 = d.getCard(i);
			
			if(card1 == null && card2 == null) {
				continue;
			} else if (card1 == null || card2 == null) { //one, but not both cards are null
				//System.out.println("FALSE");
				return false;
			}
			
			if(!(d.getCard(i)).equals(deck[i])) {	//compare the two cards
				//System.out.println("FALSE");
				return false;
			}
			//System.out.println("TRUE");
		}
		return true;
	}
}
