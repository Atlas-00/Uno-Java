package fr.atlas;

import fr.atlas.Cards.ActionCard;
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
		// Affiche la carte actuellement en jeu
		currentCardInPlay = initializer;
		System.out.println("\nLa carte actuelle en jeu : " + currentCardInPlay);

		// Affiche les cartes du joueur
		System.out.println("Les cartes de " + currentPlayer.getName() + " : ");
		for (int i = 0; i < currentPlayer.getHand().size(); i++) {
			System.out.println("\t" + (i + 1) + ". " + currentPlayer.getHand().get(i).toString());
		}

		// Saisi la carte que le joueur veut jouer
		System.out.print("\nVeuillez saisir le numéro de la carte que vous voulez jouer : ");
		int cardIndex = scanner.nextInt();
		cardIndex--;

		// Vérifie si la carte est jouable en utilisant la méthode isPlayable()
		if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
			Card selectedCard = currentPlayer.getHand().get(cardIndex);
			if (selectedCard instanceof ActionCard actionCard) {
				applyActionEffect(actionCard);
			} else {
				if (GameRules.isPlayable(currentCardInPlay, selectedCard)) {
					// Le joueur peut jouer la carte
					currentCardInPlay = selectedCard;
					currentPlayer.getHand().remove(cardIndex);
					System.out.println(currentPlayer.getName() + " a joué la carte " + currentCardInPlay);
					currentCardInPlay = selectedCard;
				} else {
					// La carte n'est pas jouable
					System.out.println("Vous ne pouvez pas jouer la carte " + selectedCard);
					System.out.println("Vous devez piocher ");

					// Pioche une carte et l'ajoute à la main du joueur
					Card piocherCard = paquetCard.drawCard();
					currentPlayer.drawCard(piocherCard);

					System.out.println("Vous avez pioché la carte" + piocherCard);

				}
			}
		} else {
			// Numéro de carte invalide
			System.out.println("Le numéro de carte que vous avez choisi n'est pas valide !");
		}
	}

	private void nextTurn() {
		// Logique pour le prochain tour
		do {
			for (int i = 0; i < players.size(); i++) {
				currentPlayer = players.get(i);
				playerTurn();
				if (i == players.size() - 1) {
					// Réinitialiser l'index du joueur à zéro pour revenir au premier joueur
					i = - 1;
				}
			}
		} while (! GameRules.isGameOver(players));
	}

	private void applyActionEffect( Card actionCard ) {
		// Appelle la méthode appropriée en fonction du type de carte d'action
		if (actionCard instanceof ActionCard action) {
			switch (action.getAction()) {
				case "REVERSE":
					applyReverseAction();
					break;
				case "SKIP":
					applySkipAction();
					break;
				case "DRAW_TWO":
					applyDrawTwoAction();
					break;
				case "WILD_DRAW_FOUR":
					applyWildDrawFourAction();
					break;
				case "WILD":
					applyWildAction();
					break;
			}
		}
	}

	private void applyReverseAction() {
		System.out.println("REVERSE");
	}

	private void applySkipAction() {
		System.out.println("SKIP");
	}

	private void applyDrawTwoAction() {
		System.out.println("DRAW_TWO");
	}

	private void applyWildDrawFourAction() {
		System.out.println("WILD_DRAW_FOUR");
	}

	private void applyWildAction() {
		System.out.println("WILD");
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
