package com.kzree.lootboxes.factories;

import com.kzree.lootboxes.LootboxReward;
import com.kzree.lootboxes.utility.WeightedRandomGenerator;
import org.bukkit.inventory.ItemStack;

import static com.kzree.lootboxes.utility.NumberUtilities.randomIntBetween;

// Once loading from config is implemented, can delete this and it's children.
// Currently, just a placeholder functionality to keep line numbers down
public abstract class RewardItemStackFactory {
    protected final WeightedRandomGenerator<LootboxReward> weightedRandomGenerator;

    protected RewardItemStackFactory() {
        this.weightedRandomGenerator = new WeightedRandomGenerator<>();
    }

    public ItemStack generateRandomizedLoot() {
        LootboxReward generatedReward = weightedRandomGenerator.getRandom();
        int amount = randomIntBetween(generatedReward.getMinAmount(), generatedReward.getMaxAmount());
        return new ItemStack(generatedReward.getMaterial(), amount);
    }
}
