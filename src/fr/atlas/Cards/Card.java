package fr.atlas.Cards;

public abstract class Card {
	public static final String[] COLORS = {"ROUGE", "BLEU", "VERT", "JAUNE"};

	private final String mColor;

	public Card( String color ) {
		this.mColor = color;
	}

	public String getColor() {
		return mColor;
	}

	public abstract void playCard();
}
