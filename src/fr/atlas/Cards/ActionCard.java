package fr.atlas.Cards;

public class ActionCard extends Card {
	private final String mActionType;
	private final String mDescription;

	public ActionCard( String actionType ) {
		super("TOUTE"); // Couleur par défaut pour les cartes d'action
		this.mActionType = actionType;

		// Définie la description en fonction du type d'action
		switch (actionType.toUpperCase()) {
			case "REVERSE":
				this.mDescription = "Inverse le sens du jeu";
				break;
			case "SKIP":
				this.mDescription = "Passez le tour";
				break;
			case "DRAW_TWO":
				this.mDescription = "Piochez deux cartes";
				break;
			case "WILD_DRAW_FOUR":
				this.mDescription = "Piochez quatre cartes";
				break;
			case "WILD":
				this.mDescription = "Change de couleur";
				break;
			default:
				this.mDescription = "Description non spécifiée";
				break;
		}
	}

	public String getAction() {
		return mActionType;
	}

	public String getDescription() {
		return mDescription;
	}

	@Override
	public String toString() {
		return "{ActionType = " + getAction() + ", Description = " + getDescription() + "} ";
	}
}
