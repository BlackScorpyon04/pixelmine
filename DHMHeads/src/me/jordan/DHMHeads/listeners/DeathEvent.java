package me.jordan.DHMHeads.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftMagmaCube;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftSlime;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.jordan.DHMHeads.Main;
import me.jordan.DHMCore.Util.Skull;
import me.jordan.DHMCore.Util.Utils;

public class DeathEvent implements Listener{

	private Main plugin;
	
	public DeathEvent(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
	        sm.setOwner(p.getName());
	        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", p.getName())));
	        ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Player").toString())));
			sm.setLore(lore);
	        playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
			if (plugin.getConfig().getBoolean("players-lose-money") == true) {
				if (me.jordan.DHMCore.Main.economy.getBalance(p.getName()) > plugin.getConfig().getInt("money-lost")) {
					me.jordan.DHMCore.Main.economy.withdrawPlayer(p.getName(), plugin.getConfig().getInt("money-lost"));
				}
			}
		} else if(e.getEntity() instanceof Zombie) {
			if(e.getEntity() instanceof PigZombie) {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_PigZombie");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Zombie Pigman")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Pigman").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			} else {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_Zombie");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Zombie")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Zombie").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			}
		} else if(e.getEntity() instanceof Skeleton) {
			Skeleton skeleton = (Skeleton) e.getEntity();
			if (skeleton.getSkeletonType() == SkeletonType.WITHER) {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_WSkeleton");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Wither Skeleton")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Wither-Skeleton").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			} else {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_Skeleton");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Skeleton")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Skeleton").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			}
		}else if(e.getEntity() instanceof Creeper) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
	        sm.setOwner("MHF_Creeper");
	        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Creeper")));
	        ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replace("%price%", plugin.getConfig().get("Creeper").toString())));
			sm.setLore(lore);
	        playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Blaze) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
	        sm.setOwner("MHF_Blaze");
	        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Blaze")));
	        ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Blaze").toString())));
			sm.setLore(lore);
	        playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof CraftSlime) {
			if(e.getEntity() instanceof CraftMagmaCube) {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_LavaSlime");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Magma Cube")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Magma-Cube").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			}else {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_Slime");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Slime")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Slime").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			}
		}else if(e.getEntity() instanceof Guardian) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
	        sm.setOwner("MHF_Guardian");
	        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Guardian")));
	        ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Guardian").toString())));
			sm.setLore(lore);
	        playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Horse) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/2cf48158c44546d6608ba2e28af1cbb550ca1b73441007acbf1d2b2212f55926");
	        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
	        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Horse")));
	        ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Horse").toString())));
			sm.setLore(lore);
	        playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Enderman) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
	        sm.setOwner("MHF_Enderman");
	        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Enderman")));
	        ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Enderman").toString())));
			sm.setLore(lore);
	        playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Spider) {
			if(e.getEntity() instanceof CaveSpider) {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
		        sm.setOwner("MHF_CaveSpider");
		        sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Cave Spider")));
		        ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Cave-Spider").toString())));
				sm.setLore(lore);
		        playerSkull.setItemMeta(sm);
		        e.getDrops().add(playerSkull);
			}else {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
				sm.setOwner("MHF_Spider");
				sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Spider")));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Spider").toString())));
				sm.setLore(lore);
				playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			}
		}else if(e.getEntity() instanceof Ghast) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Ghast");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Ghast")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Ghast").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof IronGolem) {
			Random r = new Random();
			int temp = r.nextInt((4 - 1) + 1) + 1;
			if (temp == 1) {
				ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
				sm.setOwner("MHF_Golem");
				sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Iron Golem")));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Iron-Golem").toString())));
				sm.setLore(lore);
				playerSkull.setItemMeta(sm);
				e.getDrops().add(playerSkull);
			}
		}else if(e.getEntity() instanceof Silverfish) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Silverfish")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Silver-Fish").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Bat) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/f76619652fafec90ce98df5013c63dc6a77776ab27873b73dafb2b6bdeb185");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Bat")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Bat").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Witch) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/20e13d18474fc94ed55aeb7069566e4687d773dac16f4c3f8722fc95bf9f2dfa");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Witch")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Witch").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Endermite) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/5bc7b9d36fb92b6bf292be73d32c6c5b0ecc25b44323a541fae1f1e67e393a3e");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Endermite")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Endermite").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Pig) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Pig");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Pig")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Pig").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Sheep) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Sheep");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Sheep")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Sheep").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Chicken) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Chicken");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Chicken")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Chicken").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Rabbit) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/7d1169b2694a6aba826360992365bcda5a10c89a3aa2b48c438531dd8685c3a7");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Rabbit")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Rabbit").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Villager) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Villager");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Villager")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Villager").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Squid) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Squid");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Squid")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Squid").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Wolf) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/69d1d3113ec43ac2961dd59f28175fb4718873c6c448dfca8722317d67");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Wolf")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Wolf").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof MushroomCow) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_MushroomCow");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Mushroom")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Mooshroom").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Ocelot) {
			ItemStack playerSkull = Skull.getCustomSkull("http://textures.minecraft.net/texture/5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1");
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Ocelot")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Ocelot").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}else if(e.getEntity() instanceof Cow) {
			ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) playerSkull.getItemMeta();
			sm.setOwner("MHF_Cow");
			sm.setDisplayName(Utils.color(plugin.getConfig().getString("HeadName").replaceAll("%type%", "Cow")));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Utils.color(plugin.getConfig().getString("HeadLore").replaceAll("%price%", plugin.getConfig().get("Cow").toString())));
			sm.setLore(lore);
			playerSkull.setItemMeta(sm);
			e.getDrops().add(playerSkull);
		}
	}
}
