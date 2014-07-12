package com.tenjava.entries.DomWilliams0.t3;

import com.tenjava.entries.DomWilliams0.t3.commands.CommandManager;
import com.tenjava.entries.DomWilliams0.t3.commands.ToggleCommand;
import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import com.tenjava.entries.DomWilliams0.t3.disease.SporeCloud;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
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
		getServer().getPluginManager().registerEvents(new Listener()
		{
			@EventHandler
			public void aVoid(PlayerInteractEvent event)
			{
				if (event.getPlayer().getItemInHand().getType() == Material.STICK)
					new SporeCloud(event.getPlayer().getLocation());
			}


		}, this);
	}

	private void init()
	{
		new DiseaseController();
		CommandManager manager = new CommandManager();
		manager.registerCommand("eti", new ToggleCommand());
	}

	@Override
	public void onDisable()
	{
		RANDOM = null;
		INSTANCE = null;
	}
}
