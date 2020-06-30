package me.jordan.PMReports.listeners;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.jordan.PMCore.Util.Utils;
import me.jordan.PMReports.Main;
import net.md_5.bungee.api.ChatColor;

public class InventoryClick implements Listener{

	private Main plugin;
	double total;

	public InventoryClick(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}


	@EventHandler
	public void onClick(InventoryClickEvent e) throws IOException {
		String title = e.getInventory().getTitle();
		Player p = (Player) e.getWhoClicked();
		me.jordan.PMCore.Main plug = me.jordan.PMCore.Main.plugin;
		
	}

	
}
