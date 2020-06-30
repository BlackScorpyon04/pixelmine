package me.jordan.DHMMining.listeners;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.jordan.DHMCore.Util.Utils;
import me.jordan.DHMMining.Main;
import me.jordan.DHMMining.UI.ToggleSmeltUI;

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
		if(title.equalsIgnoreCase(Utils.color(me.jordan.DHMCore.Main.plugin.getConfig().getString("AutoSmeltGui.Name")))) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.IRON_INGOT) {
				me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Iron", false);
				p.closeInventory();
				p.openInventory(ToggleSmeltUI.GUI(p));
				try {
					me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getCurrentItem().getType() == Material.IRON_ORE) {
				me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Iron", true);
				p.closeInventory();
				p.openInventory(ToggleSmeltUI.GUI(p));
				try {
					me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getCurrentItem().getType() == Material.GOLD_INGOT) {
				me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Gold", false);
				p.closeInventory();
				p.openInventory(ToggleSmeltUI.GUI(p));
				try {
					me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getCurrentItem().getType() == Material.GOLD_ORE) {
				me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Gold", true);
				p.closeInventory();
				p.openInventory(ToggleSmeltUI.GUI(p));
				try {
					me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getCurrentItem().getType() == Material.COBBLESTONE) {
				if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Cobble") == true) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Cobble", false);
					p.closeInventory();
					p.openInventory(ToggleSmeltUI.GUI(p));
					try {
						me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Cobble", true);
					p.closeInventory();
					p.openInventory(ToggleSmeltUI.GUI(p));
					try {
						me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}else if(e.getCurrentItem().getType() == Material.STONE) {
				if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Stone") == true) {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Stone", false);
					p.closeInventory();
					p.openInventory(ToggleSmeltUI.GUI(p));
					try {
						me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					me.jordan.DHMCore.Main.plugin.plrs.set("Players." + p.getUniqueId() + ".AutoSmelt.Stone", true);
					p.closeInventory();
					p.openInventory(ToggleSmeltUI.GUI(p));
					try {
						me.jordan.DHMCore.Main.plugin.plrs.save(me.jordan.DHMCore.Main.plugin.plrsFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		}
	}
}
