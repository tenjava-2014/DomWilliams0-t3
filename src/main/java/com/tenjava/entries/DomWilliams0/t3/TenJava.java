package com.tenjava.entries.DomWilliams0.t3;

import com.tenjava.entries.DomWilliams0.t3.commands.CommandManager;
import com.tenjava.entries.DomWilliams0.t3.commands.MainCommand;
import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.disease.HealthTracker;
import com.tenjava.entries.DomWilliams0.t3.listeners.GeneralListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TenJava extends JavaPlugin
{

	public static TenJava INSTANCE;
	public static Random RANDOM = new Random();

	@Override
	public void onEnable()
	{
		INSTANCE = this;
		init();
	}

	private void init()
	{
		new DiseaseController();
		new HealthTracker();

		CommandManager manager = new CommandManager();
		manager.registerCommand("eti", new MainCommand());

		getServer().getPluginManager().registerEvents(new GeneralListener(), this);
	}

	@Override
	public void onDisable()
	{
		RANDOM = null;
		INSTANCE = null;
	}
}
