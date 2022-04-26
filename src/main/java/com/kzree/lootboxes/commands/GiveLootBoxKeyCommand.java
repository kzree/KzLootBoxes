package com.kzree.lootboxes.commands;

import com.kzree.lootboxes.LootBoxRarity;
import com.kzree.lootboxes.LootBoxes;
import com.kzree.lootboxes.factories.LootBoxKeyItemStackFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveLootBoxKeyCommand implements CommandExecutor {
    private final LootBoxes plugin; // TODO: Maybe delete
    private final LootBoxKeyItemStackFactory lootBoxKeyItemStackFactory;

    public GiveLootBoxKeyCommand(LootBoxes plugin) {
        this.plugin = plugin;
        this.lootBoxKeyItemStackFactory = new LootBoxKeyItemStackFactory(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            ItemStack lootBoxKey = lootBoxKeyItemStackFactory.createLootBoxKey(LootBoxRarity.COMMON, 1);
            player.getInventory().addItem(lootBoxKey);
        }
        return false;
    }
}
