package com.tenjava.entries.DomWilliams0.t3.util;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
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
		sender.sendMessage(PREFIX + message);
	}

	/**
	 * Error message
	 */
	public static void error(CommandSender sender, String message)
	{
		sender.sendMessage(ERROR_PREFIX + message);
	}

	public static Vector randomVector()
	{
		return new Vector(randomVectorDirection(), TenJava.RANDOM.nextFloat() * (TenJava.RANDOM.nextFloat() < 0.2 ? 1 : -1), randomVectorDirection());
	}

	public static Block getAboveOrBelow(Block block, boolean above)
	{
		return block.getRelative(0, above ? 1 : -1, 0);
	}

	private static float randomVectorDirection()
	{
		return TenJava.RANDOM.nextFloat() * (TenJava.RANDOM.nextBoolean() ? 1 : -1);
	}


}
