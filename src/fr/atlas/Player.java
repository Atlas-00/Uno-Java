package fr.atlas;

import Cards.Card;

import java.util.List;

public class Player {
	private final String mName;
	private final List<Card> mHand;

	public Player(String name, List<Card> hand) {
		this.mName = name;
		this.mHand = hand;
	}

	public String getName() {
		return mName;
	}

	public List<Card> getHand() {
		return mHand;
	}

	public void setNewHand(Card newHand) {
		mHand.add(newHand);
	}

	public void drawCard( Card card) {
		mHand.add(card);
	}
}
