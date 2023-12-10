package fr.atlas;

import fr.atlas.Cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController implements Deck {
	private final List<Player> players;
	private final PaquetCard paquetCard;
	Card initializer;
	private Player currentPlayer;
	private Card currentCardInPlay;

	public GameController() {
		this.players = new ArrayList<>();
		this.paquetCard = new PaquetCard();
	}

	public void startGame() {
		System.out.println("\t\t\t\t\t\tStarting Uno Game");
		System.out.println("================================================================\n");

		// Ajout des players
		addPlayer();

		// Distributions des cards
		distributeInitialCards();

		// Initialiser le joueur actuel
		currentPlayer = players.getFirst();

		// Initialisation de la première carte
		initializer = paquetCard.drawCard();

		// Commencer le premier tour
		playerTurn();

		// Faire jouer les autres joueurs
		nextTurn();
	}

	private void addPlayer() {
		Scanner scanner = new Scanner(System.in);

		int nbPlayer;
		do {
			System.out.print("Vous allez jouer à combien (au moins 2) : ");
			nbPlayer = scanner.nextInt();

			if (nbPlayer < 2) {
				System.out.println("Vous ne pouvez pas jouer avec moins de 2 joueurs !");
			}
		} while (nbPlayer < 2);

		for (int i = 1; i <= nbPlayer; i++) {
			System.out.print("Veuillez saisir le nom du joueur n°" + i + " : ");
			String name = scanner.next();
			Player player = new Player(name);
			players.add(player);
		}
	}

	private void distributeInitialCards() {
		for (Player player : players) {
			for (int j = 0; j < 7; j++) {
				paquetCard.shuffle();
				Card drawnCard = paquetCard.drawCard();
				if (drawnCard != null) {
					player.getHand().add(drawnCard);
				} else {
					paquetCard.shuffle();
					j--;
				}
			}
		}
	}

	private void playerTurn() {
		Scanner scanner = new Scanner(System.in);
		// Afficher la carte actuellement en jeu
		currentCardInPlay = initializer;
		System.out.println("\nLa carte actuelle en jeu : " + currentCardInPlay);

		// Afficher les cartes du joueur
		System.out.println("Les cartes de " + currentPlayer.getName() + " : ");
		for (int i = 0; i < currentPlayer.getHand().size(); i++) {
			System.out.println("\t" + (i + 1) + ". " + currentPlayer.getHand().get(i).toString());
		}

		// Saisir la carte que le joueur veut jouer
		System.out.print("\nVeuillez saisir le numéro de la carte que vous voulez jouer : ");
		int cardIndex = scanner.nextInt();
		cardIndex--;

		// Vérifier si la carte est jouable en utilisant la méthode isPlayable()
		if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
			Card selectedCard = currentPlayer.getHand().get(cardIndex);
			if (GameRules.isPlayable(currentCardInPlay, selectedCard)) {
				// Le joueur peut jouer la carte
				currentPlayer.getHand().remove(cardIndex);
				System.out.println(currentPlayer.getName() + " a joué la carte " + currentCardInPlay);
				currentCardInPlay = selectedCard;
			} else {
				// La carte n'est pas jouable
				System.out.println("Vous ne pouvez pas jouer la carte " + selectedCard);
			}
		} else {
			// Numéro de carte invalide
			System.out.println("Le numéro de carte que vous avez choisi n'est pas valide !");
		}
	}

	private void nextTurn() {
		// Logique pour le prochain tour
		for (int i = 1; i < players.size(); i++) {
			currentPlayer = players.get(i);
			playerTurn();
		}
	}

	@Override
	public void shuffle() {
		paquetCard.shuffle();
	}

	@Override
	public Card drawCard() {
		Card drawnCard;

		do {
			drawnCard = paquetCard.drawCard();
			if (drawnCard != null) {
				// Distribuer la carte à tous les joueurs
				for (Player player : players) {
					player.drawCard(drawnCard);
				}

				// Vérifier si la carte piochée est jouable
				if (GameRules.isPlayable(currentCardInPlay, drawnCard)) {
					currentCardInPlay = drawnCard;
					return drawnCard;
				}
			} else {
				// Si le paquet est vide, mélanger et réessayer
				paquetCard.shuffle();
			}
		} while (drawnCard == null);

		return null;
	}
}
