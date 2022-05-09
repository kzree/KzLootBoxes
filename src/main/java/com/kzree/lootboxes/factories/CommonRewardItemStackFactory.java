package com.kzree.lootboxes.factories;

import com.kzree.lootboxes.LootboxReward;
import org.bukkit.Material;

public class CommonRewardItemStackFactory extends RewardItemStackFactory {
    public CommonRewardItemStackFactory() {
        weightedRandomGenerator
                .addEntry(new LootboxReward(Material.STONE_BRICKS, 16, 64), 0.5);
        weightedRandomGenerator
                .addEntry(new LootboxReward(Material.STONE_BRICKS, 8, 16), 0.25);
        weightedRandomGenerator
                .addEntry(new LootboxReward(Material.STICK, 1, 32), 0.20);
        weightedRandomGenerator
                .addEntry(new LootboxReward(Material.IRON_INGOT, 1, 4), 0.05);
    }
}
