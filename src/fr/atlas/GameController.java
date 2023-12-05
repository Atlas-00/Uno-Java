package fr.atlas;
import java.util.List;

public class GameController {
	private List<Player> player;
	private Deck deck;
	private Player currentPlayer;

	public GameController( List<Player> players, Deck deck) {
        this.player = players;
        this.deck = deck;
    }

	public void startGame() {}

	public void addPlayer(Player player) {
		this.player.add(player);
	}

	public void nextTurn() {}
}
