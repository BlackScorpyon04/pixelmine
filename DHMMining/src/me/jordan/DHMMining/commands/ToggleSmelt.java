package me.jordan.DHMMining.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jordan.DHMMining.Main;
import me.jordan.DHMMining.UI.ToggleSmeltUI;

public class ToggleSmelt implements CommandExecutor{

	private Main plugin;

	public ToggleSmelt(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("smelttoggle").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("YOUR NOT A PLAYER CANT EXECUTE.");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission("pmc.mining")) {
			p.openInventory(ToggleSmeltUI.GUI(p));
		}
		return true;
	}
}
