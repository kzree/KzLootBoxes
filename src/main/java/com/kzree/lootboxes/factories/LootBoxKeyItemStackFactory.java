package com.kzree.lootboxes.factories;

import com.kzree.lootboxes.LootBoxRarity;
import com.kzree.lootboxes.LootBoxes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class LootBoxKeyItemStackFactory {
    public final LootBoxes plugin;
    public LootBoxKeyItemStackFactory(LootBoxes plugin) {
        this.plugin = plugin;
    }

    private void createItemStackGlow(ItemStack itemStack) {
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        final ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(meta);
        }
    }

    private String getLootBoxKeyName(LootBoxRarity lootBoxRarity) {
        String nameBase = "Loot key";
        return switch (lootBoxRarity) {
            case COMMON -> ChatColor.BLUE + nameBase;
            case RARE -> ChatColor.LIGHT_PURPLE + nameBase;
            case ULTRA_RARE -> ChatColor.GOLD + nameBase;
        };
    }

    private String getLootBoxKeyLore(LootBoxRarity lootBoxRarity) {
        String baseText = "What might be in this?";
        return switch (lootBoxRarity) {
            case COMMON -> ChatColor.GRAY + baseText;
            case RARE -> ChatColor.WHITE + baseText;
            case ULTRA_RARE -> ChatColor.RED + baseText;
        };
    }

    private void addLootBoxKeyTags(ItemMeta meta, LootBoxRarity lootBoxRarity) {
        if (meta != null) {
            NamespacedKey itemTypeKey = new NamespacedKey(plugin, "item-type");
            meta.getPersistentDataContainer().set(itemTypeKey, PersistentDataType.STRING, "loot-key");
            NamespacedKey itemRarityKey = new NamespacedKey(plugin, "rarity");
            meta.getPersistentDataContainer().set(itemRarityKey, PersistentDataType.STRING, lootBoxRarity.name());
        }
    }

    private void createLootBoxKeyMeta(ItemStack itemStack, LootBoxRarity lootBoxRarity) {
        final ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(getLootBoxKeyName(lootBoxRarity));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(getLootBoxKeyLore(lootBoxRarity));
            addLootBoxKeyTags(meta, lootBoxRarity);
            meta.setLore(lore);
        }
        itemStack.setItemMeta(meta);
    }

    public ItemStack createLootBoxKey(LootBoxRarity lootBoxRarity, int amount) {
        ItemStack lootBoxKeyStack = new ItemStack(Material.TRIPWIRE_HOOK, amount);
        createItemStackGlow(lootBoxKeyStack);
        createLootBoxKeyMeta(lootBoxKeyStack, lootBoxRarity);
        return lootBoxKeyStack;
    }
}
