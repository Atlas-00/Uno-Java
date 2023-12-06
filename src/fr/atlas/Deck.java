package fr.atlas;

import Cards.Card;

interface Deck {
	void shuffle();
	Card drawCard();
}
