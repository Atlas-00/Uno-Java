package fr.atlas;

public class SpecialCard extends Card{
	private final String mSpecialCardName;
	public SpecialCard(String name) {
		this.mSpecialCardName = name;

	}

	@Override
	protected String mName() {
		return mSpecialCardName;
	}

	@Override
	public void playCard() {

    }
}
