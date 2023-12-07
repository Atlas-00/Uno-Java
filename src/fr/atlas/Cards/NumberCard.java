package fr.atlas.Cards;

public class NumberCard extends Card {
	private final int mValue;
	public NumberCard(String color, int value) {
		super(color);
		this.mValue = value;
	}

	public int getValue() {
		return mValue;
	}

	public void cardGenerations() {

	}

	@Override
	public void playCard() {

	}
}
