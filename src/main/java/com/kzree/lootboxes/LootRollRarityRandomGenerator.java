package com.kzree.lootboxes;

import com.kzree.lootboxes.utility.WeightedRandomGenerator;

import java.util.Arrays;

public class LootRollRarityRandomGenerator {
    private final WeightedRandomGenerator<LootRollRarity> commonLootBoxRollRarityGenerator;
    private final WeightedRandomGenerator<LootRollRarity> rareLootBoxRollRarityGenerator;
    private final WeightedRandomGenerator<LootRollRarity> ultraRareLootBoxRollRarityGenerator;

    public LootRollRarityRandomGenerator() {
        commonLootBoxRollRarityGenerator = new WeightedRandomGenerator<>();
        rareLootBoxRollRarityGenerator = new WeightedRandomGenerator<>();
        ultraRareLootBoxRollRarityGenerator = new WeightedRandomGenerator<>();
        this.initializeRarityGenerators();
    }

    private void initializeRarityGenerators() {
        Arrays.asList(LootRollRarity.values()).forEach(lootRollRarity -> {
            double commonRarityWeight = lootRollRarity.getCommonLootBoxRarity();
            double rareRarityWeight = lootRollRarity.getRareLootBoxRarity();
            double ultraRareRarityWeight = lootRollRarity.getUltraRareLootBoxRarity();

            if (commonRarityWeight > 0.0) {
                this.commonLootBoxRollRarityGenerator.addEntry(lootRollRarity, commonRarityWeight);
            }
            if (rareRarityWeight > 0.0) {
                this.rareLootBoxRollRarityGenerator.addEntry(lootRollRarity, rareRarityWeight);
            }
            if (ultraRareRarityWeight > 0.0) {
                this.ultraRareLootBoxRollRarityGenerator.addEntry(lootRollRarity, ultraRareRarityWeight);
            }
        });
    }

    public LootRollRarity generateLootRollRarity(LootBoxRarity lootBoxRarity) {
        return switch (lootBoxRarity) {
            case COMMON -> this.commonLootBoxRollRarityGenerator.getRandom();
            case RARE -> this.rareLootBoxRollRarityGenerator.getRandom();
            case ULTRA_RARE -> this.ultraRareLootBoxRollRarityGenerator.getRandom();
        };
    }
}
