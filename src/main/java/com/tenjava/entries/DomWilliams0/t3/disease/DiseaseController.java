package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages all active sporeclouds
 */
public class DiseaseController
{
	public static DiseaseController INSTANCE;

	protected Set<SporeCloud> sporeClouds;
	protected BukkitRunnable task;

	public DiseaseController()
	{
		if (INSTANCE != null)
			INSTANCE.stop();
		INSTANCE = this;

		this.sporeClouds = new HashSet<SporeCloud>();
		start();

	}

	public void start()
	{
		stop();
		task = new DiseaseTask();
		task.runTaskTimer(TenJava.INSTANCE, 0L, 25L);
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


	class DiseaseTask extends BukkitRunnable
	{
		@Override
		public void run()
		{
			for (SporeCloud sporeCloud : sporeClouds)
			{
				sporeCloud.tick();
			}
		}
	}


}
