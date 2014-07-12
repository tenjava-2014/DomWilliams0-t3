package com.tenjava.entries.DomWilliams0.t3.commands;

import com.tenjava.entries.DomWilliams0.t3.TenJava;
import com.tenjava.entries.DomWilliams0.t3.disease.sporeclouds.InfectionSporeCloud;
import com.tenjava.entries.DomWilliams0.t3.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class InfectCommand extends BaseCommand
{

	public InfectCommand()
	{
		super(false);
	}

	@Override
	boolean execute(CommandSender sender, String[] args)
	{
		if (args.length == 1)
		{
			Utils.msg(sender, "Punch an entity/player to infect them.");
			new PunchInfectListener(((Player) sender).getUniqueId());
			return true;
		}

		Player target = Bukkit.getPlayer(args[1]);
		if (target == null)
			Utils.error(sender, "That player is not online!");
		else
		{
			new InfectionSporeCloud(target);

		}

		return true;
	}

	class PunchInfectListener implements Listener
	{
		private UUID puncher;

		PunchInfectListener(UUID puncher)
		{
			this.puncher = puncher;
			Bukkit.getPluginManager().registerEvents(this, TenJava.INSTANCE);
		}

		@EventHandler
		public void onPunch(EntityDamageByEntityEvent event)
		{
			if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity)
			{
				Player player = (Player) event.getDamager();
				if (player.getUniqueId() == puncher)
				{
					new InfectionSporeCloud((LivingEntity) event.getEntity());
					HandlerList.unregisterAll(this);
				}
			}
		}


	}


}
