package me.jordan.DHMHeads.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import me.jordan.DHMHeads.Main;
import me.jordan.DHMCore.Util.Utils;
import net.md_5.bungee.api.ChatColor;

public class RightClick implements Listener{

	private Main plugin;
	private double total;
	
	public RightClick(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
    	String prefix = me.jordan.DHMCore.Main.plugin.getConfig().getString("prefix");
		String mainColor = me.jordan.DHMCore.Main.plugin.getConfig().getString("mainColor");
		String secondaryColor = me.jordan.DHMCore.Main.plugin.getConfig().getString("secondaryColor");
    	Player p = e.getPlayer();	
    	Material holding = e.getMaterial();
    	if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
    		if (holding == Material.SKULL_ITEM) {
    			e.setCancelled(true);
    			total = 0;
    			for(int i = 0 ; i < 36 ; i++) {
    				if (p.getInventory().getItem(i) == null) {
    					
    				}else {
    					if (p.getInventory().getItem(i).getType() != Material.SKULL_ITEM) {
        				}else {
        					ItemMeta m = p.getInventory().getItem(i).getItemMeta();
        					List<String> lore = m.getLore();
        					String line = lore.get(0);
        					line = line.replaceAll("\n", "").replaceAll("\r", "");
        					line = ChatColor.stripColor(line);
        					line = line.replaceAll("[^\\d.]", "");
        					double cost = Double.valueOf(line.replaceAll("[^\\d.]", ""));
        					cost = cost * p.getInventory().getItem(i).getAmount();
        					p.getInventory().setItem(i, null);
        					p.updateInventory();
        					total = total + cost;
        					me.jordan.DHMCore.Main.economy.depositPlayer(p.getName(), cost);
        				}
    				}
    			}
    			double seconds = .5;
    			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
    			p.closeInventory();
    			p.sendMessage(Utils.color(prefix + mainColor + " trade accepted:" + secondaryColor + "$" +total));
    		}
    	}
    }
}
