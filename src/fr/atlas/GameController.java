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
		// Ajout des players
		addPlayer();

		// Initialiser le joueur actuel
		currentPlayer = players.getFirst();

		// Initialisation de la première carte
		Card initializer = paquetCard.drawCard();

		// Commencer le premier tour
		playerTurn();
	}

	public void addPlayer() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Vous allez jouer à combien : ");
		int nbPlayer = scanner.nextInt();

		if (nbPlayer < 2) {
			System.out.println("Vous ne pouvez pas jouer avec moins de 2 joueurs !");
			addPlayer(); // Demande à l'utilisateur de saisir à nouveau le nombre de joueurs
			return;
		}

		for (int i = 1; i <= nbPlayer; i++) {
			System.out.print("Veuillez saisir le nom du joueur n°" + i + " : ");
			String name = scanner.next();
			Player player = new Player(name);
			players.add(player);
		}

		for (Player player : players) {
			for (int j = 0; j < 7; j++) {
				player.getHand().add(drawCard());
			}
		}
		scanner.close();
	}

	public void playerTurn() {
		System.out.println(currentPlayer.getHand());
	}

	public void nextTurn() {
		// Logique pour le prochain tour
	}

	@Override
	public void shuffle() {
		paquetCard.shuffle();
	}

	@Override
	public Card drawCard() {
		List<Card> drawnCards = new ArrayList<>();

		for (Player player : players) {
			Card drawnCard = paquetCard.drawCard();
			player.drawCard(drawnCard);
			drawnCards.add(drawnCard);

			// Vérifie si la carte piochée et jouable
			if (isPlayable(drawnCard)) {
				currentCardInPlay = drawnCard;
				return drawnCard;
			}

		}
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
