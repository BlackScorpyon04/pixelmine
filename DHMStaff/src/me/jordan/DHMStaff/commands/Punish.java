package me.jordan.DHMStaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jordan.DHMCore.Util.Utils;
import me.jordan.DHMStaff.Main;
import me.jordan.DHMStaff.UI.PunishUI;

public class Punish implements CommandExecutor{

	private Main plugin;

	public Punish(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("punish").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("YOUR NOT A PLAYER CANT EXECUTE.");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission("dhm.staff")) {
			if (args.length == 0 || args[0] == "") {
				p.sendMessage(Utils.color(me.jordan.DHMCore.Main.plugin.getConfig().getString("prefix") + " " +"&cERROR! " + me.jordan.DHMCore.Main.plugin.getConfig().getString("mainColor") + "Try again with &c&n/punish (name)"));
				return true;
			}
			p.openInventory(PunishUI.GUI(p, args[0]));
		}
		return true;
	}
}
