package com.tenjava.entries.DomWilliams0.t3.commands;


import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.CommandSender;

public class HelpCommand extends BaseCommand
{

	public HelpCommand()
	{
		super(true);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args)
	{

		//		if (args.length == 0)
		//		{
		//			DiseaseController dc = DiseaseController.INSTANCE;
		//			if (dc.isRunning())
		//				dc.stop();
		//			else
		//				dc.start();
		//
		//			Utils.msg(sender, "Sporecloud movement is now " + (dc.isRunning() ? "enabled" : "disabled"));
		//		}
		//
		//		else if (args[0].equalsIgnoreCase("begin"))
		//		{
		//			Utils.msg(sender, "Releasing the spores...");
		//			new DiseaseInitiator(sender instanceof Player ? (Player) sender : null);
		//		}
		//		else if (args[0].equalsIgnoreCase("go") && sender instanceof Player)
		//		{
		//			Utils.msg(sender, "Releasing the spores in front of you!");
		//			DiseaseInitiator.release(((Player) sender).getLocation());
		//		}

		Utils.msg(sender, "&8--- &bExtraTerrestrialInfection Help &8---");

		Utils.msg(sender, "&o&8These <params> are optional");
		Utils.msg(sender, "&7/eti &8- &3This help menu");
		Utils.msg(sender, "&7/eti toggle &8- &3(Dis)activate all infections in the world");
		Utils.msg(sender, "&7/eti destroy &8- &3Destroy all infections");
		Utils.msg(sender, "&7/eti spawn &8- &3Spawn a spore cloud at your position");
		Utils.msg(sender, "&7/eti infect <player> &8- &3Infect a player");
		Utils.msg(sender, "&7/eti immune <player> &8- &3Vaccinate yourself/a player");

		Utils.msg(sender, "&8--- &bEnd of Help &8---");


		return true;
	}
}
