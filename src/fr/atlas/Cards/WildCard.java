package fr.atlas.Cards;

public class WildCard extends Card {
	private final String mWildType;
	public WildCard(String wildType) {
		super("Wild"); // La couleur pour les cartes Wild peut être spécifique ou générique
		this.mWildType = wildType;
	}

	public String getWildType() {
		return mWildType;
	}

	@Override
	public void playCard() {

	}
}
