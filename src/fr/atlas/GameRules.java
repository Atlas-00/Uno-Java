package fr.atlas;

import fr.atlas.Cards.ActionCard;
import fr.atlas.Cards.Card;
import fr.atlas.Cards.NumberCard;

public class GameRules {

	public static boolean isPlayable( Card currentCardInPlay, Card drawnCard ) {
		if (currentCardInPlay instanceof NumberCard && drawnCard instanceof NumberCard) {
			return isNumberCardPlayable((NumberCard) currentCardInPlay, (NumberCard) drawnCard);
		} else if (currentCardInPlay instanceof ActionCard && drawnCard instanceof ActionCard) {
			return isActionCardPlayable((ActionCard) currentCardInPlay, (ActionCard) drawnCard);
		}
		return false;
	}

	private static boolean isNumberCardPlayable( NumberCard currentNumberCard, NumberCard drawnNumberCard ) {
		return currentNumberCard.getColor().equals(drawnNumberCard.getColor()) ||
				currentNumberCard.getValue() == drawnNumberCard.getValue();
	}

	private static boolean isActionCardPlayable( ActionCard currentActionCard, ActionCard drawnActionCard ) {
		return currentActionCard.getAction().equals(drawnActionCard.getAction());
	}
}