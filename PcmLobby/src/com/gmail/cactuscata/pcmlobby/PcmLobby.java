package com.gmail.cactuscata.pcmlobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.cactuscata.pcmapi.api.MenuSystem;
import com.gmail.cactuscata.pcmapi.bungee.Bungee;
import com.gmail.cactuscata.pcmlobby.api.inventorylist.CreativeMenu;
import com.gmail.cactuscata.pcmlobby.api.inventorylist.LobbyMenu;
import com.gmail.cactuscata.pcmlobby.api.inventorylist.MonsterQuestMenu;
import com.gmail.cactuscata.pcmlobby.commands.Boutique;
import com.gmail.cactuscata.pcmlobby.commands.DefaultItems;
import com.gmail.cactuscata.pcmlobby.commands.Spawn;
import com.gmail.cactuscata.pcmlobby.doublejump.DoubleJump;
import com.gmail.cactuscata.pcmlobby.doublejump.DoubleJumpEvent;
import com.gmail.cactuscata.pcmlobby.fly.Fly;
import com.gmail.cactuscata.pcmlobby.listener.Listeners;
import com.gmail.cactuscata.pcmlobby.pvp.Pvp;

public class PcmLobby extends JavaPlugin {

	private static PcmLobby plugin;

	public void onEnable() {

		plugin = this;

		PluginManager pm = this.getServer().getPluginManager();
		this.saveDefaultConfig();

		DoubleJump doubleJump = new DoubleJump();
		Fly fly = new Fly();
		Pvp pvp = new Pvp(this, fly);

		pm.registerEvents(new DoubleJumpEvent(this, doubleJump, fly, pvp), this);
		pm.registerEvents(new Listeners(this, pvp, fly), this);

		getCommand("defaultitems").setExecutor(new DefaultItems());
		getCommand("boutique").setExecutor(new Boutique());
		getCommand("spawn").setExecutor(new Spawn(this));

		MenuSystem.addMenu(new CreativeMenu());
		MenuSystem.addMenu(new MonsterQuestMenu());
		MenuSystem.addMenu(new LobbyMenu());

		setDefaultGameRules();

	}

	@Override
	public void onDisable() {

		if (getServer().getServerName().equals("lobby"))
			for (Player player : Bukkit.getServer().getOnlinePlayers())
				Bungee.teleport(player, "game");

		else if (getServer().getServerName().equals("game"))
			for (Player player : Bukkit.getServer().getOnlinePlayers())
				Bungee.teleport(player, "lobby");

	}

	public static Location getSpawnLocation(PcmLobby plugin) {
		FileConfiguration config = plugin.getConfig();
		plugin.reloadConfig();
		return new Location(Bukkit.getWorld(config.getString("spawn.world")), config.getDouble("spawn.x"),
				config.getDouble("spawn.y"), config.getDouble("spawn.z"), (float) config.getDouble("spawn.yaw"),
				(float) config.getDouble("spawn.pitch"));
	}

	private void setDefaultGameRules() {
		World world = Bukkit.getWorld("world");
		world.setGameRuleValue("commandBlockOutput", "false");
		world.setGameRuleValue("doFireTick", "false");
		world.setGameRuleValue("reducedDebugInfo", "true");
		world.setGameRuleValue("spectatorsGenerateChunks", "false");

	}

	public static PcmLobby getInstance() {
		return plugin;
	}

}
