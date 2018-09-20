package core;

import java.util.Scanner;

public class BlackJack {
	private boolean playersTurn;
	private Player player;
	private Player dealer;
	private Deck deck;
	private Player winner;

	public BlackJack() {
		player = new Player();
		dealer = new Player();
		playersTurn = true;
	}

	public int playerScore() {
		return player.getScore();
	}

	public int dealerScore() {
		return dealer.getScore();
	}

	public Player getPlayer() {
		return player;
	}

	public Player getDealer() {
		return dealer;
	}

	public void hit(Player p) {
		p.addCard(deck.nextCard());

		if (p.getScore() > 21) { // if bust player's turn is over
			playersTurn = false;
		}
	}

	public void hit(Player p, String card) { // hits player with new Card with string representation of provided card:
												// "SR"
		Card c = new Card();
		c.setSuit(Character.toString((card.charAt(0))));
		c.setRank(card.substring(1));

		p.addCard(c);

		if (p.getScore() >= 21) {
			playersTurn = false;
		}
	}

	public void stand(Player p) {
		if (p.equals(player)) {
			playersTurn = false;
		}
	}

	public void init() {
		deck = new Deck();
		deck.shuffle();

		player.addCard(deck.nextCard());
		player.addCard(deck.nextCard());
		dealer.addCard(deck.nextCard());
		dealer.addCard(deck.nextCard());
	}

	public void init(Scanner inputFile) {
		for (int i = 0; i < 2; i++) { // deal player first and second cards in file
			String nextCard = inputFile.next();
			if (nextCard == null) {
				System.out.println("Deck has been exhausted.");
				return;
			}
//			
//			System.out.println("OVER HERE!");
//			System.out.println(nextCard);

			Card c = new Card();
			c.setSuit(Character.toString((nextCard.charAt(0))));
			c.setRank(nextCard.substring(1));

			player.addCard(c);
		}
		for (int i = 0; i < 2; i++) { // deal dealer third and fourth cards in file
			String nextCard = inputFile.next();
			if (nextCard == null) {
				System.out.println("Deck has been exhausted.");
				return;
			}

			// System.out.println(nextCard);

			Card c = new Card();
			c.setSuit(Character.toString((nextCard.charAt(0))));
			c.setRank(nextCard.substring(1));

			dealer.addCard(c);
		}
	}

	public boolean isOver() {
		if (player.getScore() > 21) {
			winner = dealer;
			return true;
		} else if (dealer.getScore() > 21) {
			winner = player;
			return true;
		} else if (!playersTurn && dealer.getScore() >= player.getScore()) {
			winner = dealer;
			return true;
		} else if (!playersTurn && dealer.getScore() > 16 && !dealerHasSoft17()) {
			if (dealer.getScore() < player.getScore()) {
				winner = player;
			} else {
				winner = dealer;
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean isPlayersTurn() {
		return playersTurn;
	}

	public void show() {
		String gameStr = "\n";

		if (playersTurn) {
			gameStr += "--- PLAYER'S TURN ---\n\n";
		} else {
			gameStr += "--- DEALER'S TURN ---\n\n";
		}

		gameStr += "Player's hand: " + player.showHand() + "-> " + player.getScore() + "\n";
		gameStr += "Dealer's hand: ";
		if (dealer.showHand().length() > 1) {
			gameStr += dealer.showHand().substring(0, 3);
		}

		if (!playersTurn) {
			gameStr += dealer.showHand().substring(3) + "-> " + dealer.getScore() + "\n";
		} else {
			gameStr += " ?? -> ??\n\n";
		}

		if (!playersTurn && this.isOver() && dealerScore() > 16 && !dealerHasSoft17()) {
			if(dealerScore() <= 21) {
				gameStr += "\nDealer stands.\n";
			}
			
			if (winner.equals(getDealer())) {
				gameStr += "\nDEALER WINS";
			} else {
				gameStr += "\nPLAYER WINS";
			}
		}

		System.out.println(gameStr);
	}

	public boolean dealerHasSoft17() {
		if (dealer.getScore() == 17) {
			if (dealer.showHand().contains("HA")) {
				return true;
			}
			if (dealer.showHand().contains("CA")) {
				return true;
			}
			if (dealer.showHand().contains("DA")) {
				return true;
			}
			if (dealer.showHand().contains("SA")) {
				return true;
			}
		}
		return false;
	}

}
