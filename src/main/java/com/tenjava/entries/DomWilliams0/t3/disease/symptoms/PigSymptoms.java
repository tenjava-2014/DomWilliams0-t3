package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.HealthTracker;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;

public class PigSymptoms extends BaseSymptoms
{
	protected PigSymptoms(LivingEntity entity)
	{
		super(entity);
	}

	@Override
	public void tick()
	{
		if (TenJava.RANDOM.nextFloat() < 0.4)
		{
			Utils.scatterItems(Material.PORK, 2, entity.getLocation());
			Utils.playSound(entity, Sound.CHICKEN_EGG_POP);
		}

		if (HealthTracker.INSTANCE.getHealth(entity) == 25)
			((Pig) entity).setBaby();
	}

	@Override
	public void die()
	{
		entity.getWorld().spawnEntity(entity.getLocation(), EntityType.PIG_ZOMBIE);
		entity.remove();
	}
}
