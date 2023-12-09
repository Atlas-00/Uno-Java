package fr.atlas;

import fr.atlas.Cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController implements Deck {
	private final List<Player> players;
	private final PaquetCard paquetCard;
	private Player currentPlayer;

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

			// Ajouter les 7 cartes du début à la main du joueur
			for (int j = 0; j < 7; j++) {
				Card drawnCard = drawCard();
				player.drawCard(drawnCard);
			}
		}
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
			while (true) {
				Card drawnCard = paquetCard.drawCard();
				player.drawCard(drawnCard);
				drawnCards.add(drawnCard);

				// Vérifie si la carte piochée et jouable
				if (isPlayable(drawnCard)) {
					return drawnCard;
				}
			}
		}
		return null;
	}

	private boolean isPlayable( Card drawnCard ) {
		// Récupérer la carte actuelle en jeu (par exemple, la dernière carte jouée)
		Card currentCardInPlay = getCurrentCardInPlay();

		// Vérifier si la couleur ou la valeur correspond
		return drawnCard.getColor().equals(currentCardInPlay.getColor())
				|| drawnCard.getValue().equals(currentCardInPlay.getValue());
	}

	private Card getCurrentCardInPlay() {
		// Ajoutez votre logique pour récupérer la carte actuellement en jeu
		// Cela dépendra de la façon dont vous gérez les tours et les cartes jouées.
		// Par exemple, vous pourriez avoir une variable qui représente la dernière carte jouée.
		return null; // Pour l'exemple, retourne null
	}
}
