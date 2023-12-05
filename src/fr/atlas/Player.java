package fr.atlas;
import java.util.List;

public class Player {
	private final String mPlayerName;
	private List<Card> mHand;
	public Player(String name, List<Card> hand) {
		this.mPlayerName = name;
        this.mHand = hand;
	}
	public void playCard(Card card) {}
	public void drawCard(Card card) {}
}
