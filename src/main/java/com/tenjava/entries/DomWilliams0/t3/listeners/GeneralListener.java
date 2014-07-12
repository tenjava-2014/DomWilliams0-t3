package com.tenjava.entries.DomWilliams0.t3.listeners;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GeneralListener implements Listener
{

	@EventHandler
	public void onEntityDeath(EntityDamageEvent event)
	{
		if (!(event.getEntity() instanceof LivingEntity))
			return;

		LivingEntity entity = (LivingEntity) event.getEntity();


		if (DiseaseController.INSTANCE.isInfected(entity))
		{
			if (entity.getHealth() - event.getDamage() <= 0) // killing blow
			{
				DiseaseController.INSTANCE.unregisterInfection(entity);
			}


		}


	}


}
