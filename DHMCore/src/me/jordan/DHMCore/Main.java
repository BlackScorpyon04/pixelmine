package me.jordan.DHMCore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.jordan.DHMCore.commands.Reload;
import me.jordan.DHMCore.listeners.Join;
import me.jordan.DHMCore.listeners.Leave;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin{
	
	public static Economy economy = null;
	public static Main plugin;
	public static WorldGuardPlugin wplugin;
	public FileConfiguration plrs;
	public File plrsFile;
	
	
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		new Join(this);
		new Leave(this);
		new Reload(this);
		if (setupEconomy()) {
			getServer().getConsoleSender().sendMessage("[DHMCore] Found Vault Hooking into economy!");
		}
		
		File filep = new File(this.getDataFolder(),"players.yml");

		if(!filep.exists()) {
			try {
				filep.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create the players.yml");
			}
		}
		FileConfiguration fileConfigurationp = YamlConfiguration.loadConfiguration(filep);
		fileConfigurationp.options().copyDefaults(true);

		try {
			fileConfigurationp.save(filep);
			fileConfigurationp.load(filep);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		plrsFile = filep;
		plrs = fileConfigurationp;
		
		wplugin = getWorldGuard();
	}
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}

      
	
}
