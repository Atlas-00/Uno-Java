package fr.atlas;

import fr.atlas.Cards.ActionCard;
import fr.atlas.Cards.Card;
import fr.atlas.Cards.NumberCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController implements Deck {
	private final List<Player> players;
	private final PaquetCard paquetCard;
	private Player currentPlayer;
	private Card currentCardInPlay;

	public GameController() {
		this.players = new ArrayList<>();
		this.paquetCard = new PaquetCard();
	}

	public void startGame() {
		System.out.println("Starting Uno Game");
		System.out.println("================================================================\n");

		// Ajout des players
		addPlayer();

		// Distributions des cards
		distributeInitialCards();

		// Initialiser le joueur actuel
		currentPlayer = players.getFirst();

		// Initialisation de la première carte
		Card initializer = paquetCard.drawCard();

		// Commencer le premier tour
		playerTurn();

		// Faire jouer les autres joueurs
		nextTurn();
	}

	public void addPlayer() {
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

	public void playerTurn() {
		Scanner scanner = new Scanner(System.in);

		System.out.println(currentPlayer.getName() + " Commence!");
		System.out.println("Les carte de ".concat(currentPlayer.getName()) + " : ");

		for (Card card : currentPlayer.getHand()) {
			System.out.println("\t" + card.toString());
		}

		System.out.print("\nVeuillez saisir la carte que vous voulez jouer : ");
		int cardIndex = scanner.nextInt();
		cardIndex--;

		if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
			currentCardInPlay = currentPlayer.getHand().get(cardIndex);
			System.out.println("Vous avez la jouez carte " + currentCardInPlay);
		} else {
			System.out.println("La carte que vous avez choisie n'existe pas !!");
		}
	}

	public void nextTurn() {
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
				if (isPlayable(drawnCard)) {
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

	private boolean isPlayable( Card drawnCard ) {
		Card currentCardInPlay = getCurrentCardInPlay();

		// Si la carte actuelle en jeu est une carte numérique
		if (currentCardInPlay instanceof NumberCard currentNumberCard && drawnCard instanceof NumberCard drawnNumberCard) {
			// Vérifie si la couleur ou la valeur correspond
			return currentNumberCard.getColor().equals(drawnNumberCard.getColor()) ||
					currentNumberCard.getValue() == drawnNumberCard.getValue();
		}
		// Si la carte actuelle en jeu est une carte d'action
		else if (currentCardInPlay instanceof ActionCard currentActionCard && drawnCard instanceof ActionCard drawnActionCard) {
			// Vérifie si le type d'action correspond
			return currentActionCard.getAction().equals(drawnActionCard.getAction());
		}

		// Si les types de cartes ne correspondent pas, la carte est non jouable
		return false;
	}

	private Card getCurrentCardInPlay() {
		return currentCardInPlay;
	}
}
