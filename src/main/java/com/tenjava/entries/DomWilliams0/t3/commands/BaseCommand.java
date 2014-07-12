package com.tenjava.entries.DomWilliams0.t3.commands;

import org.bukkit.command.CommandSender;

public abstract class BaseCommand
{
	protected boolean consoleAllowed;

	protected BaseCommand(boolean consoleAllowed)
	{
		this.consoleAllowed = consoleAllowed;
	}

	abstract boolean execute(CommandSender sender, String[] args);

}
