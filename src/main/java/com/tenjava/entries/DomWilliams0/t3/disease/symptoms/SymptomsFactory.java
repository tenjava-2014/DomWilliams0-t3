package com.tenjava.entries.DomWilliams0.t3.disease.symptoms;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import org.bukkit.entity.LivingEntity;

public class SymptomsFactory
{

	public static BaseSymptoms produceSymptoms(LivingEntity entity)
	{
		if (!DiseaseController.INSTANCE.canBeInfected(entity))
			throw new IllegalArgumentException("Cannot be infected - " + entity.getType());

		switch (entity.getType())
		{
			case MUSHROOM_COW:
			case COW:
				return new CowSymptoms(entity);
			case SHEEP:
				return new SheepSymptoms(entity);
			case CHICKEN:
				return new ChickenSymptoms(entity);
			case WOLF:
				return new WolfSymptoms(entity);
			case PIG:
				return new PigSymptoms(entity);
			case PLAYER:
				return new PlayerSymptoms(entity);
			default:
				return null;
		}

	}


}
