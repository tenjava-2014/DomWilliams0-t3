package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class DiseaseController
{
	public static DiseaseController INSTANCE;

	private DiseaseTask task;
	protected Set<SporeCloud> sporeClouds;

	public DiseaseController()
	{
		if (INSTANCE != null)
			INSTANCE.stop();
		INSTANCE = this;

		this.sporeClouds = new HashSet<SporeCloud>();

		start();
	}

	public void stop()
	{
		if (task != null)
		{
			task.cancel();
			task = null;
		}
	}

	public void start()
	{
		stop();
		task = new DiseaseTask();
		task.runTaskTimer(TenJava.INSTANCE, 0L, 15L);
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
