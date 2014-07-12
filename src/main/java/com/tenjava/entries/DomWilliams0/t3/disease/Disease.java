package com.tenjava.entries.DomWilliams0.t3.disease;

public abstract class Disease
{
	protected int rate;
	protected TickRate tickRate;
	protected boolean shouldRemove;

	protected Disease()
	{
		this.tickRate = TickRate.NORMAL;
		this.rate = tickRate.ordinal();
		this.shouldRemove = false;

		DiseaseController.INSTANCE.diseases.add(this);
	}

	protected void killDisease()
	{
		shouldRemove = true;
	}

	abstract void tick();

	static enum TickRate
	{
		SUPER(1), FAST(3), NORMAL(4);

		int rate;

		TickRate(int rate)
		{
			this.rate = rate;
		}
	}

}
