package com.tenjava.entries.DomWilliams0.t3.util;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class Utils
{
	private static final String PREFIX = "§8<§2ETI§8> §r";
	private static final String ERROR_PREFIX = "§8<§4ETI§8> §r";

	/**
	 * Standard message
	 */
	public static void msg(CommandSender sender, String message)
	{
		sender.sendMessage(PREFIX + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', message));
	}

	/**
	 * Error message
	 */
	public static void error(CommandSender sender, String message)
	{
		sender.sendMessage(ERROR_PREFIX + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', message));
	}

	/**
	 * Messages all players on the server
	 */
	public static void broadcast(String message)
	{
		for (Player player : Bukkit.getOnlinePlayers())
			Utils.msg(player, message);

	}

	/**
	 * Generates a random vector that is more likely to go downwards than up
	 */
	public static Vector randomVector()
	{
		return new Vector(randomVectorDirection(), TenJava.RANDOM.nextFloat() * (TenJava.RANDOM.nextFloat() < 0.3 ? 1 : -1), randomVectorDirection());
	}

	/**
	 * Generates a random float between -1 and 1
	 */
	private static float randomVectorDirection()
	{
		return TenJava.RANDOM.nextFloat() * (TenJava.RANDOM.nextBoolean() ? 1 : -1);
	}

	/**
	 * Gets a random location within a given radius around a location
	 */
	public static Location getRandomRelativeLocation(Location loc, int radius)
	{
		Location relative = loc.clone();

		relative.add((TenJava.RANDOM.nextBoolean() ? 1 : -1) * TenJava.RANDOM.nextInt(radius), 0, (TenJava.RANDOM.nextBoolean() ? 1 : -1) * TenJava.RANDOM.nextInt(radius));
		return relative;
	}


	/**
	 * Simplifies a location into x,y,z
	 */
	public static String simplifyLocation(Location location)
	{
		return location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
	}

	/**
	 * Scatters a given amount of a given material
	 * Items cannot be picked up
	 */
	public static void scatterItems(Material material, int amount, Location location)
	{
		for (int i = 0; i < amount; i++)
		{
			Item item = location.getWorld().dropItemNaturally(location, new ItemStack(material));
			item.setMetadata("ETI-PICKUP", new FixedMetadataValue(TenJava.INSTANCE, true));
		}

	}

	/**
	 * Convenience method
	 */
	public static void playSound(Entity entity, Sound sound)
	{
		entity.getWorld().playSound(entity.getLocation(), sound, 1f, 1f);
	}

	/**
	 * Convenience method
	 */
	public static void playEffect(LivingEntity entity, Effect effect, int data)
	{
		entity.getWorld().playEffect(entity.getEyeLocation(), effect, data);
	}
}
