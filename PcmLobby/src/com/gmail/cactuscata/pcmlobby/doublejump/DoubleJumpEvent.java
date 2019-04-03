package com.gmail.cactuscata.pcmlobby.doublejump;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.cactuscata.pcmlobby.PcmLobby;
import com.gmail.cactuscata.pcmlobby.fly.Fly;
import com.gmail.cactuscata.pcmlobby.pvp.Pvp;

public class DoubleJumpEvent implements Listener {

	private final DoubleJump doubleJump;
	private final Fly fly;
	private final PcmLobby plugin;
	private final Pvp pvp;

	public DoubleJumpEvent(PcmLobby plugin, DoubleJump doubleJump, Fly fly, Pvp pvp) {
		this.fly = fly;
		this.doubleJump = doubleJump;
		this.plugin = plugin;
		this.pvp = pvp;
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		final Player player = event.getPlayer();
		if (!fly.contains(player) && !pvp.contains(player)) {
			if (doubleJump.getCooldown().contains(event.getPlayer())) {
				player.setAllowFlight(false);
			}
			if (!doubleJump.getCooldown().contains(player)) {
				if (player.getGameMode() == GameMode.CREATIVE)
					return;

				event.setCancelled(true);
				player.setAllowFlight(false);
				player.setFlying(false);
				player.setVelocity(player.getLocation().getDirection().multiply(3).setY(1));
				doubleJump.getCooldown().add(player);
				doubleJump.getCount().put(player, 1);
				new BukkitRunnable() {
					public void run() {
						int number = doubleJump.getCount().get(player);
						if (number > 0) {
							doubleJump.getCount().put(player, number - 1);
						} else {
							cancel();
							doubleJump.getCooldown().remove(player);
							doubleJump.getCount().remove(player);
						}
					}
				}.runTaskTimer(plugin, 20L, 20L);
			}
		}

	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {

		final Player player = event.getPlayer();
		if (!fly.contains(player) && !pvp.contains(player)) {
			if ((player.getGameMode() != GameMode.CREATIVE)
					&& (player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR)
					&& (!player.isFlying())) {
				player.setAllowFlight(true);
				if (doubleJump.getCooldown().contains(event.getPlayer())
						&& (doubleJump.getCooldown().contains(event.getPlayer()))) {
					Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
						public void run() {
							if (!fly.contains(player))
								player.setAllowFlight(false);
						}
					}, 20L);
				}
			}

		}

	}

}
