package me.jordan.DHMMining;

import org.bukkit.plugin.java.JavaPlugin;

import me.jordan.DHMMining.UI.ToggleSmeltUI;
import me.jordan.DHMMining.commands.ToggleSmelt;
import me.jordan.DHMMining.listeners.BlockBreak;
import me.jordan.DHMMining.listeners.InventoryClick;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		new InventoryClick(this);
		new BlockBreak(this);
		new ToggleSmelt(this);
		
		ToggleSmeltUI.initialize();
		
	}
}
