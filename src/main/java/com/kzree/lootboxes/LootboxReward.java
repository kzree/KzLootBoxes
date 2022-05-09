package com.kzree.lootboxes;

import org.bukkit.Material;

public class LootboxReward {
    private final Material material;
    private final LootBoxRewardType lootBoxRewardType;
    private final int minAmount;
    private final int maxAmount;

    public LootboxReward(Material material, LootBoxRewardType lootBoxRewardType, int minAmount, int maxAmount) {
        this.material = material;
        this.lootBoxRewardType = lootBoxRewardType;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public LootboxReward(Material material, int minAmount, int maxAmount) {
        this.material = material;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.lootBoxRewardType = LootBoxRewardType.MATERIAL;
    }

    public Material getMaterial() {
        return material;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public LootBoxRewardType getLootBoxRewardType() {
        return lootBoxRewardType;
    }
}
