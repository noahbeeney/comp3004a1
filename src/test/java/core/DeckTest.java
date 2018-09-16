package core;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	public void testInit() {	//Makes sure there are 52 cards in the deck in proper order
		int numCards = 0;
		Deck deck = new Deck();
		
		Card card = deck.nextCard();
		Card firstCard = card;
		Card lastCard = card;
		while(card != null) {
			numCards++;
			lastCard = card;
			card = deck.nextCard();
		}
				
		assertEquals("CA", firstCard.toString());
		assertEquals("SK", lastCard.toString());
		assertEquals(52, numCards);
	}
	
	public void testGetCard() {
		Deck deck = new Deck();
		
		assertEquals("CA", deck.getCard(0).toString());
		assertEquals("SK", deck.getCard(51).toString());
	}
	
	public void testSetCard() {
		Deck deck = new Deck();
		
		Card newCard = new Card("S", "A");
		deck.setCard(13, newCard);
		
		assertEquals("SA", deck.getCard(13).toString());
	}
	
	public void testRemoveCard() {
		Deck deck = new Deck();
		
		deck.removeCard(0);
		assertEquals(null, deck.getCard(0));
		assertEquals(deck.getCard(1), deck.nextCard());
	}
	
	public void testEquals() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck(deck1);
		assertEquals(true, deck1.equals(deck2));	//two identical decks
		
		deck2.removeCard(13);
		assertEquals(false, deck1.equals(deck2));	//remove the 14th card in one of the decks
		
		deck2.setCard(13, deck1.getCard(13));
		assertEquals(true, deck1.equals(deck2));	//set the 14th card in the incomplete deck to the 14th card in the complete deck
	}
	
	//Makes sure shuffle generates a random permutation of the deck.
	//NOTE: this test will fail on very rare occasions due to the fact that a shuffle resulting in an identical deck is possible.
	//		this test will also fail if there is a "null" hole in one of the decks. Only complete decks can be shuffled.
	public void testShuffle() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck(deck1);
		assertEquals(true, deck1.equals(deck2));	//two identical decks
		
		deck2.shuffle();
		assertEquals(false, deck1.equals(deck2));	//shuffle one of the decks
		
		deck1.shuffle();
		assertEquals(false, deck1.equals(deck2));	//shuffle the other deck	
	}
}
