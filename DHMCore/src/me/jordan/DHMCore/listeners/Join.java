package me.jordan.DHMCore.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jordan.DHMCore.Main;
import me.jordan.DHMCore.Util.Utils;

public class Join implements Listener{
	
	private Main plugin;
	
	public Join(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {
		Player p = e.getPlayer();
		
		if (!p.hasPlayedBefore()) {
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Iron", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Gold", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Stone", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Cobble", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule1", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule2", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule3", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule4", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule5", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule6", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule7", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule8", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule9", 0);
			if(p.hasPermission("dhm.staff")) {
				plugin.plrs.set("Players." + p.getUniqueId() + ".Staff", true);
			}
		} else if (!plugin.plrs.contains("Players." + p.getUniqueId())){
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Iron", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Gold", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Stone", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Cobble", true);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule1", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule2", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule3", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule4", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule5", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule6", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule7", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule8", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".Bans.Rule9", 0);
			if(p.hasPermission("dhm.staff")) {
				plugin.plrs.set("Players." + p.getUniqueId() + ".Staff", true);
			}else {
				plugin.plrs.set("Players." + p.getUniqueId() + ".Staff", false);
			}
		}
		if (!plugin.plrs.contains("Players." + p.getUniqueId() + ".reports")){
			plugin.plrs.set("Players." + p.getUniqueId() + ".claimedreports", 0);
			plugin.plrs.set("Players." + p.getUniqueId() + ".claimedReport", false);
		}
		if (p.hasPermission("dhm.staff")) {
			plugin.plrs.set("Players." + p.getUniqueId() + ".Staff", true);
			e.setJoinMessage("");
			plugin.plrs.set("Players." + p.getUniqueId().toString() + ".staffmode", true);
			for (Player plr : Bukkit.getOnlinePlayers()) {
				if (!plr.hasPermission("dhm.staff")) {
					plr.hidePlayer(p);
				}
			}
		}else {
			plugin.plrs.set("Players." + p.getUniqueId() + ".Staff", false);
			e.setJoinMessage(Utils.color("&8[&a+&8] &7" + p.getDisplayName()));
			for (Player plr : Bukkit.getOnlinePlayers()) {
				if (plugin.plrs.getBoolean("Players." + plr.getUniqueId().toString() + ".staffmode") == true) {
					p.hidePlayer(plr);
				}
			}
		}
		try {
			plugin.plrs.save(plugin.plrsFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		plugin.plrs.set("Players." + p.getUniqueId() +".Accounts", 1);
		plugin.plrs.save(plugin.plrsFile);
		for (Player plr : plugin.getServer().getOnlinePlayers()) {
			if (p != plr) {
				if (p.getAddress().getHostString().equals(plr.getAddress().getHostString())) {
					plugin.plrs.set("Players." + p.getUniqueId() +".Accounts", plugin.plrs.getInt("Players." + p.getUniqueId() +".Accounts") + 1);
					plugin.plrs.save(plugin.plrsFile);
					if (plugin.plrs.getInt("Players." + p.getUniqueId() +".Accounts") == 2){
						p.sendMessage(Utils.color(plugin.getConfig().getString("prefix") + " " +"&cWatch your account limit remember only 3 accounts per ip or you will get perm ban"));
					}
					if (plugin.plrs.getInt("Players." + p.getUniqueId() +".Accounts") > 3) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ipban -s " + p.getName() + " Rule #9 / Alt Limit");
					}
				}
			}
			
		}
	}
}