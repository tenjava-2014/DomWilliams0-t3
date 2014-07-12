package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.CommandSender;

public class DestroyCommand extends BaseCommand
{

	public DestroyCommand()
	{
		super(true);
	}

	@Override
	boolean execute(CommandSender sender, String[] args)
	{
		int count = DiseaseController.INSTANCE.end();
		Utils.msg(sender, "All &2" + count + "&7 infection(s) have been destroyed.");

		return true;
	}
}
