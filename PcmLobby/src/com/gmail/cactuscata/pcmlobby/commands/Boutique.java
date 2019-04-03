package com.gmail.cactuscata.pcmlobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Boutique implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		player.openInventory(Bukkit.createInventory(null, 27, "tests"));
		sender.sendMessage("\n§3-§6La boutique §c➔ §ahttps://pleasecraftme.buycraft.net\n");
		return true;
		
	}
}
