package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import org.bukkit.entity.LivingEntity;

public abstract class BaseSymptoms
{
	protected LivingEntity entity;

	protected BaseSymptoms(LivingEntity entity)
	{
		this.entity = entity;
	}

	private BaseSymptoms()
	{
	}

	public abstract void tick();

	public abstract void die();

}

