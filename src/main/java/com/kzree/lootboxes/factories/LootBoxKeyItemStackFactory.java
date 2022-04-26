package com.kzree.lootboxes.factories;

import com.kzree.lootboxes.LootBoxRarity;
import com.kzree.lootboxes.LootBoxes;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LootBoxKeyItemStackFactory {
    public final LootBoxes plugin;
    public LootBoxKeyItemStackFactory(LootBoxes plugin) {
        this.plugin = plugin;
    }

    void createItemStackGlow(ItemStack itemStack) {
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        final ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(meta);
        }
    }

    public ItemStack createLootBoxKey(LootBoxRarity lootBoxRarity, int amount) {
        ItemStack lootBoxKeyStack = new ItemStack(Material.TRIPWIRE_HOOK, amount);
        createItemStackGlow(lootBoxKeyStack);
        return lootBoxKeyStack;
    }
}
