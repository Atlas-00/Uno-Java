package fr.atlas;

import fr.atlas.Cards.ActionCard;
import fr.atlas.Cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("\t\t\t\t\t\tStarting Uno Game");
		System.out.println("================================================================\n");

		// Ajout des players
		addPlayer();

		// Distributions des cards
		distributeInitialCards();

		// Initialiser le joueur actuel
		currentPlayer = players.getFirst();

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
		for (int i = 0; i < 7; i++) {
			for (Player player : players) {
				Card drawnCard = paquetCard.drawCard();

				if (drawnCard != null) {
					// Si la carte tirée est une ActionCard, mélanger le paquet et continuer à piocher
					if (drawnCard instanceof ActionCard) {
						paquetCard.shuffle();
					} else {
						// Sinon, initialiser currentCardInPlay avec la carte numérique tirée
						currentCardInPlay = drawnCard;
					}

					// Ajouter la carte à la main du joueur
					player.getHand().add(drawnCard);
				} else {
					paquetCard.shuffle();
				}
			}
		}
	}

	public void playerTurn() {
		Scanner scanner = new Scanner(System.in);

		// Affiche la carte actuellement en jeu
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
				currentPlayer.getHand().remove(selectedCard);
			} else {
				if (GameRules.isPlayable(currentCardInPlay, selectedCard)) {
					// Le joueur peut jouer la carte
					currentPlayer.getHand().remove(cardIndex);
					System.out.println(currentPlayer.getName() + " a joué la carte " + selectedCard);
					currentCardInPlay = selectedCard;
				} else {
					// La carte n'est pas jouable
					System.out.println("Vous ne pouvez pas jouer la carte " + selectedCard);
					System.out.println("Vous devez piocher ");

					// Pioche une carte et l'ajoute à la main du joueur
					Card piocherCard = paquetCard.drawCard();
					currentPlayer.drawCard(piocherCard);

					System.out.println("Vous avez pioché la carte " + piocherCard);
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
			for (int i = 1; i < players.size(); i++) {
				currentPlayer = players.get(i);
				playerTurn();

				if (i == players.size() - 1) {
					i = - 1;
				}
			}
		} while (GameRules.isGameOver(players));
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
					applyDrawTwoAction(currentPlayer);
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
		int currentPlayerIndex = players.indexOf(currentPlayer);

		// Calculer l'index du prochain joueur après le saut de tour
		int nextPlayerIndex = (currentPlayerIndex + 2) % players.size();

		// Mettre à jour le joueur actuel avec le joueur suivant
		currentPlayer = players.get(nextPlayerIndex);

		System.out.println("Le tour du prochain joueur (" + currentPlayer.getName() + ") est sauté !");
	}

	private void applyDrawTwoAction( Player player ) {
		skipNextPlayerTurn();
		for (int i = 0; i < 2; i++) {
			Card card = drawCard();
			if (card != null) {
				player.getHand().add(card);
			} else {
				paquetCard.shuffle();
				i--;
			}
		}
	}

	private void applyWildDrawFourAction() {
		// Demander au joueur actuel de choisir une nouvelle couleur
		String chosenColor = askPlayerForColorSelection();

		// Mettre à jour la couleur de la carte en jeu
		currentCardInPlay.setColor(chosenColor);

		System.out.println("La nouvelle couleur choisie est : " + chosenColor);

		// Sauter le tour du joueur suivant
		skipNextPlayerTurn();

		// Faire piocher quatre cartes au joueur suivant
		drawCardsFourForPlayer(currentPlayer);
	}

	private void applyWildAction() {
		// Demander au joueur actuel de choisir une nouvelle couleur
		String chosenColor = askPlayerForColorSelection();

		// Mettre à jour la couleur de la carte en jeu
		currentCardInPlay.setColor(chosenColor);

		System.out.println("La nouvelle couleur choisie est : " + chosenColor);
	}

	private String askPlayerForColorSelection() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\nListe des couleurs disponibles : ");
		for (String cardColor : Card.COLORS) {
			System.out.println("\t" + cardColor);
		}

		String color;
		boolean validColor;

		do {
			System.out.print("\nVeuillez saisir la couleur que vous voulez jouer : ");
			color = scanner.next();

			// Vérifier si la couleur est valide
			validColor = Arrays.asList(Card.COLORS).contains(color.toUpperCase());

			if (! validColor) {
				System.out.println("La couleur choisie est invalide. Veuillez choisir parmi les couleurs disponibles.");
			}

		} while (! validColor);

		return color.toUpperCase();
	}

	private void skipNextPlayerTurn() {
		int currentPlayerIndex = players.indexOf(currentPlayer);
		int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
		currentPlayer = players.get(nextPlayerIndex);
	}

	private void drawCardsFourForPlayer( Player player ) {
		for (int i = 0; i < 4; i++) {
			Card drawnCard = drawCard();
			if (drawnCard != null) {
				player.getHand().add(drawnCard);
			} else {
				paquetCard.shuffle();
				i--;
			}
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
