package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Effects;
import org.bukkit.entity.LivingEntity;

public class InfectionSporeCloud extends Disease
{
	private LivingEntity victim;

	protected InfectionSporeCloud(LivingEntity victim)
	{
		this.victim = victim;
		DiseaseController.INSTANCE.registerInfection(victim);
	}

	@Override
	public void tick()
	{
		Effects.sporeEffect(victim.getLocation(), 2, false);
		Effects.ouchSound(victim);

		HealthTracker.INSTANCE.damage(victim);
		int victimHealth = HealthTracker.INSTANCE.getHealth(victim);

		if (--victimHealth == 33)
			tickRate = TickRate.SUPER;
		else if (victimHealth == 66)
			tickRate = TickRate.FAST;
		if (victimHealth <= 0 || victim.isDead())
		{
			killDisease();
			if (TenJava.RANDOM.nextFloat() < 0.2)
				new MobileSporeCloud(victim.getLocation());
			DiseaseController.INSTANCE.unregisterInfection(victim);
			Effects.kill(victim);
		}

	}
}
