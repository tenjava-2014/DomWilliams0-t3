package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ChickenSymptoms extends BaseSymptoms
{
	protected ChickenSymptoms(LivingEntity entity)
	{
		super(entity);
	}

	@Override
	public void tick()
	{
		if (TenJava.RANDOM.nextFloat() < 0.3)
		{
			Utils.playSound(entity, Sound.FUSE);
			entity.setVelocity(entity.getVelocity().setY(0.4));
		}
	}

	@Override
	public void die()
	{
		Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
		FireworkMeta meta = firework.getFireworkMeta();
		meta.setPower(2);
		meta.addEffect(FireworkEffect.builder().withTrail().withColor(Color.WHITE, Color.ORANGE).build());
		firework.setFireworkMeta(meta);

		entity.setVelocity(entity.getVelocity().setY(2));

		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				entity.remove();
			}
		}.runTaskLater(TenJava.INSTANCE, 20L);

	}
}
