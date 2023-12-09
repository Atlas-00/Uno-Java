package fr.atlas.Cards;

import java.util.ArrayList;
import java.util.List;

public class NumberCard extends Card {
	private final int mValue;
	private final List<Card> mNumberCard;

	public NumberCard(String color, int value) {
		super(color);
		this.mValue = value;
		this.mNumberCard = new ArrayList<Card>();
	}

	public int getValue() {
		return mValue;
	}

	@Override
	public void playCard() {

	}

	@Override
    public String toString() {
        return "Value = " + getValue() + ", Color = " + getColor();
    }
}
