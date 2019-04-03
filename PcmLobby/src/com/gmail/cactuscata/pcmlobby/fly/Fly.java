package com.gmail.cactuscata.pcmlobby.fly;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.gmail.cactuscata.pcmlobby.enums.PrefixMessage;

public class Fly {

	private final ArrayList<Player> fly = new ArrayList<>();

	public void addPlayer(Player player) {
		player.sendMessage(PrefixMessage.PREFIX + "Le fly a été activé !");
		player.setAllowFlight(true);
		fly.add(player);
	}

	public void removePlayer(Player player) {
		player.setAllowFlight(false);
		player.sendMessage(PrefixMessage.PREFIX + "Le fly a été désactivé !");
		fly.remove(player);
	}

	public boolean contains(Player player) {
		return fly.contains(player);
	}

	public ArrayList<Player> getFly() {
		return fly;
	}

}
