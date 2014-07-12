package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.HealthTracker;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class SheepSymptoms extends BaseSymptoms
{

	private ItemStack wool;

	protected SheepSymptoms(LivingEntity entity)
	{
		super(entity);
		Sheep sheep = (Sheep) entity;
		wool = new ItemStack(Material.WOOL, 1, sheep.getColor().getWoolData());
	}

	@Override
	public void tick()
	{
		Item item = entity.getWorld().dropItemNaturally(entity.getLocation(), wool);
		item.setVelocity(item.getVelocity().multiply(2).setY(item.getVelocity().getY() + 0.7));
		item.setMetadata("ETI-PICKUP", new FixedMetadataValue(TenJava.INSTANCE, true));

		if (HealthTracker.INSTANCE.getHealth(entity) == 50)
			((Sheep) entity).setSheared(true);
	}

	@Override
	public void die()
	{
		entity.setFireTicks(500);
		entity.setHealth(1);
	}
}
