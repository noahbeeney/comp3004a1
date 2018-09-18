package core;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	
	public void testAddCard() {	//Also tests showHand()
		Player player = new Player();
		assertEquals("", player.showHand());
		
		player.addCard("H", "A");
		assertEquals("HA ", player.showHand());
		
		player.addCard("S", "A");
		assertEquals("HA SA ", player.showHand());
		
		player.addCard("C", "8");
		assertEquals("HA SA C8 ", player.showHand());

		player.addCard("C", "10");
		assertEquals("HA SA C8 C10 ", player.showHand());
	}
	
	public void testGetScore() {	//Also tests reset()
		Player player = new Player();
		assertEquals(0, player.getScore());	//player has a score of 0
		
		Card card = new Card("H", "2");
		player.addCard(card);
		assertEquals(2, player.getScore());	//player has a score of 2
		
		player.reset();
		assertEquals(0, player.getScore());	//player has a score of 0
		
		player.addCard("C", "A");
		assertEquals(11, player.getScore());//player has a score of 11: {A=11}
		
		player.addCard("H", "A");
		assertEquals(12, player.getScore());//player has a score of 12: {A=11, A=1}
		
		player.addCard("S", "A");
		assertEquals(13, player.getScore());//player has a score of 13: {A=11, A=1, A=1}
		
		player.addCard("C", "8");
		assertEquals(21, player.getScore());//Player has a score of 21: {A=11, A=1, A=1, 8}
		
		player.addCard("D", "A");
		assertEquals(12, player.getScore());//Player has a score of 12: {A=1, A=1, A=1, 8, A=1}
		
		player.addCard("C", "10");
		assertEquals(22, player.getScore());//Player has a score of 22: {A=1, A=1, A=1, 8, A=1, 10}
	}
}
