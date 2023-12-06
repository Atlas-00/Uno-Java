package Cards;

public class WildCard extends Card {
	private final String mWildType;

	private final String mName;
	public WildCard( String name,String wildType) {
		super("Wild"); // La couleur pour les cartes Wild peut être spécifique ou générique, selon vos besoins.
		this.mWildType = wildType;
		this.mName = name;
	}

	public String getWildType() {
		return mWildType;
	}

	@Override
	protected String mName() {
		return mName;
	}

	@Override
	public void playCard() {

	}
}
