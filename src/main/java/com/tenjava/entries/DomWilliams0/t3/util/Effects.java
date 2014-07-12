package com.tenjava.entries.DomWilliams0.t3.util;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;

public class Effects
{
	//	private static int[] sporeColours = new int[]{29, 48, 81};
	private static Sound[] sounds = new Sound[]{Sound.AMBIENCE_CAVE, Sound.FIRE, Sound.FIZZ, Sound.PORTAL_TRAVEL, Sound.CAT_HISS, Sound.CAT_PURR, Sound.WITHER_IDLE};

	/**
	 * Plays the floating spore cloud effect
	 */
	public static void sporeEffect(Location location, int radius)
	{
		World world = location.getWorld();

		for (int i = 0; i < 12; i++)
			world.playEffect(location, Effect.SMOKE, TenJava.RANDOM.nextInt(8));

		int rad = TenJava.RANDOM.nextInt(radius);

		for (int i = 1; i <= rad; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				Location l = Utils.getRandomRelativeLocation(location, radius);
				world.playEffect(l, Effect.SMOKE, TenJava.RANDOM.nextInt(8));
			}
		}
		world.playSound(location, sounds[TenJava.RANDOM.nextInt(sounds.length)], 0.4f, 1f);
	}

	/**
	 * Plays a blood effect
	 */
	public static void infectedEffect(LivingEntity entity)
	{
		if (TenJava.RANDOM.nextFloat() < 0.8)
			entity.getWorld().playEffect(entity.getEyeLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK.getId());
	}

	/**
	 * Plays a hurt sound depending on the EntityType
	 */
	public static void ouchSound(LivingEntity entity)
	{
		if (TenJava.RANDOM.nextFloat() < 0.8)
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

		Utils.playSound(entity, sound);
	}


}
