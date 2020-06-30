package me.jordan.DHMMining.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.jordan.DHMMining.Main;

public class BlockBreak implements Listener{

	public BlockBreak(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if (!e.isCancelled()) {
			if (e.getBlock().getType() == Material.ICE || e.getBlock().getType() == Material.MOB_SPAWNER || e.getBlock().getType() == Material.CHEST || e.getBlock().getType() == Material.TRAPPED_CHEST) {
			}else {
				if (p.getGameMode() == GameMode.SURVIVAL) {
					if (p.hasPermission("pmc.mining")) {
						for (ItemStack items : e.getBlock().getDrops(p.getItemInHand())) {
							if (p.getInventory().firstEmpty() == -1) {
								if (p.hasPermission("pmc.mining")) {
									if (p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
										int random = (int)(Math.random() * 100 + 1);
										if(random > 1 && random < 11) {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.FLINT));
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.GRAVEL));
										}
									}else if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
										int random = (int)(Math.random() * 100 + 1);
										if(random > 1 && random < 15) {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.FLINT));
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.GRAVEL));
										}
									}else if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
										int random = (int)(Math.random() * 4 + 1);
										if(random == 1) {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.FLINT));
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.GRAVEL));
										}
									}else if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 3) {
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.FLINT));
									}else if (e.getBlock().getType() == Material.IRON_ORE) {
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Iron") == true) {
											ItemStack Smelted = new ItemStack(Material.IRON_INGOT, CraftMagicNumbers.getBlock(Material.DIAMOND_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), items);
										}
									}else if(e.getBlock().getType() == Material.GOLD_ORE){
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Gold") == true) {
											ItemStack Smelted = new ItemStack(Material.GOLD_INGOT, CraftMagicNumbers.getBlock(Material.DIAMOND_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), items);
										}
									}else if(e.getBlock().getType() == Material.QUARTZ_ORE) {
										ItemStack Smelted = new ItemStack(Material.QUARTZ, items.getAmount());
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
									}else if(e.getBlock().getType() == Material.COBBLESTONE) {
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Cobble") == true) {
											ItemStack Smelted = new ItemStack(Material.STONE, items.getAmount());
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), items);
										}
									}else if(e.getBlock().getType() == Material.DIAMOND_ORE) {
										ItemStack Smelted = new ItemStack(Material.DIAMOND, CraftMagicNumbers.getBlock(Material.DIAMOND_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
									}else if(e.getBlock().getType() == Material.EMERALD_ORE){
										ItemStack Smelted = new ItemStack(Material.EMERALD, CraftMagicNumbers.getBlock(Material.EMERALD_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
									}else if(e.getBlock().getType() == Material.COAL_ORE) {
										ItemStack Smelted = new ItemStack(Material.COAL, CraftMagicNumbers.getBlock(Material.COAL_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
									}else if(e.getBlock().getType() == Material.LAPIS_ORE) {
										ItemStack Smelted = new ItemStack(Material.INK_SACK, CraftMagicNumbers.getBlock(Material.LAPIS_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()) / 4, (short) 4);
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
									}else if(e.getBlock().getType() == Material.STONE){
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Stone") == true) {
											ItemStack Smelted = new ItemStack(Material.STONE, items.getAmount());
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), Smelted);
										}else {
											e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), items);
										}
									}else {
										e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), items);
									}
								}else {
									e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), items);
								}
							} else {
								if (p.hasPermission("pmc.mining")) {
									if (e.getBlock().getType() == Material.GRAVEL) {
										if (p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
											int random = (int)(Math.random() * 100 + 1);
											if(random > 1 && random < 11) {
												p.getInventory().addItem(new ItemStack(Material.FLINT));
											}else {
												p.getInventory().addItem(new ItemStack(Material.GRAVEL));
											}
										}else if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
											int random = (int)(Math.random() * 100 + 1);
											if(random > 1 && random < 15) {
												p.getInventory().addItem(new ItemStack(Material.FLINT));
											}else {
												p.getInventory().addItem(new ItemStack(Material.GRAVEL));
											}
										}else if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
											int random = (int)(Math.random() * 4 + 1);
											if(random == 1) {
												p.getInventory().addItem(new ItemStack(Material.FLINT));
											}else {
												p.getInventory().addItem(new ItemStack(Material.GRAVEL));
											}
										}else if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 3) {
											p.getInventory().addItem(new ItemStack(Material.FLINT));
										}
									}else if (e.getBlock().getType() == Material.IRON_ORE) {
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Iron") == true) {
											ItemStack Smelted = new ItemStack(Material.IRON_INGOT, CraftMagicNumbers.getBlock(Material.DIAMOND_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
											p.getInventory().addItem(Smelted);
										}else {
											p.getInventory().addItem(items);
										}
									}else if(e.getBlock().getType() == Material.GOLD_ORE){
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Gold") == true) {
											ItemStack Smelted = new ItemStack(Material.GOLD_INGOT, CraftMagicNumbers.getBlock(Material.DIAMOND_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
											p.getInventory().addItem(Smelted);
										}else {
											p.getInventory().addItem(items);	
										}
									}else if(e.getBlock().getType() == Material.QUARTZ_ORE) {
										ItemStack Smelted = new ItemStack(Material.QUARTZ, items.getAmount());
										p.getInventory().addItem(Smelted);
									}else if(e.getBlock().getType() == Material.COBBLESTONE) {
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Cobble") == true) {
											ItemStack Smelted = new ItemStack(Material.STONE, items.getAmount());
											p.getInventory().addItem(Smelted);
										}else {
											p.getInventory().addItem(items);
										}
									}else if(e.getBlock().getType() == Material.DIAMOND_ORE) {
										ItemStack Smelted = new ItemStack(Material.DIAMOND, CraftMagicNumbers.getBlock(Material.DIAMOND_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
										p.getInventory().addItem(Smelted);
									}else if(e.getBlock().getType() == Material.EMERALD_ORE){
										ItemStack Smelted = new ItemStack(Material.EMERALD, CraftMagicNumbers.getBlock(Material.EMERALD_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
										p.getInventory().addItem(Smelted);
									}else if(e.getBlock().getType() == Material.COAL_ORE) {
										ItemStack Smelted = new ItemStack(Material.COAL, CraftMagicNumbers.getBlock(Material.COAL_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()));
										p.getInventory().addItem(Smelted);
									}else if(e.getBlock().getType() == Material.LAPIS_ORE) {
										ItemStack Smelted = new ItemStack(Material.INK_SACK, CraftMagicNumbers.getBlock(Material.LAPIS_ORE).getDropCount(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), new Random()) / 4, (short) 4);
										p.getInventory().addItem(Smelted);
									}else if(e.getBlock().getType() == Material.STONE){
										if (me.jordan.DHMCore.Main.plugin.plrs.getBoolean("Players." + p.getUniqueId() + ".AutoSmelt.Stone") == true) {
											ItemStack Smelted = new ItemStack(Material.STONE, items.getAmount(), e.getBlock().getData());
											p.getInventory().addItem(Smelted);
										}else {
											p.getInventory().addItem(items);
										}
									}else if(e.getBlock().getType() == Material.CARROT) {
										if (e.getBlock().getData() >= 7) {
											Random random = new Random();
											p.getInventory().addItem(new ItemStack(Material.CARROT_ITEM, random.nextInt(4) + 1));
										}
									}else if(e.getBlock().getType() == Material.CROPS) {
										if (e.getBlock().getData() >= 7) {
											Random random = new Random();
											p.getInventory().addItem(new ItemStack(Material.WHEAT, 1));
											p.getInventory().addItem(new ItemStack(Material.SEEDS, random.nextInt(4) + 1));
										}
									}else if(e.getBlock().getType() == Material.POTATO) {
										if (e.getBlock().getData() >= 7) {
											Random random = new Random();
											p.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, random.nextInt(4) + 1));
										}
									}else if(e.getBlock().getType() == Material.COCOA) {
										if (e.getBlock().getData() == 2) {
											Random random = new Random();
											p.getInventory().addItem(new ItemStack(Material.INK_SACK, 3, (short) 3));
										}
									}else {
										p.getInventory().addItem(items);
									}
								}else {
									p.getInventory().addItem(items);
								}
							}
						}
						p.giveExp(e.getExpToDrop());
						e.setCancelled(true);
						e.setExpToDrop(0);
						e.getBlock().setType(Material.AIR);
					}
				}
			}
		}
	}


	public boolean invFull(Player p) {          
		return p.getInventory().firstEmpty() == -1;
	}
}
