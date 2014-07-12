package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ImmuneCommand extends BaseCommand
{

	public ImmuneCommand()
	{
		super(false);
	}

	@Override
	boolean execute(CommandSender sender, String[] args)
	{
		Player target = args.length == 1 ? (Player) sender : Bukkit.getPlayer(args[1]);

		if (target == null)
		{
			Utils.error(sender, "That player is not online!");
			return true;
		}

		boolean result = DiseaseController.INSTANCE.toggleImmunity(target);
		Utils.msg(sender, (target == sender ? "You are " : target.getName() + " is ") + (result ? "&2now immune" : "&4no longer immune") + "&7!");


		return true;
	}

}
