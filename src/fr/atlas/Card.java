package fr.atlas;

public abstract class Card {
	protected abstract String mName();

	protected Card() {
	}

	public abstract void playCard();
}
