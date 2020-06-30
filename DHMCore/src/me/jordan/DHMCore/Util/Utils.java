package me.jordan.DHMCore.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Utils {
	public static String color (String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	public static ItemStack createItem(Inventory inv, String materialID, int amount, int invSlot, String displayname, String...loreString) {
		ItemStack item;
		List<String> lore = new ArrayList();
		
		item = new ItemStack(Material.getMaterial(materialID), amount);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(displayname));
		for (String s : loreString) { 
			lore.add(Utils.color(s));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		inv.setItem(invSlot - 1, item);
		return item;
	}
	public static ItemStack createItemByte(Inventory inv, String materialID, int byteID, int amount, int invSlot, String displayname, String...loreString) {
		ItemStack item;
		List<String> lore = new ArrayList();
		
		item = new ItemStack(Material.getMaterial(materialID), amount, (short) byteID);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(displayname));
		for (String s : loreString) { 
			lore.add(Utils.color(s));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		inv.setItem(invSlot - 1, item);
		return item;
	}
	public static Map createYAMLFile(String name, Plugin plug) {
		File filep = new File(plug.getDataFolder(), name + ".yml");

		if(!filep.exists()) {
			try {
				filep.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create the " + name +".yml");
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
		
		Map<File, FileConfiguration> map = new HashMap<>();
		map.put(filep, fileConfigurationp);
		return map;
	}
}
