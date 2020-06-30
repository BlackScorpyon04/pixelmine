package me.jordan.DHMPlaytime.commands;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
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

public class PlaytimeTop implements CommandExecutor {
	
	private Main plugin;
	private LinkedHashMap<String, Integer> playtimeList = new LinkedHashMap<String, Integer>(); 

	public PlaytimeTop(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("ptop").setExecutor(this);
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
		for (String key : plugin.playtime.getKeys(false)) {
			playtimeList.put(Bukkit.getOfflinePlayer(UUID.fromString(key)).getName(), plugin.playtime.getInt(key));
		}
		LinkedHashMap<String, Integer> sorted = playtimeList.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		p.sendMessage(Utils.color(secondaryColor + "&l&m--------" + mainColor+"[ " + secondaryColor +"Playtime Top " + mainColor +"]" + secondaryColor + "&l&m--------"));
		int pos = 1;
		for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
			if (pos > 10) {
				break;
			}
			int tim = entry.getValue();
			int day = tim/24/60;
			int hour = tim/60%24;
			int minute = tim%60;
			String dh = secondaryColor + Integer.toString(day) + mainColor +" Days," + secondaryColor +" " + Integer.toString(hour) + mainColor +" Hours, " + secondaryColor + Integer.toString(minute) + " &7Minutes";
			p.sendMessage(Utils.color("&9#" + Integer.toString(pos) + " " + mainColor +": " + secondaryColor +entry.getKey() + " " + mainColor +"- " + secondaryColor + dh));
			pos++;
		}
		return true;
	}
}
