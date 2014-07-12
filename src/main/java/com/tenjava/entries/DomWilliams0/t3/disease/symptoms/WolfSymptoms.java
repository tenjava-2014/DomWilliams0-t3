package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WolfSymptoms extends BaseSymptoms
{
	protected WolfSymptoms(LivingEntity entity)
	{
		super(entity);
		Wolf wolf = (Wolf) entity;
		wolf.setAngry(true);
	}

	@Override
	public void tick()
	{
		if (TenJava.RANDOM.nextFloat() < 0.2)
		{
			entity.setVelocity(entity.getLocation().getDirection().setY(0.5));
			Utils.playSound(entity, Sound.WOLF_WHINE);
		}
	}

	@Override
	public void die()
	{
		Utils.playSound(entity, Sound.WOLF_WHINE);
		final FallingBlock replacement = entity.getWorld().spawnFallingBlock(entity.getLocation(), Material.WOOL, (byte) 0);
		entity.remove();

		replacement.setVelocity(new Vector(0, 2, 0));
		new BukkitRunnable()
		{

			@Override
			public void run()
			{
				replacement.getWorld().createExplosion(replacement.getLocation(), 2);
				replacement.remove();
			}
		}.runTaskLater(TenJava.INSTANCE, 20L);
	}
}
