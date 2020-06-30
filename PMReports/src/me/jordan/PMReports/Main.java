package me.jordan.PMReports;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.jordan.PMReports.listeners.InventoryClick;
import me.jordan.PMStaff.UI.ReportUI;
import me.jordan.PMStaff.commands.Report;

public class Main extends JavaPlugin{

	public FileConfiguration reports;
	public File reportFile;
	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		new Report(this);
		new InventoryClick(this);
		
		ReportUI.initialize();
		
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("PMCore")) {
			getServer().getConsoleSender().sendMessage("[PMPunish] Found PMCore Hooking In!");
		}
		
		File file = new File(this.getDataFolder(),"reports.yml");

		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create the players.yml");
			}
		}
		FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
		fileConfiguration.options().copyDefaults(true);

		try {
			fileConfiguration.save(file);
			fileConfiguration.load(file);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		reportFile = file;
		reports = fileConfiguration;
	}
}
