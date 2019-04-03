package com.gmail.cactuscata.pcmlobby.api.inventorylist;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.cactuscata.pcmapi.api.CustomInventory;
import com.gmail.cactuscata.pcmapi.bungee.Bungee;
import com.gmail.cactuscata.pcmlobby.utils.Helper;

public class MonsterQuestMenu implements CustomInventory {

	@Override
	public int getSize() {
		return 27;
	}

	@Override
	public String name() {
		return "§1§l✈ §9§lMonsterquest §1§l✈";
	}

	@Override 
	public void contents(Player player, Inventory inv) {
		inv.setItem(12, Helper.createItem(Material.DIAMOND_BARDING, "§6§lServeur 1",
				Arrays.asList("§a§lJoueurs: §e" + Bungee.getCount(player, "mq1") + "/70")));
		inv.setItem(13, Helper.createItem(Material.EMERALD, "§6§lBETA MQPVP/CLASSE",
				Arrays.asList("§c§lMax 20 joueurs (1.10)")));
		inv.setItem(14, Helper.createItem(Material.DIAMOND_BARDING, 2, "§6§lServeur 2",
				Arrays.asList("§a§lJoueurs: §e" + Bungee.getCount(player, "mq2") + "/70")));

	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack item, int slot) {

		if (item.getType() == Material.DIAMOND_BARDING && slot == 12 && item.getAmount() == 1)
			Bungee.teleport(player, "mq1");
		else if (item.getType() == Material.DIAMOND_BARDING && slot == 14 && item.getAmount() == 2)
			Bungee.teleport(player, "mq2");
		else if (item.getType() == Material.EMERALD && slot == 13)
			Bungee.teleport(player, "mqclass");

	}

}
