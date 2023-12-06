package Cards;

public class NumberCard extends Card {
	private final int mValue;
	private final String mName;

	public NumberCard(String name,String color, int value) {
		super(color);
		this.mValue = value;
		this.mName = name;
	}

	public int getValue() {
		return mValue;
	}
	@Override
	protected String mName() {
		return mName;
	}

	@Override
	public void playCard() {

	}
}
