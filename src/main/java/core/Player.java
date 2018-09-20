package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
	private ArrayList<Card> hand;
	private int score;
	
	public Player() {
		hand = new ArrayList<Card>();
		score = 0;
	}
	
	public String showHand() {
		String handStr = "";
		
		for(int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if(c != null) {
				handStr += c.toString() + " ";
			}
		}
		
		return handStr;
	}
	
	public void addCard(Card c) {
		hand.add(c);
	}
	public void addCard(String s, String r) {
		Card c = new Card(s, r);
		hand.add(c);
	}
	
	public void updateScore() {
		int newScore = 0;
		int numAces = 0;
		Map<String, Integer> cardValues = new HashMap<String, Integer>();	//Map card ranks to integer values
		cardValues.put("A", 1);	//Ace defaults to value of 1
		cardValues.put("2", 2);
		cardValues.put("3", 3);
		cardValues.put("4", 4);
		cardValues.put("5", 5);
		cardValues.put("6", 6);
		cardValues.put("7", 7);
		cardValues.put("8", 8);
		cardValues.put("9", 9);
		cardValues.put("10", 10);
		cardValues.put("J", 10);
		cardValues.put("Q", 10);
		cardValues.put("K", 10);
		
		for(int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if(c.getRank() == "A" || c.getRank() == "A ") {
				numAces++;
			}
			newScore += cardValues.get(c.getRank());
		}
		
		for(int i = 0; i < numAces; i++) {	//convert any aces into 10's without exceeding a score of 21
			if(newScore+10 <= 21) {
				newScore += 10;
			} else {
				score = newScore;
				return;
			}
		}
		
		score = newScore;
	}
	public int getScore() {
		updateScore();
		return score;
	}
	
	public void reset() {
		hand.clear();
		score = 0;
	}
}
