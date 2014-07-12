package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
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
		if (TenJava.RANDOM.nextFloat() < 0.4)
		{
			Utils.playSound(entity, Sound.FUSE);
			entity.setVelocity(entity.getVelocity().setY(0.4));
		}
	}

	@Override
	public void die()
	{
		for (int i = 0; i < 8; i++)
		{
			Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
			FireworkMeta fireworkMeta = firework.getFireworkMeta();
			fireworkMeta.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.fromRGB(TenJava.RANDOM.nextInt(256), TenJava.RANDOM.nextInt(256), TenJava.RANDOM.nextInt(256))).build());
			fireworkMeta.setPower(0);
			firework.setFireworkMeta(fireworkMeta);
		}
		entity.setVelocity(entity.getVelocity().setY(1.3));

		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.EGG));
				entity.remove();
			}
		}.runTaskLater(TenJava.INSTANCE, 20L);

	}
}
