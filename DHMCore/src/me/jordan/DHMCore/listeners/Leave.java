package me.jordan.DHMCore.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jordan.DHMCore.Main;
import me.jordan.DHMCore.Util.Utils;

public class Leave implements Listener{
	
	private Main plugin;
	
	public Leave(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) throws IOException, InvalidConfigurationException {
		
		Player p = e.getPlayer();
		if (!p.hasPermission("dhm.staff")) {
			e.setQuitMessage(Utils.color("&8[&C-&8] &7" + p.getDisplayName()));
		}else {
			if (plugin.plrs.getBoolean("Players." + p.getUniqueId().toString() + ".staffmode") == false) {
				e.setQuitMessage(Utils.color("&8[&C-&8] &7" + p.getDisplayName()));
			} else {
				e.setQuitMessage("");
			}
		}
	}
}
