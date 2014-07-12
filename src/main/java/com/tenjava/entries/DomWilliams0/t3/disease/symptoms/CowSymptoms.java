package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class CowSymptoms extends BaseSymptoms
{
	protected CowSymptoms(LivingEntity entity)
	{
		super(entity);
	}

	@Override
	public void tick()
	{
		for (int i = 0; i < 8; i++)
			Utils.playEffect(entity, Effect.SMOKE, i);
		//		Utils.playEffect(entity, Effect.EXTINGUISH, 0);
	}

	@Override
	public void die()
	{
		Utils.scatterItems(Material.COOKED_BEEF, 15, entity.getLocation());
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				entity.getWorld().createExplosion(entity.getLocation(), 4, true);
				entity.remove();

			}
		}.runTaskLater(TenJava.INSTANCE, 15L);
	}
}
