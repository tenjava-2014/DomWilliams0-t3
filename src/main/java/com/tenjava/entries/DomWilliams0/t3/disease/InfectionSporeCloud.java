package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.symptoms.BaseSymptoms;
import com.tenjava.entries.DomWilliams0.t3.disease.symptoms.SymptomsFactory;
import com.tenjava.entries.DomWilliams0.t3.util.Effects;
import org.bukkit.entity.LivingEntity;

public class InfectionSporeCloud extends Disease
{
	private LivingEntity victim;
	private BaseSymptoms symptoms;

	protected InfectionSporeCloud(LivingEntity victim)
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

		if (--victimHealth == 33)
			tickRate = TickRate.SUPER;
		else if (victimHealth == 66)
			tickRate = TickRate.FAST;
		if (victimHealth <= 0 || victim.isDead())
		{
			if (TenJava.RANDOM.nextFloat() < 0.2)
			{
				new MobileSporeCloud(victim.getLocation());
				victim.getWorld().strikeLightningEffect(victim.getLocation());
			}
			DiseaseController.INSTANCE.unregisterInfection(victim);
			shouldRemove = true;
			symptoms.die();
		}

	}
}
