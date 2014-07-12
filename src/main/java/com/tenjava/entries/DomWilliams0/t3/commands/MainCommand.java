package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseInitiator;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements BaseCommand
{
	@Override
	public boolean execute(CommandSender sender, String[] args)
	{

		if (args.length == 0)
		{
			DiseaseController dc = DiseaseController.INSTANCE;
			if (dc.isRunning())
				dc.stop();
			else
				dc.start();

			Utils.msg(sender, "Sporecloud movement is now " + (dc.isRunning() ? "enabled" : "disabled"));
		}

		else if (args[0].equalsIgnoreCase("begin"))
		{
			Utils.msg(sender, "Releasing the spores...");
			new DiseaseInitiator(sender instanceof Player ? (Player) sender : null);
		}
		else if (args[0].equalsIgnoreCase("go") && sender instanceof Player)
		{
			Utils.msg(sender, "Releasing the spores in front of you!");
			DiseaseInitiator.release(((Player) sender).getLocation());
		}


		return true;
	}
}
