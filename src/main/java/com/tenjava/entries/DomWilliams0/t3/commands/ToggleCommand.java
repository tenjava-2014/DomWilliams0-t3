package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.CommandSender;

public class ToggleCommand extends BaseCommand
{
	public ToggleCommand()
	{
		super(true);
	}

	@Override
	boolean execute(CommandSender sender, String[] args)
	{
		DiseaseController dc = DiseaseController.INSTANCE;
		if (dc.isRunning())
			dc.stop();
		else
			dc.start();
		String status = (dc.isRunning() ? "&2activated" : "&4disactivated");

		Utils.msg(sender, "Sporecloud movement has been " + status + "&7.");

		return true;
	}
}
