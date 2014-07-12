package com.tenjava.entries.DomWilliams0.t3.util;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Effects
{
	private static int[] sporeColours = new int[]{29, 48, 81};

	public static void sporeEffect(Location location, int radius)
	{
		System.out.println("DING");
		World world = location.getWorld();

		world.playEffect(location, Effect.SMOKE, TenJava.RANDOM.nextInt(8));

		int rad = TenJava.RANDOM.nextInt(radius);
		for (int i = 1; i <= rad; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				Block b = location.getBlock().getRelative(BlockFace.values()[TenJava.RANDOM.nextInt(BlockFace.values().length)], rad);
				world.playEffect(b.getLocation(), Effect.SMOKE, TenJava.RANDOM.nextInt(8));
				world.playEffect(b.getLocation(), Effect.STEP_SOUND, sporeColours[TenJava.RANDOM.nextInt(sporeColours.length)]);
			}
		}
	}


}
