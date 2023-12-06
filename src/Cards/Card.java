package Cards;

public abstract class Card {
	protected abstract String mName();
	public abstract void playCard();
	private final String color;

	public Card(String color) {
		this.color = color;
	}
	public String getColor() {
		return color;
	}
}
