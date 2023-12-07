package fr.atlas;

import fr.atlas.Cards.ActionCard;
import fr.atlas.Cards.Card;
import fr.atlas.Cards.NumberCard;

import java.util.*;

public class PaquetCard implements Deck{
	private Queue<Card> mPaquet;

	public PaquetCard() {
		List<Card> temp = new ArrayList<>();

		ActionCard actionCardReverse = new ActionCard("REVERSE");
		ActionCard actionCardSkip = new ActionCard("SKIP");
		ActionCard actionCardDrawTwo = new ActionCard("DRAW_TWO");
		ActionCard actionCardWildDrawFour = new ActionCard("WILD_DRAW_FOUR");
		ActionCard actionCardWild = new ActionCard("WILD");

		temp.add(actionCardReverse);
		temp.add(actionCardSkip);
		temp.add(actionCardDrawTwo);
		temp.add(actionCardWildDrawFour);
		temp.add(actionCardWild);
		temp.add(new NumberCard());

		this.mPaquet = new PriorityQueue<>();
		Collections.shuffle(temp);
		this.mPaquet.addAll(temp);
	}

	@Override
	public void shuffle() {

	}

	@Override
	public Card drawCard() {
		return null;
	}
}
