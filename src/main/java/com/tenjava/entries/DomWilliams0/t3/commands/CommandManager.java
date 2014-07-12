package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor
{
	private Map<String, BaseCommand> commandMap;

	public CommandManager()
	{
		this.commandMap = new HashMap<String, BaseCommand>();
	}

	public void registerCommands()
	{
		TenJava.INSTANCE.getCommand("eti").setExecutor(this);
		commandMap.put("help", new HelpCommand());
		commandMap.put("toggle", new ToggleCommand());
		commandMap.put("destroy", new DestroyCommand());
		commandMap.put("spawn", new SpawnCommand());
		commandMap.put("infect", new InfectCommand());
		commandMap.put("immune", new ImmuneCommand());
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String command = args.length == 0 ? "help" : args[0].toLowerCase();

		boolean isPlayer = sender instanceof Player;
		BaseCommand bcmd = commandMap.get(command);

		if (bcmd == null)
		{
			Utils.error(sender, "Command not found!");
			return true;
		}
		if (!isPlayer && !bcmd.consoleAllowed) // player only commands
		{
			Utils.error(sender, "Only players can do that!");
			return true;
		}

		if (!sender.hasPermission("eti." + command)) // permission check
		{
			Utils.error(sender, "You don't have permission to do that!");
			return true;
		}

		bcmd.execute(sender, args);

		return true;
	}

}
