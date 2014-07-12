package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements BaseCommand
{
	@Override
	public boolean execute(CommandSender sender, String[] args)
	{

		DiseaseController dc = DiseaseController.INSTANCE;
		if (dc.isRunning())
			dc.stop();
		else dc.start();

		Utils.msg(sender, "now " + dc.isRunning());


		return true;
	}
}
