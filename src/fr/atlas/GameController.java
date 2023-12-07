package fr.atlas;

import fr.atlas.Cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController implements Deck {
	private final List<Player> players;
	private PaquetCard paquetCard;
	private Player currentPlayer;

	public GameController() {
		this.players = new ArrayList<>();
	}

	public void startGame() {
		addPlayer();
		// Other game initialization logic
	}

	public void addPlayer() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Vous allez jouer à combien : ");
		int nbPlayer = scanner.nextInt();

		for (int i = 1; i < nbPlayer; i++) {
			System.out.print("Veuillez saisir le nom du joueur n°" + i + " : ");
			String name = scanner.next();
			players.add(new Player(name));
		}

		drawCard();
	}

	public void nextTurn() {
		// Logique pour le prochain tour
	}

	@Override
	public void shuffle() {
		// Implémentation pour mélanger le jeu
	}

	@Override
	public Card drawCard() {
		for (Player player : players) {
			for (int i = 0; i < 7; i++) {
				player.drawCard(paquetCard.drawCard());
			}
		}
		return null;
	}
}