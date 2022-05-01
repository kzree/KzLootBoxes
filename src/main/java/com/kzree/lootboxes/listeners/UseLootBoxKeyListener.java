package com.kzree.lootboxes.listeners;

import com.kzree.lootboxes.LootBoxes;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class UseLootBoxKeyListener implements Listener {
    private final LootBoxes plugin;

    public UseLootBoxKeyListener(LootBoxes plugin) {
        this.plugin = plugin;
    }

    private void handleLootBoxUse(Player player, ItemStack lootboxKey) {
        player.sendMessage(ChatColor.GREEN + "You just tried to open a lootbox!");

        if (lootboxKey.getAmount() > 1) {
            lootboxKey.setAmount(lootboxKey.getAmount() - 1);
        } else {
            player.getInventory().remove(lootboxKey);
        }
    }

    @EventHandler
    public void onLootBoxKeyUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemMeta itemInMainHandItemMeta = itemInMainHand.getItemMeta();
        NamespacedKey namespacedKey = new NamespacedKey(this.plugin, "item-type");
        if (itemInMainHandItemMeta != null) {
            String keyValue = itemInMainHandItemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
            if (keyValue != null && keyValue.equals("loot-key")) {
                handleLootBoxUse(player, itemInMainHand);
            }
        }
    }
}
