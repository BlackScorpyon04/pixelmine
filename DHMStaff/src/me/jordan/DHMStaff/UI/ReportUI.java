package me.jordan.DHMStaff.UI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.jordan.DHMCore.Main;
import me.jordan.DHMCore.Util.Utils;

public class ReportUI {
	public static Inventory inv;
	public static String name;
	public static int inv_rows = 27;
	public static void initialize() {
		name = Utils.color(Main.plugin.getConfig().getString("prefix") + " " +"&cReport GUI");
		inv = Bukkit.createInventory(null, inv_rows);
	}
	
	@SuppressWarnings("deprecation")
	public static Inventory GUI(Player p, String plrname) {
		Inventory toReturn = Bukkit.createInventory(null, inv_rows, name);
		
		Utils.createItem(inv, Material.BARRIER.toString(), 1, 12, "&cCheating/Exploiting", "&7Rule #3", "&7E.G Kill Aura, Speed, Fly, Glitching");
		Utils.createItem(inv, Material.PAPER.toString(), 1, 5, "&cSpam", "&7Rule #1", "&7E.G multiple messages with same text");
		Utils.createItem(inv, Material.BEACON.toString(), 1, 11, "&cAdvertisement", "&7Rule #2", "&7E.G Sending Links");
		Utils.createItem(inv, Material.REDSTONE_BLOCK.toString(), 1, 13, "&cRacism/Sexism", "&7Rule #4", "&7E.G Racial slurs / \"No rights for women!\"");
		Utils.createItem(inv, Material.GOLD_SWORD.toString(), 1, 14, "&cThreats", "&7Rule #5", "&7E.G \"I'm gonna kill you IRL!\" / DDOS/IP Threats");
		Utils.createItemByte(inv, Material.RAW_FISH.toString(), 3, 1, 15, "&cDisrespect", "&7Rule #6", "&7E.G \"You suck!\", \"F**k you retard!\"");
		Utils.createItem(inv, Material.NETHER_STAR.toString(), 1, 16, "&cExcessive Swearing", "&7Rule #7", "&7E.G \"F**k you f**king hell you stupid f**king tw*t\"");
		Utils.createItem(inv, Material.NETHER_STALK.toString(), 1, 17, "&cImpersonation", "&7Rule #8", "&7E.G \"I'm an admin\", nicked as staff");
		Utils.createItemByte(inv, Material.SKULL_ITEM.toString(), 3,  1, 23, "&cAlt Limit", "&7Rule #9", "&7E.G having more than 3 accounts online");
		ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
        sm.setOwner(plrname);
        sm.setDisplayName(Utils.color("&c" + plrname));
        playerSkull.setItemMeta(sm);
		inv.setItem(0, playerSkull);
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
}
