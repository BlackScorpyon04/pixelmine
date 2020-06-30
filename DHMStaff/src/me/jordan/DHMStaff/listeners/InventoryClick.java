package me.jordan.DHMStaff.listeners;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.jordan.DHMCore.Util.Utils;
import me.jordan.DHMStaff.Main;
import net.md_5.bungee.api.ChatColor;

public class InventoryClick implements Listener{

	private Main plugin;
	double total;

	public InventoryClick(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}


	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) throws IOException {
		String title;
		if (e.getInventory().getType() != InventoryType.PLAYER) {
			title = e.getInventory().getTitle();
		}else {
			title = "";
		}
		Player p = (Player) e.getWhoClicked();
		me.jordan.DHMCore.Main plug = me.jordan.DHMCore.Main.plugin;
		if (title.equalsIgnoreCase(Utils.color(plug.getConfig().getString("prefix") + " " +"&cPunish GUI"))) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.PAPER) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule1") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule1", 1);
					p.performCommand("warn -s " + name + " Rule #1 / Spam / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule1") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule1", 2);
					p.performCommand("mute -s " + name + " 1d Rule #1 / Spam / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule1") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule1", 3);
					p.performCommand("tempban -s " + name + " 7d Rule #1 / Spam / 3rd Offense");
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.BEACON) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule2") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule3", 1);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("tempban -s " + name + " 7d Rule #2 / Advertisment / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule2") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule2", 2);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("tempban -s " + name + " 30d Rule #2 / Advertisment / 2nd Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule2") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule2", 2);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("ban " + name + " Rule #2 / Advertisment / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule3") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule2", 4);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("ban " + name + " Rule #2 / Advertisment");
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.BARRIER) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule3") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule3", 1);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("tempban -s " + name + " 7d Rule #3 / Cheating or Exploiting / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule3") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule3", 2);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("tempban -s " + name + " 30d Rule #3 / Cheating or Exploiting / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule3") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule3", 3);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("ban " + name + " Rule #3 / Cheating or Exploiting / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule3") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule3", 3);
					plug.plrs.save(plug.plrsFile);
					p.performCommand("ban " + name + " Rule #3 / Cheating or Exploiting");
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule4") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule4", 1);
					p.performCommand("tempban -s " + name + " 7d Rule #4 / Racism/Sexism / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule4") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule4", 2);
					p.performCommand("tempban -s " + name + " 30d Rule #4 / Racism/Sexism / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule4") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule4", 3);
					p.performCommand("ban " + name + " Rule #4 / Racism/Sexism / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule4") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule4", 3);
					p.performCommand("ban " + name + " Rule #4 / Racism/Sexism");
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.GOLD_SWORD) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule5") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule5", 1);
					p.performCommand("mute -s " + name + " 7d Rule #5 / Threats / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule5") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule5", 2);
					p.performCommand("tempban -s " + name + " 30d Rule #5 / Threats / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule5") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule5", 3);
					p.performCommand("ban " + name + " Rule #5 / Threats / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule5") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule5", 3);
					p.performCommand("ban " + name + " Rule #5 / Threats");
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.RAW_FISH) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule6") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule6", 1);
					p.performCommand("warn -s " + name + " Rule #6 / Disrespect / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule6") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule6", 2);
					p.performCommand("mute -s " + name + " 1d Rule #6 / Disrespect / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule6") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule6", 3);
					p.performCommand("tempban -s " + name + " 7d Rule #6 / Disrespect / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule6") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule6", 3);
					p.performCommand("tempban -s " + name + " 7d Rule #6 / Disrespect");
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule7") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule7", 1);
					p.performCommand("warn -s " + name + " Rule #7 / Excessive Swearing / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule7") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule7", 2);
					p.performCommand("tempban -s " + name + " 7d Rule #7 / Excessive Swearing / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule7") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule7", 3);
					p.performCommand("tempban -s " + name + " 30d Rule #7 / Excessive Swearing / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule7") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule7", 3);
					p.performCommand("tempban -s " + name + " 30d Rule #7 / Excessive Swearing");
					
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}else if (e.getCurrentItem().getType() == Material.NETHER_STALK) {
				String name = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
				Player plr = Bukkit.getPlayer(name);
				UUID uuid = plr.getUniqueId();
				if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule8") == 0) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule8", 1);
					p.performCommand("tempban -s " + name + " 7d Rule #8 / Impersonation / 1st Offense");
				}else if (plug.plrs.getInt("Players." + uuid + ".Bans.Rule8") == 1) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule8", 2);
					p.performCommand("tempban -s " + name + " 30d Rule #8 / Impersonation / 2nd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule8") == 2) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule8", 3);
					p.performCommand("ban -s " + name + " Rule #8 / Impersonation / 3rd Offense");
				}else if(plug.plrs.getInt("Players." + uuid + ".Bans.Rule8") >= 3){
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + uuid + ".Bans.Rule8", 3);
					p.performCommand("ban -s " + name + " Rule #8 / Impersonation");
				}
				try {
					plug.plrs.save(plug.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.closeInventory();
			}
		}else if (title.equalsIgnoreCase(Utils.color(plug.getConfig().getString("prefix") + " " +"&cReport GUI"))) {
			switch (e.getCurrentItem().getType()) {
				case PAPER:
			
			}
		}
	}
	public static String charRemoveAt(String str, int p) {  
        return str.substring(0, p) + str.substring(p + 1);  
     }  

}
