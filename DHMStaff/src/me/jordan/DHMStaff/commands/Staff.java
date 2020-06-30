package me.jordan.DHMStaff.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jordan.DHMCore.Util.Utils;
import me.jordan.DHMStaff.Main;

public class Staff implements CommandExecutor {
	
	private Main plugin;
	
	public Staff(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("staff").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("YOUR NOT A PLAYER CANT EXECUTE.");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission("dhm.staff")) {
			if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId().toString() + ".staffmode") == false) {
				me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId().toString() + ".staffmode", true);
				for (Player e : Bukkit.getServer().getOnlinePlayers()) {
					if(!e.hasPermission("pmc.staff")) {
						e.hidePlayer(p);
					}
				}
				p.sendMessage(Utils.color(me.jordan.DHMCore.Main.plugin.getConfig().getString("prefix") + " " +"&aStaff mode Activated!"));
				//Utils.createItem(p.getInventory(), Material.COBBLESTONE.toString(), 1, 3, Utils.color("&7Cobblestone"), "&2Drops Cobble");
//				p.openInventory(SellHeadsUI.GUI(p));
			}else {
				for (Player e : Bukkit.getServer().getOnlinePlayers()) {
					e.showPlayer(p);
				}
				me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId().toString() + ".staffmode", false);
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Utils.color(me.jordan.DHMCore.Main.plugin.getConfig().getString("prefix") + " " +"&cStaff mode De-Activated!"));
			}
			return true;
		}else {
			p.sendMessage(Utils.color(plugin.getConfig().getString("Messages.no_permission")));
			return true;
		}
	}
}
