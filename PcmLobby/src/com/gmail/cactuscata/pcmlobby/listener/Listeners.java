package com.gmail.cactuscata.pcmlobby.listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.cactuscata.pcmapi.PcmApi;
import com.gmail.cactuscata.pcmapi.api.MenuSystem;
import com.gmail.cactuscata.pcmapi.bungee.Bungee;
import com.gmail.cactuscata.pcmapi.sql.BAT_Players;
import com.gmail.cactuscata.pcmapi.staff.DisplayTag;
import com.gmail.cactuscata.pcmlobby.PcmLobby;
import com.gmail.cactuscata.pcmlobby.api.inventorylist.LobbyMenu;
import com.gmail.cactuscata.pcmlobby.commands.DefaultItems;
import com.gmail.cactuscata.pcmlobby.enums.PrefixMessage;
import com.gmail.cactuscata.pcmlobby.fly.Fly;
import com.gmail.cactuscata.pcmlobby.pvp.Pvp;

public class Listeners implements Listener {

	private final PcmLobby plugin;
	private final BAT_Players batplayer;
	private final DisplayTag tag;
	private final Pvp pvp;
	private final Fly fly;
	private final Connection connection;

	public Listeners(PcmLobby plugin, Pvp pvp, Fly fly) {
		this.plugin = plugin;
		this.connection = PcmApi.getConnexion();
		this.batplayer = new BAT_Players(connection);
		this.tag = new DisplayTag();
		this.pvp = pvp;
		this.fly = fly;
	}

	@EventHandler
	public void disconnect(PlayerQuitEvent event) throws SQLException {
		event.setQuitMessage(null);
		Player player = event.getPlayer();
		pvp.remove(player);
		fly.removePlayer(player);
		batplayer.updateLastConnection(player);
		// tag.removeTeam(player);

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();
		player.teleport(PcmLobby.getSpawnLocation(this.plugin));
		event.setJoinMessage(null);
		player.setFoodLevel(20);
		player.setWalkSpeed(0.4f);
		player.setFlySpeed(0.3f);

		player.sendMessage("");
		player.sendMessage("§a§l           Bienvenue sur PleaseCraftMe ");
		player.sendMessage("§9§m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		player.sendMessage("§a§l/amis  §d=> §7Gestion de tes amis");
		player.sendMessage("§a§l/fac   §d=> §7Accède directement sur le serveur Faction");
		player.sendMessage("§a§l/mq    §d=> §7Accède directement sur le serveur MonsterQuest");
		player.sendMessage("§a§l/vote  §d=> §7Vote pour le serveur et gagne des Me's");
		player.sendMessage("§a§l/credit   §d=> §7Affiches ton nombre de crédit cosmétique");
		player.sendMessage("§9§m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		player.sendMessage("§6Infos:§cNous sommes aussi sur §b§lDiscord §6https://pleasecraftme.fr/discord");
		player.sendMessage("§6Infos:§cLes Mini-Jeux sont tous rassemblés en §aArcade §e➜ boussole");
		player.setGameMode(GameMode.ADVENTURE);

		DefaultItems.getDefaultInventory(player);

		// new BukkitRunnable() {
		//
		// @Override
		// public void run() {

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM `BAT_players` WHERE `UUID` = '"
					+ player.getUniqueId().toString().replace("-", "") + "'");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Trouve l'uuid du joueur dans la tab

				System.out.println("trouve le joueur");
				if (!rs.getString("BAT_player").equals(player.getName()))
					batplayer.updateNewPlayerName(player);
			} else {
				// Ajout de la ligne du joueur
				System.out.println("ne trouve pas le joueur");
				batplayer.addPlayer(player);

			}

			tag.setGradeConnect(player, connection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// }
		// };

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Action action = event.getAction();
		Material blockType = event.getClickedBlock().getType();

		if (action == Action.RIGHT_CLICK_BLOCK)
			if (blockType == Material.TRAP_DOOR
					|| blockType == Material.CHEST
					|| blockType == Material.TRAPPED_CHEST)
				event.setCancelled(true);

		Player player = event.getPlayer();

		if (action == Action.RIGHT_CLICK_BLOCK) {
			if (blockType == Material.SIGN || blockType == Material.SIGN_POST || blockType == Material.WALL_SIGN) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				switch (sign.getLine(0)) {
				case "§c§l§nKit PvP":
					Bungee.teleport(player, "kitpvp");
					return;
				case "§c§l§nBedWars":
					Bungee.teleport(player, "bedwars");
					return;
				case "§c§l§nTeamFortress":
					Bungee.teleport(player, "tf");
					return;
				case "§c§l§nSkyWars":
					Bungee.teleport(player, "skywars");
					return;

				}
			}
		}

		ItemStack it = event.getItem();

		if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK || it == null)
			return;

		if (it.getType() == Material.DIAMOND_SWORD) {
			if (!pvp.contains(player)) {
				pvp.addPlayer(player);
				event.setCancelled(true);
				return;
			}

		}

		if (it.getType() == Material.BARRIER) {
			if (pvp.contains(player)) {
				pvp.remove(player);
				return;
			}

		}

		if (it.getType() == Material.FEATHER)
			// if (player.hasPermission("pcm.lobby.vip.fly"))
			if (!pvp.contains(player)) {
				if (fly.contains(player)) {
					fly.removePlayer(player);
					return;
				}
				fly.addPlayer(player);
				return;

			}

		if (it.getType() == Material.COMPASS)
			MenuSystem.open(player, LobbyMenu.class);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();
		ItemStack current = event.getCurrentItem();

		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)
			return;

		MenuSystem.getRegisteredMenus().values().stream().filter(menu -> inv.getName().equalsIgnoreCase(menu.name()))
				.forEach(menu -> {
					player.closeInventory();
					menu.onClick(player, inv, current, event.getSlot());
					event.setCancelled(true);
				});

	}

	@EventHandler
	public void onArrowHitBlock(ProjectileHitEvent event) {
		if (event.getEntity() instanceof Arrow)
			event.getEntity().remove();
	}

	@EventHandler
	public void death(PlayerDeathEvent event) {
		event.setDeathMessage(null);
		event.setKeepInventory(true);
		pvp.remove(event.getEntity());
	}

	@EventHandler
	public void ChangeWeather(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void takeDamage(EntityDamageByEntityEvent event) {

		if (!(event.getEntity() instanceof Player)
				|| !(event.getDamager() instanceof Arrow) && !(event.getDamager() instanceof Player)) {
			event.setCancelled(true);
			return;
		}

		if (event.getDamager() instanceof Arrow)
			return;

		Player senddmgplayer = (Player) event.getDamager();
		Player getdmgplayer = (Player) event.getEntity();

		if (!pvp.contains(senddmgplayer)) {
			senddmgplayer.sendMessage(PrefixMessage.PREFIX + "Vous n'avez pas le mode pvp d'activé !");
			event.setCancelled(true);
			return;
		}

		if (!pvp.contains(getdmgplayer)) {
			senddmgplayer.sendMessage(PrefixMessage.PREFIX + "Le joueur " + getdmgplayer.getDisplayName()
					+ " n'a pas le mode pvp d'activé !");
			event.setCancelled(true);
			return;
		}

		if (pvp.contains(getdmgplayer)) {
			if (pvp.contains(getdmgplayer))
				return;
		}

	}

	@EventHandler
	public void takeDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			event.setCancelled(true);
			return;
		}

		Player player = (Player) event.getEntity();
		if (!pvp.contains(player)) {
			event.setCancelled(true);
			return;
		}

	}

	@EventHandler
	public void fadeIce(BlockFadeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void placeBlock(BlockPlaceEvent event) {
		if (!event.getPlayer().hasPermission("sk.admin.lobby.place"))
			event.setCancelled(true);
	}

	@EventHandler
	public void breakBlock(BlockBreakEvent event) {
		if (!event.getPlayer().hasPermission("sk.admin.lobby.break"))
			event.setCancelled(true);
	}

	@EventHandler
	public void openInventory(InventoryOpenEvent event) {
		if (event.getInventory().getType() != InventoryType.PLAYER
				&& event.getInventory().getType() != InventoryType.CHEST
				&& !event.getPlayer().hasPermission("sk.lobby.admin"))
			event.setCancelled(true);
	}

	@EventHandler
	public void changeBlock(EntityChangeBlockEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void descreazeEat(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void drop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void PlayerCommand(PlayerCommandPreprocessEvent event) {
		Player p = event.getPlayer();
		String[] cmd = event.getMessage().split(" ");
		if (cmd[0].equalsIgnoreCase("/menu") && !p.isOp())
			event.setCancelled(true);
	}

}
