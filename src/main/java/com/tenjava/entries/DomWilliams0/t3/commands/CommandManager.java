package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor
{
	private Map<String, BaseCommand> commandMap;

	public CommandManager()
	{
		this.commandMap = new HashMap<String, BaseCommand>();
	}

	public void registerCommand(String cmd, BaseCommand executor)
	{
		commandMap.put(cmd.toLowerCase(), executor);
		TenJava.INSTANCE.getCommand("eti").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String command = cmd.getName().toLowerCase();
		BaseCommand bcmd = commandMap.get(command);

		if (bcmd == null)
			Utils.error(sender, "Command not found!");
		else
			bcmd.execute(sender, args);

		return true;
	}
}
