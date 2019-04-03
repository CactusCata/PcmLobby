package com.gmail.cactuscata.pcmlobby.doublejump;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class DoubleJump {

	private final ArrayList<Player> toggled = new ArrayList<>();
	private final ArrayList<Player> cooldown = new ArrayList<>();
	private final HashMap<Player, Integer> count = new HashMap<>();

	public ArrayList<Player> getCooldown() {
		return cooldown;
	}

	public ArrayList<Player> getToggled() {
		return toggled;
	}

	public HashMap<Player, Integer> getCount() {
		return count;
	}

}
