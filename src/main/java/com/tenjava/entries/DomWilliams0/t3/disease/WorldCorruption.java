package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Allows a spore cloud to corrupt the world
 */
public class WorldCorruption
{
	private int cooldown, maxCooldown;

	public WorldCorruption()
	{
		this.maxCooldown = 3;
		this.cooldown = maxCooldown;
	}

	public void tick(Location location)
	{
		if (--cooldown > 0)
			return;
		cooldown = TenJava.RANDOM.nextInt(maxCooldown) + 2;

		int rad = TenJava.RANDOM.nextInt(6);
		for (int x = -rad; x < rad; x++)
		{
			for (int y = -rad; y < rad; y++)
			{
				for (int z = -rad; z < rad; z++)
				{
					Block block = location.getBlock().getRelative(x, y, z);
					switch (block.getType())
					{
						case GRASS:
						case LONG_GRASS:
						case LEAVES:
						case LEAVES_2:
						case CROPS:
							if (TenJava.RANDOM.nextBoolean())
								affectBlock(block);
					}
				}
			}
		}
	}

	/**
	 * Corrupts a block
	 */
	private void affectBlock(Block block)
	{
		Material target;
		byte data = 0;
		switch (block.getType())
		{
			case GRASS:
				target = Material.DIRT;
				data = 2;
				break;
			case LONG_GRASS:
				target = Material.DEAD_BUSH;
				break;
			default:
				target = Material.AIR;
		}

		//		for (Player player : block.getWorld().getPlayers())
		//			player.sendBlockChange(block.getLocation(), target, data); // doesn't affect the world server-side
		block.setType(target);
		block.setData(data);

	}

}
