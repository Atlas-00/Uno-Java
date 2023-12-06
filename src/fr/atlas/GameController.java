package fr.atlas;

import Cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController implements Deck {
	private final List<Player> players;
	private Deck deck;
	private Player currentPlayer;

	public GameController() {
		this.players = new ArrayList<>();
		// Initialize deck and other variables if needed
	}

	public void startGame() {
		addPlayer();
		// Other game initialization logic
	}

	public void addPlayer() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Vous allez jouer à combien : ");
		int nbPlayer = scanner.nextInt();

		int numPlayer = 1;
		for (int i = 0; i < nbPlayer; i++) {
			System.out.print("Veuillez saisir le nom du joueur n°" + numPlayer + " : ");
			numPlayer++;
			String name = scanner.next();
			players.add(new Player(name, new ArrayList<>()));
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
				player.drawCard(deck.drawCard());
			}
		}
		return null;
	}
}