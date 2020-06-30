package me.jordan.DHMHeads;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.jordan.DHMHeads.listeners.DeathEvent;
import me.jordan.DHMHeads.listeners.RightClick;

public class Main extends JavaPlugin{

	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		new RightClick(this);
		new DeathEvent(this);
		
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("DHMCore")) {
			getServer().getConsoleSender().sendMessage("[DHMHeads] Found DHMCore Hooking In!");
		}
	}
}
