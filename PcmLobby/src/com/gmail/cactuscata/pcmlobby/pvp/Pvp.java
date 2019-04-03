package com.gmail.cactuscata.pcmlobby.pvp;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.cactuscata.pcmapi.command.other.TitleApi;
import com.gmail.cactuscata.pcmlobby.PcmLobby;
import com.gmail.cactuscata.pcmlobby.commands.DefaultItems;
import com.gmail.cactuscata.pcmlobby.enums.PrefixMessage;
import com.gmail.cactuscata.pcmlobby.fly.Fly;
import com.gmail.cactuscata.pcmlobby.utils.Helper;

public class Pvp {

	private final PcmLobby plugin;
	private final Fly fly;

	public Pvp(PcmLobby plugin, Fly fly) {
		this.plugin = plugin;
		this.fly = fly;
	}

	private final ArrayList<Player> pvpList = new ArrayList<>();
	private final ArrayList<Player> waitingTime = new ArrayList<>();

	public void remove(Player player) {
		pvpList.remove(player);
		player.setWalkSpeed(0.4f);
		player.setFlySpeed(0.3f);
		DefaultItems.getDefaultInventory(player);
		TitleApi.sendTitle(player, 20, 40, 20, "", "§6Votre mode §4PvP §6a été §cdésactivé");
	}

	public void addPlayer(Player player) {
		if (waitingTime.contains(player)) {
			player.sendMessage(PrefixMessage.PREFIX + "Evitez de spammer !");
			return;
		}

		if (fly.contains(player))
			fly.removePlayer(player);

		player.setWalkSpeed(0.2f);
		player.setFlySpeed(0.2f);
		pvpList.add(player);
		setDefaultPvpItem(player);
		TitleApi.sendTitle(player, 20, 40, 20, "", "§6Votre mode §4PvP §6a été §aactivé");
		startAntiSpawn(player);
	}

	public boolean contains(Player player) {
		return pvpList.contains(player);
	}

	private void setDefaultPvpItem(Player player) {
		player.getInventory().clear();
		player.updateInventory();
		player.getInventory().setHelmet(Helper.createItem(Material.IRON_HELMET));
		player.getInventory().setChestplate(Helper.createItem(Material.IRON_CHESTPLATE));
		player.getInventory().setLeggings(Helper.createItem(Material.IRON_LEGGINGS));
		player.getInventory().setBoots(Helper.createItem(Material.IRON_BOOTS));

		ItemStack itemSword = new ItemStack(Material.IRON_SWORD);
		ItemMeta itemSwordM = itemSword.getItemMeta();
		itemSwordM.setDisplayName("§eEpée de " + player.getDisplayName());
		itemSwordM.spigot().setUnbreakable(true);
		itemSwordM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemSword.setItemMeta(itemSwordM);
		player.getInventory().setItem(0, itemSword);

		ItemStack itemBow = new ItemStack(Material.BOW);
		ItemMeta itemBowM = itemBow.getItemMeta();
		itemBowM.setDisplayName("§eEpée de " + player.getDisplayName());
		itemBowM.spigot().setUnbreakable(true);
		itemBowM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemBowM.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		itemBow.setItemMeta(itemBowM);
		player.getInventory().setItem(1, itemBow);

		ItemStack itemBarrier = new ItemStack(Material.BARRIER);
		ItemMeta itemBarrierM = itemBarrier.getItemMeta();
		itemBarrierM.setDisplayName("§c§lQuitter le mode PvP");
		itemBarrier.setItemMeta(itemBarrierM);
		player.getInventory().setItem(8, itemBarrier);

		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName("§cFleche de " + player.getDisplayName());
		arrow.setItemMeta(arrowM);
		player.getInventory().setItem(9, arrow);
		
		player.updateInventory();

	}

	private void startAntiSpawn(Player player) {

		waitingTime.add(player);

		new BukkitRunnable() {

			@Override
			public void run() {

				waitingTime.remove(player);

			}
		}.runTaskLater(plugin, 60L);

	}

}
