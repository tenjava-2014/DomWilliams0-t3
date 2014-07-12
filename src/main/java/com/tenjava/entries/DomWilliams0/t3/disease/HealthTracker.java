package com.tenjava.entries.DomWilliams0.t3.disease;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HealthTracker
{
	public static HealthTracker INSTANCE;

	private Map<UUID, Integer> health;

	public HealthTracker()
	{
		INSTANCE = this;
		this.health = new HashMap<UUID, Integer>();
	}


	/**
	 * Defaults to 100
	 */
	public int getHealth(LivingEntity entity)
	{
		Integer i = health.get(entity.getUniqueId());
		return i == null ? 100 : i;
	}

	public void setHealth(LivingEntity entity, int newHealth)
	{
		health.put(entity.getUniqueId(), newHealth);
	}

	/**
	 * Decrements an entity's health by 1
	 * @return The new health
	 */
	public int damage(LivingEntity entity)
	{
		int newValue = getHealth(entity) - 1;
		health.put(entity.getUniqueId(), newValue);
		return newValue;
	}

}
