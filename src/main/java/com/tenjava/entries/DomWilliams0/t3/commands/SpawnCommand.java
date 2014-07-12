package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseInitiator;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends BaseCommand
{

	public SpawnCommand()
	{
		super(false);
	}

	@Override
	boolean execute(CommandSender sender, String[] args)
	{
		Player player = (Player) sender; // certain they are a player due to 'consoleAllowed' being set to false
		DiseaseInitiator.release(player.getLocation());

		if (!DiseaseController.INSTANCE.isRunning())
			DiseaseController.INSTANCE.start();

		Utils.msg(sender, "The spores have been released and globally activated! &4&oRun!");

		return true;
	}
}
