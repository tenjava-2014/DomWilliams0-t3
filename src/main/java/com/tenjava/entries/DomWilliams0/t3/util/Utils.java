package com.tenjava.entries.DomWilliams0.t3.util;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Utils
{
	public static BlockFace angleToBlockFace(float yaw)
	{
		int angle = Math.abs(45 * (Math.round(yaw / 45)));
		BlockFace blockFace;

		switch (angle)
		{
			case 0:
			case 360:
				blockFace = BlockFace.SOUTH;
				break;
			case 45:
				blockFace = BlockFace.SOUTH_WEST;
				break;
			case 90:
				blockFace = BlockFace.WEST;
				break;
			case 135:
				blockFace = BlockFace.NORTH_WEST;
				break;
			case 180:
				blockFace = BlockFace.NORTH;
				break;
			case 225:
				blockFace = BlockFace.NORTH_EAST;
				break;
			case 270:
				blockFace = BlockFace.EAST;
				break;
			case 315:
				blockFace = BlockFace.SOUTH_EAST;
				break;
			default:
				blockFace = BlockFace.SOUTH;
		}

		return blockFace.getOppositeFace();
	}

	private static final String PREFIX = "§8<§2ETI§8> §r";
	private static final String ERROR_PREFIX = "§8<§cETI§8> §r";

	/**
	 * Standard message
	 */
	public static void msg(CommandSender sender, String message)
	{
		sender.sendMessage(PREFIX + ChatColor.translateAlternateColorCodes('&', message));
	}

	/**
	 * Error message
	 */
	public static void error(CommandSender sender, String message)
	{
		sender.sendMessage(ERROR_PREFIX + ChatColor.translateAlternateColorCodes('&', message));
	}

	public static void broadcast(String message)
	{
		for (Player player : Bukkit.getOnlinePlayers())
			Utils.msg(player, message);

	}

	public static Vector randomVector()
	{
		return new Vector(randomVectorDirection(), TenJava.RANDOM.nextFloat() * (TenJava.RANDOM.nextFloat() < 0.4 ? 1 : -1), randomVectorDirection());
	}

	private static float randomVectorDirection()
	{
		return TenJava.RANDOM.nextFloat() * (TenJava.RANDOM.nextBoolean() ? 1 : -1);
	}

	public static Location getRandomRelativeLocation(Location loc, int radius)
	{
		Location relative = loc.clone();

		relative.add((TenJava.RANDOM.nextBoolean() ? 1 : -1) * TenJava.RANDOM.nextInt(radius), 0, (TenJava.RANDOM.nextBoolean() ? 1 : -1) * TenJava.RANDOM.nextInt(radius));
		return relative;
	}


	public static String simplifyLocation(Location location)
	{
		return location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
	}

	public static void scatterItems(Material material, int amount, Location location)
	{
		for (int i = 0; i < amount; i++)
			location.getWorld().dropItemNaturally(location, new ItemStack(material));
	}

	public static void playSound(Entity entity, Sound sound)
	{
		entity.getWorld().playSound(entity.getLocation(), sound, 1f, 1f);
	}

	public static void playEffect(LivingEntity entity, Effect effect, int data)
	{
		entity.getWorld().playEffect(entity.getEyeLocation(), effect, data);
	}
}
