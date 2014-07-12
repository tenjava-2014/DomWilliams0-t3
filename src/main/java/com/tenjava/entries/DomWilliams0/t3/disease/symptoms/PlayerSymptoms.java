package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class PlayerSymptoms extends BaseSymptoms
{
	private Player player; // to prevent constant casting in tick()
	private static final List<PotionEffect> EFFECTS;

	static
	{
		EFFECTS = Arrays.asList(new PotionEffect(PotionEffectType.CONFUSION, 30, 5), new PotionEffect(PotionEffectType.BLINDNESS, 35, 5));
	}

	protected PlayerSymptoms(LivingEntity entity)
	{
		super(entity);
		player = (Player) entity;
	}

	@Override
	public void tick()
	{
		player.addPotionEffects(EFFECTS);
		player.damage(0);
	}

	@Override
	public void die()
	{
		player.setVelocity(new Vector(0, 1.5, 0));
		player.removePotionEffect(PotionEffectType.CONFUSION);
		player.removePotionEffect(PotionEffectType.BLINDNESS);
	}
}
