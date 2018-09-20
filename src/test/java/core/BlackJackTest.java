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
	
	public void testIsOver() {
		BlackJack game = new BlackJack();
		game.init();
	
		game.hit(game.getPlayer(), "C10");
		game.hit(game.getPlayer(), "C10");
		game.hit(game.getPlayer(), "C10");
		assertEquals(true, game.isOver());
	}
	
	public void testHit() {
		BlackJack game = new BlackJack();
		game.init();
		int playerScore = game.playerScore();
		game.show();
		
		game.hit(game.getPlayer(), "DA");
		if(playerScore < 11) {
			playerScore += 11;
			
		} else {
			playerScore += 1;
		}
		game.show();
		assertEquals(playerScore, game.playerScore());
		
		game.hit(game.getPlayer(), "HA");
		if(playerScore < 11) {
			playerScore += 11;
			
		} else {
			playerScore += 1;
		}
		game.show();
		assertEquals(playerScore, game.playerScore());
	}
	
	public void testStand() {
		BlackJack game = new BlackJack();
		game.init();
		game.show();
				
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
