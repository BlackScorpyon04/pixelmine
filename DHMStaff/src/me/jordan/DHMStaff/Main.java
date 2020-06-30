package me.jordan.DHMStaff;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.jordan.DHMStaff.UI.PunishUI;
import me.jordan.DHMStaff.UI.ReportUI;
import me.jordan.DHMStaff.commands.Punish;
import me.jordan.DHMStaff.commands.Staff;
import me.jordan.DHMStaff.listeners.InventoryClick;

public class Main extends JavaPlugin{

	public FileConfiguration reports;
	public File reportFile;
	public static Main plugin;
	public static me.jordan.DHMCore.Main pmcore;
	
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		new Punish(this);
		new Staff(this);
		new InventoryClick(this);
		
		PunishUI.initialize();
		ReportUI.initialize();
		
		File filept = new File(this.getDataFolder(),"reports.yml");

		if(!filept.exists()) {
			try {
				filept.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create the reports.yml");
			}
		}
		FileConfiguration fileConfigurationpt = YamlConfiguration.loadConfiguration(filept);

		try {
			fileConfigurationpt.save(filept);
			fileConfigurationpt.load(filept);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		reportFile = filept;
		reports = fileConfigurationpt;
	}
	
	public void saveReportsFile() {
		try {
			reports.save(reportFile);
			reports.load(reportFile);
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
