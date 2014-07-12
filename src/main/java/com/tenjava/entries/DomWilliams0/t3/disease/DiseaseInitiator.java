package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DiseaseInitiator
{

	/**
	 * @param cause If null, a random player is chosen
	 */
	public DiseaseInitiator(Player cause)
	{

		Location epicentre;
		if (cause == null)
		{
			if (Bukkit.getOnlinePlayers().length != 0)
				epicentre = Bukkit.getOnlinePlayers()[TenJava.RANDOM.nextInt(Bukkit.getOnlinePlayers().length)].getLocation();
			else
				epicentre = Bukkit.getWorlds().get(0).getSpawnLocation();

		} else
			epicentre = cause.getLocation();


		final Location centre = epicentre.getWorld().getHighestBlockAt(Utils.getRandomRelativeLocation(epicentre, 50)).getLocation();

		new BukkitRunnable()
		{

			@Override
			public void run()
			{
				centre.getWorld().strikeLightningEffect(centre);
				for (Effect effect : new Effect[]{Effect.GHAST_SHRIEK, Effect.POTION_BREAK, Effect.ENDER_SIGNAL})
					centre.getWorld().playEffect(centre, effect, 0);

				new SporeCloud(centre);

				Utils.broadcast("&o&7There has been an &2outbreak&7 at &8" + Utils.simplifyLocation(centre) + "!");

			}
		}.runTaskLater(TenJava.INSTANCE, TenJava.RANDOM.nextInt(50) + 50);


	}

}
