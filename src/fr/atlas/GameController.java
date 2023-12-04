package fr.atlas;
import java.util.List;

public class GameController {
	private List<Players> players;
	private Deck deck;
	private Players currentPlayer;

	public GameController(List<Players> players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

}
