package com.kzree.lootboxes.factories;

import com.kzree.lootboxes.LootBoxRarity;
import com.kzree.lootboxes.LootRollRarity;
import com.kzree.lootboxes.LootRollRarityRandomGenerator;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LootRollRewardItemStackListFactory {
    private final LootRollRarityRandomGenerator lootRollRarityRandomGenerator;
    private final CommonRewardItemStackFactory commonRewardItemStackFactory;

    public LootRollRewardItemStackListFactory() {
        lootRollRarityRandomGenerator = new LootRollRarityRandomGenerator();
        commonRewardItemStackFactory = new CommonRewardItemStackFactory();
    }

    private ItemStack generateReward(LootRollRarity lootRollRarity) {
        return switch (lootRollRarity) {
            default -> commonRewardItemStackFactory.generateRandomizedLoot();
        };
    }

    public List<ItemStack> generateRandomLootBoxRewardItemStack(LootBoxRarity lootBoxRarity) {
        List<ItemStack> rolledItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LootRollRarity itemStackRollRarity = lootRollRarityRandomGenerator.generateLootRollRarity(lootBoxRarity);
            if (itemStackRollRarity != LootRollRarity.NOTHING) {
                ItemStack rolledItem = generateReward(itemStackRollRarity);
                rolledItems.add(rolledItem);
            }
        }
        if (rolledItems.size() == 0) {
            rolledItems.add(generateReward(LootRollRarity.COMMON));
        }
        return rolledItems;
    }
}
