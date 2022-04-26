package com.kzree.lootboxes.commands;

import com.kzree.lootboxes.LootBoxRarity;
import com.kzree.lootboxes.LootBoxes;
import com.kzree.lootboxes.factories.LootBoxKeyItemStackFactory;
import com.kzree.lootboxes.utility.NumberUtilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.OptionalInt;

public class GiveLootBoxKeyCommand implements CommandExecutor {
    String argsError = ChatColor.RED + "[Error]: " + ChatColor.DARK_RED + "Wrong arguments. Usage: /givelootboxkey <amount> <rarity>";

    private final LootBoxes plugin; // TODO: Maybe delete
    private final LootBoxKeyItemStackFactory lootBoxKeyItemStackFactory;

    public GiveLootBoxKeyCommand(LootBoxes plugin) {
        this.plugin = plugin;
        this.lootBoxKeyItemStackFactory = new LootBoxKeyItemStackFactory(plugin);
    }

    LootBoxRarity getRarityFromStringInput(String input) {
        return switch (input) {
            case "ultrarare" -> LootBoxRarity.ULTRA_RARE;
            case "rare" -> LootBoxRarity.RARE;
            default -> LootBoxRarity.COMMON;
        };
    }

    boolean isRarityArgValid(String rarity, Player player) {
        String errorMsg = ChatColor.RED + "[Error]: " + ChatColor.DARK_RED + "Rarity argument can only be common, rare or ultrarare";
        if (rarity == null) return true;
        if (!rarity.equals("ultrarare") && !rarity.equals("rare") && !rarity.equals("common")) {
            player.sendMessage(errorMsg);
            return false;
        }
        return true;
    }

    boolean isAmountArgValid(int amount, Player player) {
        String emptyErrorMsg = ChatColor.RED + "[Error]: " + ChatColor.DARK_RED + "Amount has to be a number";
        if (amount > 64) {
            player.sendMessage(emptyErrorMsg);
            return false;
        }
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OptionalInt amountArg = OptionalInt.empty();
        String rarityArg = null;
        if (sender instanceof Player player) {
            if (args.length > 0) {
                amountArg = args[0] != null ? NumberUtilities.convertStringToIntegerWithOptionalInt(args[0]) : OptionalInt.of(1);
            }
            if (args.length > 1) {
                rarityArg = args[1];
            }
            if (amountArg.isEmpty()) {
                player.sendMessage(argsError);
                return false;
            }
            if (isRarityArgValid(rarityArg, player) && isAmountArgValid(amountArg.getAsInt(), player)) {
                ItemStack lootBoxKey = lootBoxKeyItemStackFactory.createLootBoxKey(rarityArg == null
                        ? LootBoxRarity.COMMON
                        : getRarityFromStringInput(rarityArg),
                        amountArg.getAsInt());
                player.getInventory().addItem(lootBoxKey);
            }
        }
        return true;
    }
}
