package Cards;

public class ActionCard extends Card{
	private final String actionType;
	private final String mName;

	public ActionCard(String name,String color, String actionType) {
		super(color);
		this.actionType = actionType;
		this.mName = name;
	}

	public String getActionType() {
		return actionType;
	}

	@Override
	protected String mName() {
		return mName;
	}

	@Override
	public void playCard() {

	}
}
