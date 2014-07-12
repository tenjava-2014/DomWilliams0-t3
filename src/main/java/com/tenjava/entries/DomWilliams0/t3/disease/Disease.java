package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.sporeclouds.InfectionSporeCloud;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

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

		DiseaseController.INSTANCE.diseaseBuffer.add(this);
	}

	public abstract void tick();

	protected void infectNearby(Location location)
	{
		// get nearby
		Entity[] chunk = location.getChunk().getEntities();
		if (chunk.length == 0)
			return;

		for (Entity entity : chunk)
		{

			if (!DiseaseController.INSTANCE.canBeInfected(entity) || DiseaseController.INSTANCE.isInfected(entity))
				continue;

			if (entity instanceof LivingEntity)
			{
				LivingEntity le = (LivingEntity) entity;
				if (TenJava.RANDOM.nextFloat() < 0.2) // infection!
				{
					new InfectionSporeCloud(le);
					return; // only infect one mob at a time
				}

			}

		}

	}

	/**
	 * Refers to the rate at which a spore cloud can move
	 */
	public static enum TickRate
	{
		SUPER(0), FAST(2), NORMAL(3);

		int rate;

		TickRate(int rate)
		{
			this.rate = rate;
		}
	}

}
