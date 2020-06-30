package me.jordan.DHMMining.UI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.jordan.DHMCore.Main;
import me.jordan.DHMCore.Util.Utils;

public class ToggleSmeltUI {
	
	public static Inventory inv;
	public static String name;
	public static int inv_rows = 27;
	public static void initialize() {
		if (!Main.plugin.getConfig().contains("AutoSmeltGui")) {
			Main.plugin.getConfig().set("AutoSmeltGui.Name", "&b&lToggle AutoSmelt");
			Main.plugin.saveConfig();
		}
		name = Utils.color(Main.plugin.getConfig().getString("AutoSmeltGui.Name"));
		inv = Bukkit.createInventory(null, inv_rows);
	}
	
	public static Inventory GUI(Player p) {
		Inventory toReturn = Bukkit.createInventory(null, inv_rows, name);
		
		if (Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Iron")) {
			Utils.createItem(inv, Material.IRON_INGOT.toString(), 1, 11, Utils.color("&6Iron Ore"), "&2Smelting Ore");
		}else {
			Utils.createItem(inv, Material.IRON_ORE.toString(), 1, 11, Utils.color("&6Iron Ore"), "&2Not Smelting Ore");
		}
		if (Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Gold")) {
			Utils.createItem(inv, Material.GOLD_INGOT.toString(), 1, 13, Utils.color("&eGold Ore"), "&2Smelting Ore");
		}else {
			Utils.createItem(inv, Material.GOLD_ORE.toString(), 1, 13, Utils.color("&eGold Ore"), "&2Not Smelting Ore");
		}
		if (Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Stone")) {
			Utils.createItem(inv, Material.STONE.toString(), 1, 15, Utils.color("&8Stone"), "&2Drops Stone");
		}else {
			Utils.createItem(inv, Material.STONE.toString(), 1, 15, Utils.color("&8Stone"), "&2Drops Cobble");
		}
		if (Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Cobble")) {
			Utils.createItem(inv, Material.COBBLESTONE.toString(), 1, 17, Utils.color("&7Cobblestone"), "&2Drops Stone");
		}else {
			Utils.createItem(inv, Material.COBBLESTONE.toString(), 1, 17, Utils.color("&7Cobblestone"), "&2Drops Cobble");
		}
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
}
