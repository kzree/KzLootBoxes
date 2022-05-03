package com.kzree.lootboxes;

public enum LootRollRarity {
    NOTHING(0.2, 0.0, 0.0),
    COMMON(0.6, 0.525, 0.4),
    RARE(0.15, 0.25, 0.35),
    ULTRA_RARE(0.05, 0.1, 0.2),
    LEGENDARY(0.0, 0.025, 0.05);

    private final double commonLootBoxRarity;
    private final double rareLootBoxRarity;
    private final double ultraRareLootBoxRarity;

    LootRollRarity(double commonLootBoxRarity, double rareLootBoxRarity, double ultraRareLootBoxRarity) {
        this.commonLootBoxRarity = commonLootBoxRarity;
        this.rareLootBoxRarity = rareLootBoxRarity;
        this.ultraRareLootBoxRarity = ultraRareLootBoxRarity;
    }

    public double getCommonLootBoxRarity() {
        return commonLootBoxRarity;
    }

    public double getRareLootBoxRarity() {
        return rareLootBoxRarity;
    }

    public double getUltraRareLootBoxRarity() {
        return ultraRareLootBoxRarity;
    }

    public double getRarityByLootBoxRarityValue(LootBoxRarity lootBoxRarity) {
        return switch (lootBoxRarity) {
            case COMMON -> this.commonLootBoxRarity;
            case RARE -> this.rareLootBoxRarity;
            case ULTRA_RARE -> this.ultraRareLootBoxRarity;
        };
    }
}
