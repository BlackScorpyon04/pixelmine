package me.jordan.DHMCore.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import me.jordan.DHMCore.Main;
import me.jordan.DHMCore.Util.Utils;

public class Reload implements CommandExecutor{
	
	private Main plugin;
	
	public Reload(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("dhmreload").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = plugin.getConfig().getString("prefix");
		String mainColor = plugin.getConfig().getString("mainColor");
		String secondaryColor = plugin.getConfig().getString("secondaryColor");
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.color(prefix + mainColor +" Core has been successfully" + secondaryColor + " Reloaded"));
			plugin.reloadConfig();
			try {
				plugin.plrs.load(plugin.plrsFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

		Player p = (Player) sender;
		
		if (p.hasPermission("dhm.admin")) {
			sender.sendMessage(Utils.color(prefix + mainColor +" Core has been successfully" + secondaryColor + " Reloaded"));
			plugin.reloadConfig();
			try {
				plugin.plrs.load(plugin.plrsFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
