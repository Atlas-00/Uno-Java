package fr.atlas;

public class NormalCard extends Card
{
	private final String mNormalCardName;
	public NormalCard(String name)
	{
		 mNormalCardName = name;
	}

	@Override
	protected String mName() {
		return mNormalCardName;
	}
	@Override
	public void playCard() {

	}
}
