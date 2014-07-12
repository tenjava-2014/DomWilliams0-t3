package com.tenjava.entries.DomWilliams0.t3;

import com.tenjava.entries.DomWilliams0.t3.commands.CommandManager;
import com.tenjava.entries.DomWilliams0.t3.commands.MainCommand;
import com.tenjava.entries.DomWilliams0.t3.disease.DiseaseController;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
			public void testingListener(final PlayerInteractEvent event)
			{
				if (event.getPlayer().getItemInHand().getType() == Material.STICK)
				{

					new BukkitRunnable()
					{
						int i = 0;
						int[] hue = new int[] { 29, 48, 81};

						@Override
						public void run()
						{
							Effect effect = Effect.STEP_SOUND;
							event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), effect, hue[++i % 3]);
							event.getPlayer().sendMessage(effect.name() + i);

							// sticky piston, melon block, mossy cobble, grass
							// 103 31 29

							if (i >= 21)
							{
								i = 0;
								cancel();
							}

						}
					}.runTaskTimer(TenJava.INSTANCE, 0L, 5L);


				}
			}


		}, this);
	}

	private void init()
	{
		new DiseaseController();

		CommandManager manager = new CommandManager();
		manager.registerCommand("eti", new MainCommand());
	}

	@Override
	public void onDisable()
	{
		RANDOM = null;
		INSTANCE = null;
	}
}
