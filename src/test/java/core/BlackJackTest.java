package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

public class BlackJackTest extends TestCase{
	public void testShow() {
		BlackJack game = new BlackJack();
		game.show();
		
		game.init();
		game.show();
	}
	
	public void testFileInit() throws FileNotFoundException {
		BlackJack game = new BlackJack();
		game.show();
		
		Scanner input = new Scanner(new File("src/input.txt"));
		game.init(input);
		game.show();
	}
	
	public void testHit() {
		BlackJack game = new BlackJack();
		game.init();
		int playerScore = game.playerScore();
		game.show();

		game.hit(game.getPlayer(), "D3");
		playerScore += 3;
		game.show();
		assertEquals(playerScore, game.playerScore()); //Will fail occasionally because playerScore variable doesn't normalize aces
		
		game.hit(game.getPlayer(), "H5");
		playerScore += 5;
		game.show();
		assertEquals(playerScore, game.playerScore());
		
		game.hit(game.getPlayer(), "C10");
		playerScore += 10;
		game.show();
		assertEquals(true, game.isOver());
	}
	
	public void testStand() {
		BlackJack game = new BlackJack();
		game.init();
		game.show();
		
		//assertEquals(true, game.isPlayersTurn()); <- can't guarantee this will be true; dealer could get dealt blackjack
		
		while(game.playerScore() < 16) {
			game.hit(game.getPlayer());
		}
		
		game.show();
		game.stand(game.getPlayer());
		System.out.println("Player Stands");
		game.show();
		assertEquals(false, game.isPlayersTurn());
	}
}
