package fr.atlas.Cards;

public abstract class Card {
	public abstract void playCard();
	private final String color;

	public Card(String color) {
		this.color = color;
	}
	public String getColor() {
		return color;
	}
}
