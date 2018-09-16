package core;

import junit.framework.TestCase;

public class CardTest extends TestCase{
	public void testDefaultInit() {
		Card card = new Card();
		
		assertEquals("X", card.getSuit());
		assertEquals("X", card.getRank());
	}
	
	public void testCustomInit() {
		Card sa = new Card("S", "A");
		
		assertEquals("S", sa.getSuit());
		assertEquals("A", sa.getRank());
		assertEquals("SA", sa.toString());
	}
	
	public void testSetCard() {
		Card card = new Card();
		
		card.setSuit("S");
		card.setRank("A");
		
		assertEquals("S", card.getSuit());
		assertEquals("A", card.getRank());
		assertEquals("SA", card.toString());
	}
	
	public void testEquals() {
		Card card1 = new Card("S", "A");
		Card card2 = new Card ("C", "K");
		assertEquals(false, card1.equals(card2));
		
		card1.setSuit(card2.getSuit());
		card1.setRank(card2.getRank());
		assertEquals(true, card1.equals(card2));
		
		Card card3 = new Card("C", "K");
		assertEquals(true, card1.equals(card3));
	}
}
