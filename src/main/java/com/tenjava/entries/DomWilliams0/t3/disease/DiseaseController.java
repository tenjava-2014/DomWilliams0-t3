package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * Manages all sporeclouds, as well as initiators
 */
public class DiseaseController
{
	public static DiseaseController INSTANCE;

	protected BukkitRunnable task;
	private long nextOutbreak;

	protected Set<Disease> diseases;
	private Set<UUID> infected;

	public DiseaseController()
	{
		if (INSTANCE != null)
			INSTANCE.stop();
		INSTANCE = this;

		this.diseases = new HashSet<Disease>();
		this.infected = new HashSet<UUID>();
		nextOutbreak();
		start();

	}

	public void start()
	{
		stop();
		task = new DiseaseTask();
		task.runTaskTimer(TenJava.INSTANCE, 0L, 5L);
	}


	public void stop()
	{
		if (task != null)
		{
			task.cancel();
			task = null;
		}
	}

	public boolean isRunning()
	{
		return task != null;
	}

	private void nextOutbreak()
	{
		int offset = 300000;
		nextOutbreak = System.currentTimeMillis() + (TenJava.RANDOM.nextInt(offset) + offset); // between 5 and 10 minutes
	}

	class DiseaseTask extends BukkitRunnable
	{
		@Override
		public void run()
		{
			for (Iterator<Disease> iterator = diseases.iterator(); iterator.hasNext(); )
			{
				Disease disease = iterator.next();

				if (disease.shouldRemove)
				{
					iterator.remove();
					continue;
				}

				if (--disease.rate <= 0)
				{
					disease.rate = disease.tickRate.ordinal();
					disease.tick();
				}
			}

			long time = System.currentTimeMillis();

			if (time >= nextOutbreak)
			{
				new DiseaseInitiator(null);
				nextOutbreak();
			}
		}
	}



	public void unregisterInfection(LivingEntity entity)
	{
		infected.remove(entity.getUniqueId());
	}

	public void registerInfection(LivingEntity entity)
	{
		infected.add(entity.getUniqueId());
	}

	public boolean isInfected(Entity entity)
	{
		return infected.contains(entity.getUniqueId());
	}

	public boolean canBeInfected(Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			switch (entity.getType())
			{
				case MUSHROOM_COW:
				case COW:
				case SHEEP:
				case CHICKEN:
				case WOLF:
				case PIG:
				case PLAYER:
					return true;
			}
		}

		return false;

	}


}
