package com.tenjava.entries.DomWilliams0.t3.disease;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SporeCloud implements Disease
{
	private static final int MAX_SIZE = 12;

	//	protected Set<Location> locations;
	private Location centre;
	private Vector direction;
	private int lastDirectionChange;

	public SporeCloud(Location location)
	{
		this.centre = location;
		this.direction = Utils.randomVector();
		this.lastDirectionChange = 0;

		DiseaseController.INSTANCE.sporeClouds.add(this);
	}

	@Override
	public void tick()
	{
		move();
		playEffect();
	}

	private void playEffect()
	{
		centre.getWorld().playEffect(centre, Effect.SMOKE, TenJava.RANDOM.nextInt(8));

		int rad = TenJava.RANDOM.nextInt(4);
		for (int i = 1; i <= rad; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				Block b = centre.getBlock().getRelative(BlockFace.values()[TenJava.RANDOM.nextInt(BlockFace.values().length)], rad);
				b.getWorld().playEffect(b.getLocation(), Effect.SMOKE, TenJava.RANDOM.nextInt(8));
			}
		}
	}

	private void move()
	{
		// randomly change direction
		if (--lastDirectionChange <= 0)
		{
			direction = Utils.randomVector();
			lastDirectionChange = TenJava.RANDOM.nextInt(5) + 3;
			return;
		}

		// get path
		BlockIterator iterator = new BlockIterator(centre.getWorld(), centre.toVector(), direction, 0, 2);
		List<Block> blocks = new ArrayList<Block>();
		while (iterator.hasNext())
			blocks.add(iterator.next());

		for (int i = 0; i < blocks.size() - 1; i++)
		{
			Block block = blocks.get(i);
			Block next = blocks.get(i + 1);

			// hit wall
			if (block.getType().isTransparent() && !next.getType().isTransparent())
			{
				direction = Utils.randomVector();
				return;
			}

			blocks.set(i + 1, next);
		}

		centre = blocks.get(blocks.size() - 1).getLocation();
	}
}
