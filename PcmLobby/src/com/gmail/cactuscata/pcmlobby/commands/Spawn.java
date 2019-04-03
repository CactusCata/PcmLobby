package com.gmail.cactuscata.pcmlobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactuscata.pcmlobby.PcmLobby;
import com.gmail.cactuscata.pcmlobby.enums.PrefixMessage;

public class Spawn implements CommandExecutor {

	private final PcmLobby plugin;

	public Spawn(PcmLobby plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixMessage.ERROR + "Il n'y a que les joueurs qui puissent executer cette commande !");
			return true;
		}

		Player player = (Player) sender;
		player.teleport(PcmLobby.getSpawnLocation(plugin));
		return true;
	}

}
