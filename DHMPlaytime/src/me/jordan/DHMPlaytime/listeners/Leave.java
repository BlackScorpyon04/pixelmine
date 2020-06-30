package me.jordan.DHMPlaytime.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jordan.DHMPlaytime.Main;

public class Leave implements Listener {
	
	private Main plugin;
	
	public Leave(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) throws IOException, InvalidConfigurationException {
		int time = e.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK)/ 60 / 20;
		plugin.playtime.set(e.getPlayer().getUniqueId().toString(), time);
		plugin.playtime.save(plugin.playtimeFile);
		plugin.playtime.load(plugin.playtimeFile);
	}
	
}
