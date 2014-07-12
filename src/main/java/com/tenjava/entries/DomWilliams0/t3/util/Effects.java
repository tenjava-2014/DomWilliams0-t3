package com.tenjava.entries.DomWilliams0.t3.util;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

public class Effects
{
	private static int[] sporeColours = new int[]{29, 48, 81};

	public static void sporeEffect(Location location, int radius, boolean withStep)
	{
		World world = location.getWorld();

		for (int i = 0; i < 4; i++)
			world.playEffect(location, Effect.SMOKE, TenJava.RANDOM.nextInt(8));

		int rad = TenJava.RANDOM.nextInt(radius);

		for (int i = 1; i <= rad; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				Location l = Utils.getRandomRelativeLocation(location, radius);
				world.playEffect(l, Effect.SMOKE, TenJava.RANDOM.nextInt(8));
				if (withStep)
					world.playEffect(l, Effect.STEP_SOUND, sporeColours[TenJava.RANDOM.nextInt(sporeColours.length)]);
			}
		}
	}

	public static void ouchSound(LivingEntity entity)
	{
		if (TenJava.RANDOM.nextFloat() < 0.6)
			return;

		Sound sound;
		switch (entity.getType())
		{
			case COW:
			case MUSHROOM_COW:
				sound = Sound.COW_HURT;
				break;
			case SHEEP:
				sound = Sound.SHEEP_IDLE;
				break;
			case CHICKEN:
				sound = Sound.CHICKEN_HURT;
				break;
			case WOLF:
				sound = Sound.WOLF_HURT;
				break;
			case PIG:
				sound = Sound.PIG_IDLE;
				break;
			case PLAYER:
				sound = Sound.HURT_FLESH;
				break;
			default:
				sound = Sound.HURT_FLESH;
		}

		entity.getWorld().playSound(entity.getLocation(), sound, 1f, 1f);


	}


	public static void kill(LivingEntity entity)
	{







	}
}
