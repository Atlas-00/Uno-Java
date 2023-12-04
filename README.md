
# Uno Game
Ce projet représente une implémentation simple du jeu de cartes Uno en Java, sans interface utilisateur graphique (GUI).

## Classes et Méthodes

### 1. `Card` (Classe Abstraite)

La classe `Card` représente une carte du jeu Uno. Elle est définie comme une classe abstraite avec des méthodes abstraites pour gérer les actions spécifiques aux différentes cartes.

- **Méthodes :**
    - `playCard()`: Méthode abstraite pour effectuer une action spécifique lorsqu'une carte est jouée.

### 2. `Deck` (Interface)

L'interface `Deck` définit les méthodes nécessaires pour manipuler un paquet de cartes. Une classe concrète doit implémenter cette interface pour créer un paquet de cartes Uno.

- **Méthodes :**
    - `shuffle()`: Mélange les cartes dans le paquet.
    - `drawCard()`: Pioche une carte du paquet.

### 3. `Player` (Classe Normale)

La classe `Player` représente un joueur dans le jeu Uno. Chaque joueur a un nom et une main (une liste de cartes).

- **Attributs :**
    - `playerName`: Nom du joueur.
    - `hand`: Liste des cartes dans la main du joueur.

- **Méthodes :**
    - `playCard(Card card)`: Joue une carte de la main du joueur.
    - `drawCard(Card card)`: Pioche une carte et l'ajoute à la main du joueur.

### 4. `GameController` (Classe Normale)

La classe `GameController` est responsable de la gestion du flux de jeu. Elle maintient une liste de joueurs, un paquet de cartes, et gère les tours de jeu.

- **Attributs :**
    - `players`: Liste des joueurs.
    - `deck`: Paquet de cartes.
    - `currentPlayer`: Joueur actuellement en train de jouer.

- **Méthodes :**
    - `startGame()`: Initialise et démarre le jeu Uno.
    - `nextTurn()`: Passe au joueur suivant.
    - Autres méthodes pour gérer le déroulement du jeu.

### 5. `GameRules` (Classe Normale)

La classe `GameRules` contient la logique des règles du jeu Uno.

- **Méthodes :**
    - Méthodes pour vérifier la validité d'un coup.
    - Méthodes pour détecter la fin de la partie.
    - Autres règles spécifiques au jeu Uno.

## Utilisation

1. Clonez le projet.
2. Modifiez les classes et méthodes selon vos besoins spécifiques.
3. Compilez et exécutez le fichier `UnoGame.java` pour démarrer le jeu.

---