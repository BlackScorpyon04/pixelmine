package me.jordan.DHMPlaytime.commands;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import me.jordan.DHMPlaytime.Main;
import me.jordan.DHMCore.Util.Utils;

public class Playtime implements CommandExecutor {
	
	private Main plugin;
	private LinkedHashMap<String, Integer> playtimeList = new LinkedHashMap<String, Integer>(); 

	public Playtime(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("playtime").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String prefix = me.jordan.DHMCore.Main.plugin.getConfig().getString("prefix");
		String mainColor = me.jordan.DHMCore.Main.plugin.getConfig().getString("mainColor");
		String secondaryColor = me.jordan.DHMCore.Main.plugin.getConfig().getString("secondaryColor");
		int time = p.getStatistic(Statistic.PLAY_ONE_TICK)/ 60 / 20;
		int days = time/24/60;
		int hours = time/60%24;
		int minutes = time%60;
		p.sendMessage(Utils.color(prefix));
		p.sendMessage(Utils.color(mainColor + "Your playtime is"));
		String dhm = secondaryColor + Integer.toString(days) + mainColor +" Days," + secondaryColor +" " + Integer.toString(hours) + mainColor +" Hours, " + secondaryColor + Integer.toString(minutes) + " &7Minutes";
		p.sendMessage(Utils.color(dhm));
		for (String key : plugin.playtime.getKeys(false)) {
			playtimeList.put(Bukkit.getOfflinePlayer(UUID.fromString(key)).getName(), plugin.playtime.getInt(key));
		}
		LinkedHashMap<String, Integer> sorted = playtimeList.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		int pos = 1;
		for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
			if (entry.getKey() == p.getName()) {
				p.sendMessage(Utils.color(mainColor + "(You're also " + secondaryColor +"#" + Integer.toString(pos) + mainColor + " on /ptop!)"));
				break;
			}
			
			pos++;
		}
		plugin.playtime.set(p.getUniqueId().toString(), time);
		try {
			plugin.playtime.save(plugin.playtimeFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			plugin.playtime.load(plugin.playtimeFile);
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	 private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

	        // 1. Convert Map to List of Map
	        List<Map.Entry<String, Integer>> list =
	                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2) {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        /*
	        //classic iterator example
	        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
	            Map.Entry<String, Integer> entry = it.next();
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }*/


	        return sortedMap;
	    }
}
