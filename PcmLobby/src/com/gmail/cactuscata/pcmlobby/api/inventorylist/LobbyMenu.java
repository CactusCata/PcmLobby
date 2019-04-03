package com.gmail.cactuscata.pcmlobby.api.inventorylist;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.cactuscata.pcmapi.api.CustomInventory;
import com.gmail.cactuscata.pcmapi.api.MenuSystem;
import com.gmail.cactuscata.pcmapi.bungee.Bungee;
import com.gmail.cactuscata.pcmlobby.PcmLobby;
import com.gmail.cactuscata.pcmlobby.utils.Helper;

public class LobbyMenu implements CustomInventory {

	@Override
	public String name() {
		return "§1§l✈ §9§lboussole §1§l✈";
	}

	@Override
	public void contents(Player player, Inventory inv) {

		inv.setItem(0,
				Helper.createItem(Material.SIGN, "§bNotre Site", true,
						Arrays.asList("§7Adresse: §6http://pleasecraftme.fr ", "", "§aClique ici pour obtenir",
								"§ale lien du site")));

		inv.setItem(9,
				Helper.createItem(Material.NOTE_BLOCK, "§bNotre Teamspeak", true,
						Arrays.asList("§7Adresse: §ets3.pleasecraftme.fr", "", "§7-------------------------",
								"§cCréation de canaux privés pour tous ! !", "§7-------------------------",
								"§aClique ici pour obtenir le lien", "§ade téléchargement ! §")));

		inv.setItem(18,
				Helper.createItem(Material.PACKED_ICE, "§bNotre Twitter", true,
						Arrays.asList("§7Nom: §ePleaseCraftMe", "§7-------------------------", "   §3████████████",
								"   §3████§f██§3██████", "   §3████§f██§3██████", "   §3████§f██§3██████",
								"   §3████§f████§3████", "   §3████§f████§3████", "   §3████§f██§3██████",
								"   §3████§f██§3██████", "   §3████§f██§3██████", "   §3████§f████§3████",
								"   §3█████§f███§3████", "   §3████████████", "§7-------------------------",
								"§aClique ici pour obtenir le lien", "§acliquable ! §6")));

		inv.setItem(27, Helper.createItem(Material.REDSTONE_BLOCK, "§bNotre Facebook", true,
				Arrays.asList("§7Nom: §ePleaseCraftMeHD", "§7-------------------------", "   §c████████████",
						"   §c████████████", "   §c████§f█§c███████", "   §c████§f██§c██████", "   §c████§f███§c█████",
						"   §c████§f████§c████", "   §c████§f█████§c███", "   §c████§f████§c████",
						"   §c████§f███§c█████", "   §c████§f██§c██████", "   §c████§f█§c███████", "   §c████████████",
						"   §c████████████", "§7-------------------------", "§aClique ici pour obtenir le lien",
						"§acliquable ! §6")));

		inv.setItem(36,
				Helper.createItem(Material.LAPIS_BLOCK, "§bNotre Site", true,
						Arrays.asList("§7Nom: §ePleaseCraftMe", "§7-------------------------", "   §9████████████",
								"   §9███████§f███§9██", "   §9██████§f████§9██", "   §9██████§f██§9████",
								"   §9██████§f██§9████", "   §9█████§f█████§9██", "   §9█████§f████§9███",
								"   §9██████§f██§9████", "   §9██████§f██§9████", "   §9██████§f██§9████",
								"   §9██████§f██§9████", "   §9██████§f██§9████", "§7-------------------------",
								"§aClique ici pour obtenir le lien", "§acliquable ! §6")));

		inv.setItem(4, Helper.createItem(Material.IRON_BARDING, "§a㊗ OpPrison ㊗",
				Arrays.asList("§a➣ Clique ici pour te connecter ", "", "§7-Serveur 100%% PCM !", "")));

		inv.setItem(5,
				Helper.createItem(Material.FIREWORK, "§b㊗ Arcade / Mini-Jeux ㊗",
						Arrays.asList("§a➣ Clique ici pour rejoindre la zone ", "", "§c§lListes des Mini-Jeux:",
								" §e-§aBedwars ", " §e-§aThe Towers ", " §e-§aQuakeCraft ", " §e-§aSkyWars ",
								" §e-§aTeamFortress ", " §e-§aKitPVP ", " §e-§aUHCrun", "")));
		inv.setItem(11,
				Helper.createItem(Material.DIAMOND_BARDING, "§a㊗ Monster Quest ㊗",
						Arrays.asList("§a➣ Clique ici pour te connecter ", "", "§7-Serveur 100%% PCM !",
								"§eDonjons II:", "§7- Donjons 100%% automatiques", "§7- Construits par nos soins",
								"§7 Niveaux §eInfernal §7et §eHardcore", "§7-------------------------",
								"§eMonster Quest :", "§a✠ De retour en Beta 3.0 ! ✠")));

		inv.setItem(12,
				Helper.createItem(Material.ANVIL, "§6㊗ Créatif ㊗",
						Arrays.asList("§a➣ Clique ici pour rejoindre le créatif ", "", "§cInformations :",
								"§7- Serveur §eFreebuild §7ouvert à tous", "§7- Géré à l'aide de notre team de",
								"§7construction : la §ePleaseCraftTeam §7!", "", "§bExclusivité:",
								"§7- Les §6pets §7bientôt de retour !")));

		inv.setItem(13,
				Helper.createItem(Material.DIAMOND_SWORD, "§5㊗ PvP Faction ㊗",
						Arrays.asList("§a➣ Clique ici pour rejoindre le Faction ", "",
								"§7- §ePvP Faction §7- Serveur 100%% faction", "", "§bExclusivité:",
								"§7- Nous sommes actuellement à la §esaison 11 !")));
		inv.setItem(14,
				Helper.createItem(Material.GOLD_SWORD, "§5㊗ PvP Sauvage ㊗",
						Arrays.asList("§a➣ Clique ici pour rejoindre le Sauvage ", "",
								"§7- §ePvP Sauvage §7- Du PvP à l'état pur", "", "§bExclusivité:",
								"§7- Nous sommes actuellement à la §esaison 10 !")));
		inv.setItem(15, Helper.createItem(Material.WATCH, "§e㊗ Semi-RP ㊗",
				Arrays.asList("§a➣ Clique ici pour rejoindre le Semi-RP")));

		inv.setItem(16,
				Helper.createItem(Material.FEATHER, "§b㊗ SkyBlock ㊗",
						Arrays.asList("§a➣ Clique ici pour rejoindre le Skyblock ", "", "§cInformation :",
								"§7- §eSkyblock 2 §7- Accomplissez toutes les missions", "")));
		inv.setItem(22,
				Helper.createItem(Material.SKULL_ITEM, "§e㊗ PvP Area ㊗",
						Arrays.asList("§a➣ Clique ici pour te connecter ", "", "§cInformations :", "", "§7- §dPvP Hard",
								"§7- §6PvP Shot", "§7- §2PvP Soup", "§7- §3PvP Tank", "")));
		inv.setItem(23,
				Helper.createItem(Material.NETHER_STAR, "§e㊗ PvP Ranked ㊗",
						Arrays.asList("§a➣ Clique ici pour te connecter ", "", "§cInformations :", "",
								"§7- §dPvP ranked, Survie + system de rank", "")));

	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {

		if (current.getType() == Material.SIGN && slot == 0) {
			player.sendMessage("§7           ------------------------");
			player.sendMessage("§b                     Notre Site");
			player.sendMessage("§a     Lien cliquable: §ehttp://pleasecraftme.fr");
		} else if (current.getType() == Material.NOTE_BLOCK && slot == 9) {
			player.sendMessage("§7           ------------------------");
			player.sendMessage("§aLien de téléchargement: §ehttp://www.teamspeak.com/?page=downloads");
			player.sendMessage(
					"§7- §bVous devez choisir en fonction de votre système d'exploitation (Windows, Mac ou Linux)");
			player.sendMessage("§7- §bDans tout les cas, choisissez '§oClient' §r§bet de préférence 64 bit !");
		} else if (current.getType() == Material.PACKED_ICE && slot == 18) {
			player.sendMessage("§7           ------------------------");
			player.sendMessage("§b                     Notre Twitter");
			player.sendMessage("§a     Lien cliquable: §ehttps://twitter.com/pleasecraftme");
			player.sendMessage("§7           ------------------------");
		} else if (current.getType() == Material.LAPIS_BLOCK && slot == 36) {
			player.sendMessage("§7           ------------------------");
			player.sendMessage("§b                     Notre Facebook");
			player.sendMessage("§a     Lien cliquable: §ehttps://www.facebook.com/pleasecraftme");
			player.sendMessage("§7           ------------------------");
		} else if (current.getType() == Material.REDSTONE_BLOCK && slot == 27) {
			player.sendMessage("§7           ------------------------");
			player.sendMessage("§b                     Notre Youtube");
			player.sendMessage("§a     Lien cliquable: §ehttps://www.youtube.com/user/PleaseCraftMeHD");
			player.sendMessage("§7           ------------------------");
		} else if (current.getType() == Material.IRON_BARDING && slot == 4)
			Bungee.teleport(player, "opprison");
		else if (current.getType() == Material.FIREWORK && slot == 5) {
			File file = new File(PcmLobby.getInstance().getDataFolder(), "../EssentialsPcm/warps.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			player.teleport(new Location(Bukkit.getWorld(config.getString("warps.arcade.monde")),
					config.getDouble("warps.arcade.x"), config.getDouble("warps.arcade.y"),
					config.getDouble("warps.arcade.z"), (float) config.getDouble("warps.arcade.yaw"),
					(float) config.getDouble("warps.arcade.pitch")));
		} else if (current.getType() == Material.DIAMOND_BARDING && slot == 11)
			MenuSystem.open(player, MonsterQuestMenu.class);
		else if (current.getType() == Material.ANVIL && slot == 12)
			MenuSystem.open(player, CreativeMenu.class);
		else if (current.getType() == Material.DIAMOND_SWORD && slot == 13)
			Bungee.teleport(player, "pfaction11");
		else if (current.getType() == Material.GOLD_SWORD && slot == 14)
			Bungee.teleport(player, "psauvage9");
		else if (current.getType() == Material.WATCH && slot == 15)
			Bungee.teleport(player, "semi-rp");
		else if (current.getType() == Material.FEATHER && slot == 16)
			Bungee.teleport(player, "pskyblock");
		else if (current.getType() == Material.SKULL_ITEM && slot == 22)
			Bungee.teleport(player, "pvparea");
		else if (current.getType() == Material.NETHER_STAR && slot == 23)
			Bungee.teleport(player, "prank1");
	}

	@Override
	public int getSize() {
		return 45;
	}

}
