package com.gmail.cactuscata.pcmlobby.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.cactuscata.pcmlobby.enums.PrefixMessage;
import com.gmail.cactuscata.pcmlobby.utils.Helper;

public class DefaultItems implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixMessage.ERROR + "La commande ne peut etre execute que par un joueur");
			return true;
		}

		Player player = (Player) sender;
		DefaultItems.getDefaultInventory(player);
		player.setWalkSpeed(0.4f);
		player.setFlySpeed(0.3f);
		return true;
	}

	public static void getDefaultInventory(Player player) {
		player.getInventory().clear();
		player.updateInventory();
		player.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR),
				new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });

		player.getInventory().setItem(0, Helper.createItem(Material.COMPASS, "§a§lMenu jeux §7§l(Clic Droit)",
				Arrays.asList("§eFaites clic-droit pour acceder au menu de teleportation")));
		player.getInventory().setItem(1, Helper.createItem(Material.GOLD_NUGGET, "§a§lVotes",
				Arrays.asList("§7", "§7Vous avez §6§l%{_coins}% Me's", "§r", "§eClic pour voter!")));
		player.getInventory().setItem(4,
				Helper.createItem(Material.NETHER_STAR, "§aGadgetsMenu",
						Arrays.asList("§eFaites clique droit pour ouvrir le menu Gadgets")));
		ItemStack item = Helper.createItem(Material.DIAMOND_SWORD, "§a§lMode PvP", true,
				Arrays.asList("§eFaites clique droit passer en mod PvP"));
		ItemMeta itemM = item.getItemMeta();
		itemM.spigot().setUnbreakable(true);
		itemM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(itemM);
		player.getInventory().setItem(7, item);
		player.getInventory().setItem(8,
				Helper.createItem(Material.FEATHER, "§6§l[VIP]§r §a§lPlume Magique §7(Clic Droit)",
						Arrays.asList("§eFaites clic-droit pour prendre votre envole !")));
		player.updateInventory();
	}

}
