package com.tenjava.entries.DomWilliams0.t3.listeners;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class GeneralListener implements Listener
{

	/**
	 * Makes sure an infected entity will always die from its symptoms
	 */
	@EventHandler
	public void onEntityDeath(EntityDamageEvent event)
	{
		if (!(event.getEntity() instanceof LivingEntity))
			return;

		LivingEntity entity = (LivingEntity) event.getEntity();


		if (DiseaseController.INSTANCE.isInfected(entity))
			if (entity.getHealth() - event.getDamage() <= 0) // killing blow
				DiseaseController.INSTANCE.unregisterInfection(entity);
	}

	/**
	 * Prevents free items
	 */
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event)
	{
		if (event.getItem().hasMetadata("ETI-PICKUP"))
		{
			event.setCancelled(true);
			event.getItem().remove();
		}
	}

}
