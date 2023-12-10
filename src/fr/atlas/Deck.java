package fr.atlas;

import fr.atlas.Cards.Card;

interface Deck {
	void shuffle();
	Card drawCard();
}
