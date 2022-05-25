package com.kzree.lootboxes.listeners;

import com.kzree.lootboxes.LootBoxRarity;
import com.kzree.lootboxes.LootBoxes;
import com.kzree.lootboxes.LootRollRarity;
import com.kzree.lootboxes.factories.LootRollRewardItemStackListFactory;
import com.kzree.lootboxes.utility.WeightedRandomGenerator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

import static com.kzree.lootboxes.utility.NumberUtilities.randomUniqueNumbers;

public class UseLootBoxKeyListener implements Listener {
    private final LootBoxes plugin;
    private final LootRollRewardItemStackListFactory lootRollRewardItemStackListFactory;

    public UseLootBoxKeyListener(LootBoxes plugin) {
        this.plugin = plugin;
        lootRollRewardItemStackListFactory = new LootRollRewardItemStackListFactory(plugin);
    }

    private void createLootBoxGUI(Player player, LootBoxRarity lootBoxRarity) {
        Inventory gui = Bukkit.createInventory(player, 27, "Loot box ("+ lootBoxRarity.getFormattedName() + ")");
        List<ItemStack> items = lootRollRewardItemStackListFactory.generateRandomLootBoxRewardItemStack(lootBoxRarity);
        List<Integer> itemSlots = randomUniqueNumbers(3, 0, 26);
        final int[] itemIndex = {0};
        items.forEach(itemStack -> {
            gui.setItem(itemSlots.get(itemIndex[0]), itemStack);
            itemIndex[0]++;
        });
        player.openInventory(gui);
    }

    private void handleLootBoxUse(Player player, ItemStack lootboxKey, LootBoxRarity lootBoxRarity) {
        player.sendMessage(ChatColor.GREEN + "You just opened a "
                + lootBoxRarity.getPrimaryColor() + lootBoxRarity.getFormattedName()
                + ChatColor.GREEN + " lootbox");
        createLootBoxGUI(player, lootBoxRarity);

        if (lootboxKey.getAmount() > 1) {
            lootboxKey.setAmount(lootboxKey.getAmount() - 1);
        } else {
            player.getInventory().remove(lootboxKey);
        }
    }

    // TODO: Maybe put this in an utils file
    private LootBoxRarity getLootboxRarityFromString(String rarityString) {
        LootBoxRarity rarity;
        try {
            rarity = LootBoxRarity.valueOf(rarityString);
        } catch (IllegalArgumentException e) {
            rarity = LootBoxRarity.COMMON;
        }
        return rarity;
    }

    @EventHandler
    public void onLootBoxKeyUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemMeta itemInMainHandItemMeta = itemInMainHand.getItemMeta();
        if (itemInMainHandItemMeta != null && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            String itemTypeKeyValue = itemInMainHandItemMeta.getPersistentDataContainer()
                    .get(new NamespacedKey(this.plugin, "item-type"), PersistentDataType.STRING);
            String rarityKeyValue = itemInMainHandItemMeta.getPersistentDataContainer()
                    .get(new NamespacedKey(this.plugin, "rarity"), PersistentDataType.STRING);
            if (itemTypeKeyValue != null && rarityKeyValue != null && itemTypeKeyValue.equals("loot-key")) {
                LootBoxRarity rarity = getLootboxRarityFromString(rarityKeyValue);
                handleLootBoxUse(player, itemInMainHand, rarity);
            }
        }
    }
}
