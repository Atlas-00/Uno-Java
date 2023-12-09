package fr.atlas;

import fr.atlas.Cards.*;

import java.util.*;

public class PaquetCard implements Deck {
	private final Queue<Card> mPaquet;

	public PaquetCard() {
		mPaquet = new LinkedList<>();
		initializeDeck();
		shuffle();
	}

	private void initializeDeck() {
		// Ajout des cartes d'action
		mPaquet.add(new ActionCard("REVERSE"));
		mPaquet.add(new ActionCard("SKIP"));
		mPaquet.add(new ActionCard("DRAW_TWO"));
		mPaquet.add(new ActionCard("WILD_DRAW_FOUR"));
		mPaquet.add(new ActionCard("WILD"));

		// Ajout des cartes numériques pour chaque couleur
		for (String color : Card.COLORS) {
			for (int value = 0; value <= 9; value++) {
				mPaquet.add(new NumberCard(color, value));
			}
		}
	}

	@Override
	public void shuffle() {
		List<Card> temp = new ArrayList<>(mPaquet);
		Collections.shuffle(temp);
		mPaquet.clear();
		mPaquet.addAll(temp);
	}

	@Override
	public Card drawCard() {
		return mPaquet.poll();
	}
}
