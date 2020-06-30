package me.jordan.DHMOregen;

import org.bukkit.plugin.java.JavaPlugin;

import me.jordan.DHMOregen.listeners.BlockForm;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	
	
	public void onEnable() {
		plugin = this;
		
		new BlockForm(this);
	}	
}
