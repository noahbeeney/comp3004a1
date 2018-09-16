package core;

public class Card {
	private String suit;
	private String rank;
	
	public Card() {
		setSuit("X");
		setRank("X");
	}
	
	public Card(String s, String r) {
		setSuit(s);
		setRank(r);
	}
	
	public String toString() {
		return (getSuit() + getRank());
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
