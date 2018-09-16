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
}
