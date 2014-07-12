package com.tenjava.entries.DomWilliams0.t3.disease.sporeclouds;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.Disease;
import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.disease.HealthTracker;
import com.tenjava.entries.DomWilliams0.t3.disease.symptoms.BaseSymptoms;
import com.tenjava.entries.DomWilliams0.t3.disease.symptoms.SymptomsFactory;
import com.tenjava.entries.DomWilliams0.t3.util.Effects;
import org.bukkit.entity.LivingEntity;

/**
 * A cloud of spores that have infected a host
 */
public class InfectionSporeCloud extends Disease
{
	private LivingEntity victim;
	private BaseSymptoms symptoms;

	public InfectionSporeCloud(LivingEntity victim)
	{
		this.victim = victim;
		this.symptoms = SymptomsFactory.produceSymptoms(victim);
		DiseaseController.INSTANCE.registerInfection(victim);
	}

	@Override
	public void tick()
	{
		Effects.infectedEffect(victim);
		Effects.ouchSound(victim);
		symptoms.tick();
		infectNearby(victim.getLocation());

		HealthTracker.INSTANCE.damage(victim);
		int victimHealth = HealthTracker.INSTANCE.getHealth(victim);

		if (--victimHealth == 20)
			tickRate = TickRate.SUPER;
		else if (victimHealth == 40)
			tickRate = TickRate.FAST;
		if (victimHealth <= 0 || victim.isDead())
		{
			if (TenJava.RANDOM.nextFloat() < 0.2)
			{
				new MobileSporeCloud(victim.getLocation());
				//	victim.getWorld().strikeLightningEffect(victim.getLocation()); // slightly annoying
			}
			DiseaseController.INSTANCE.unregisterInfection(victim);
			shouldRemove = true;
			symptoms.die();
		}

	}

	public LivingEntity getVictim()
	{
		return victim;
	}
}
