package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.sporeclouds.MobileSporeCloud;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Causes lightning to bring down a spore cloud from space
 */
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


		final Location centre = epicentre.getWorld().getHighestBlockAt(Utils.getRandomRelativeLocation(epicentre, 20)).getLocation();

		new BukkitRunnable()
		{

			@Override
			public void run()
			{
				release(centre);
			}
		}.runTaskLater(TenJava.INSTANCE, TenJava.RANDOM.nextInt(50) + 10);


	}

	public static void release(Location location)
	{
		location.getWorld().createExplosion(location, 0);

		for (Effect effect : new Effect[]{Effect.GHAST_SHRIEK, Effect.POTION_BREAK, Effect.ENDER_SIGNAL})
		{
			location.getWorld().strikeLightningEffect(location);
			location.getWorld().playEffect(location, effect, 0);
		}
		location.getWorld().playSound(location, Sound.WITHER_DEATH, 3f, 1f);

		new MobileSporeCloud(location);

		Utils.broadcast("&o&7There has been an &2outbreak&7 at &8" + Utils.simplifyLocation(location) + "&7!");

	}

}
