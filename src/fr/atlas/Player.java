package fr.atlas;

import fr.atlas.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private final String mName;
	private final List<Card> mHand;

	public Player(String name) {
		this.mName = name;
		this.mHand = new ArrayList<>();
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
