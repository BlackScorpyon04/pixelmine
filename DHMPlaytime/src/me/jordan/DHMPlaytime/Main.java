
package me.jordan.DHMPlaytime;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.jordan.DHMPlaytime.commands.Playtime;
import me.jordan.DHMPlaytime.commands.PlaytimeTop;
import me.jordan.DHMPlaytime.listeners.Leave;

public class Main extends JavaPlugin{

	public static Main plugin;
	public FileConfiguration playtime;
	public File playtimeFile;
	
	public void onEnable() {
		plugin = this;
		
		new Playtime(this);
		new PlaytimeTop(this);
		new Leave(this);
		
		File filept = new File(this.getDataFolder(),"pt.yml");

		if(!filept.exists()) {
			try {
				filept.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create the players.yml");
			}
		}
		FileConfiguration fileConfigurationpt = YamlConfiguration.loadConfiguration(filept);

		try {
			fileConfigurationpt.save(filept);
			fileConfigurationpt.load(filept);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		playtimeFile = filept;
		playtime = fileConfigurationpt;

		if (Bukkit.getServer().getPluginManager().isPluginEnabled("DHMCore")) {
			getServer().getConsoleSender().sendMessage("[DHMPlaytime] Found DHMCore Hooking In!");
		}
	}

}
