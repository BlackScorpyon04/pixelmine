package me.jordan.DHMOregen.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import io.netty.util.internal.ThreadLocalRandom;
import me.jordan.DHMOregen.Main;

public class BlockForm implements Listener{
	
	public BlockForm(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onFromTo(BlockFromToEvent event)
    {
        int id = event.getBlock().getTypeId();
        if(id >= 8 && id <= 11)
        {
            Block b = event.getToBlock();
            int toid = b.getTypeId();
            if(toid == 0)
            {
                if(generatesCobble(id, b))
                {
                	int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                	if (randomNum <= 40) {
                		event.getToBlock().setType(Material.STONE);
                	}else if(randomNum <= 60 & randomNum > 40) {
                		event.getToBlock().setType(Material.COAL_ORE);
                	}else if(randomNum <=67 & randomNum > 60) {
                		event.getToBlock().setType(Material.DIAMOND_ORE);
                	}else if(randomNum <=77 & randomNum > 67) {
                		event.getToBlock().setType(Material.IRON_ORE);
                	}else if(randomNum <=92 & randomNum > 77) {
                		event.getToBlock().setType(Material.GOLD_ORE);
                	}else if(randomNum <=100 & randomNum > 92) {
                		event.getToBlock().setType(Material.EMERALD_ORE);
                	}
                    
                }
            }
        }
    }
     
    private final BlockFace[] faces = new BlockFace[]
        {
            BlockFace.SELF,
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
        };
     
    @SuppressWarnings("deprecation")
	public boolean generatesCobble(int id, Block b)
    {
        int mirrorID1 = (id == 8 || id == 9 ? 10 : 8);
        int mirrorID2 = (id == 8 || id == 9 ? 11 : 9);
        for(BlockFace face : faces)
        {
            Block r = b.getRelative(face, 1);
            if(r.getTypeId() == mirrorID1 || r.getTypeId() == mirrorID2)
            {
                return true;
            }
        }
        return false;
    }
}
