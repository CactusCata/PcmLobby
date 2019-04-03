package com.gmail.cactuscata.pcmlobby.api.inventorylist;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.cactuscata.pcmapi.api.CustomInventory;
import com.gmail.cactuscata.pcmapi.bungee.Bungee;
import com.gmail.cactuscata.pcmlobby.utils.Helper;

public class CreativeMenu implements CustomInventory {

	@Override
	public int getSize() {
		return 27;
	}

	@Override
	public String name() {
		return "§1§l✈ §9§lCreatif §1§l✈";
	}

	@Override
	public void contents(Player player, Inventory inv) {
		inv.setItem(12, Helper.createItem(Material.ANVIL, "§6§lCreatif 1.8"));
		inv.setItem(14, Helper.createItem(Material.ANVIL, 2, "§6§lNew Creatif 1.10"));

	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack item, int slot) {
		if (item.getType() == Material.ANVIL && slot == 12 && item.getAmount() == 1)
			Bungee.teleport(player, "pcreatif");
		else if (item.getType() == Material.ANVIL && slot == 14 && item.getAmount() == 2)
			Bungee.teleport(player, "pcreatif2");

	}

}
