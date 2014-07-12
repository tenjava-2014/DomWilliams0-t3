package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.sporeclouds.InfectionSporeCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * Manages most disease related processes
 */
public class DiseaseController
{
	public static DiseaseController INSTANCE;

	protected BukkitRunnable task;
	private long nextOutbreak;

	protected Set<Disease> diseases, diseaseBuffer;
	private Set<UUID> infected, immune;

	public DiseaseController()
	{
		if (INSTANCE != null)
			INSTANCE.stop();
		INSTANCE = this;

		this.diseases = new HashSet<Disease>();
		this.diseaseBuffer = new HashSet<Disease>();

		this.infected = new HashSet<UUID>();
		this.immune = new HashSet<UUID>();
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

	public int end()
	{
		int amount = diseases.size() + diseaseBuffer.size();
		diseases.clear();
		diseaseBuffer.clear();
		infected.clear();
		return amount;
	}

	class DiseaseTask extends BukkitRunnable
	{
		@Override
		public void run()
		{
			// flush buffer
			if (!diseaseBuffer.isEmpty())
			{
				for (Disease disease : diseaseBuffer)
					diseases.add(disease);
				diseaseBuffer.clear();
			}


			for (Iterator<Disease> iterator = diseases.iterator(); iterator.hasNext(); )
			{
				Disease disease = iterator.next();

				if ((disease instanceof InfectionSporeCloud && immune.contains(((InfectionSporeCloud) disease).getVictim().getUniqueId())))
					disease.shouldRemove = true;

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
			if (immune.contains(entity.getUniqueId()))
				return false;

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

	/**
	 * @return The entities current state of immunity
	 */
	public boolean toggleImmunity(LivingEntity entity)
	{
		UUID uuid = entity.getUniqueId();
		boolean nowImmune = false;
		if (immune.contains(uuid))
			immune.remove(uuid);
		else
		{
			immune.add(uuid);
			nowImmune = true;
			unregisterInfection(entity);

		}
		return nowImmune;
	}

}
