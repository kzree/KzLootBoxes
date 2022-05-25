package com.kzree.lootboxes.factories;

import com.kzree.lootboxes.*;
import com.kzree.lootboxes.utility.WeightedRandomGenerator;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.kzree.lootboxes.utility.NumberUtilities.randomIntBetween;

public class LootRollRewardItemStackListFactory {
    private final LootBoxes plugin;
    private final LootRollRarityRandomGenerator lootRollRarityRandomGenerator;
    protected final WeightedRandomGenerator<LootboxReward> commonRewardRandomGenerator;
    private final WeightedRandomGenerator<LootboxReward> rareRewardRandomGenerator;
    private final WeightedRandomGenerator<LootboxReward> ultraRareRewardRandomGenerator;


    public LootRollRewardItemStackListFactory(LootBoxes plugin) {
        this.plugin = plugin;
        lootRollRarityRandomGenerator = new LootRollRarityRandomGenerator();
        commonRewardRandomGenerator = new WeightedRandomGenerator<>();
        rareRewardRandomGenerator = new WeightedRandomGenerator<>();
        ultraRareRewardRandomGenerator = new WeightedRandomGenerator<>();

        Arrays.asList(LootRollRarity.values()).forEach(this::initializeRewardGenerator);
    }

    // Initializes reward random generators. Rewards are generated not by loot box rarity but by the loot roll rarity
    // which are generated in the generateRandomLootBoxRewardItemStack function
    private void initializeRewardGenerator(LootRollRarity lootRollRarity) {
        String configurationSectionName = lootRollRarity.name().toLowerCase();
        ConfigurationSection section = Objects.requireNonNull(this.plugin.getConfig().getConfigurationSection("rewards")).getConfigurationSection(configurationSectionName);
        if (section != null) {
            for (String key : section.getKeys(false)) {
                ConfigurationSection itemData = section.getConfigurationSection(key);
                if (itemData != null) {
                    Material rewardMaterial = Material.valueOf(Objects.requireNonNull(itemData.getString("material")).toUpperCase());
                    LootboxReward reward = new LootboxReward(rewardMaterial, itemData.getInt("min_amount"), itemData.getInt("max_amount"));
                    switch (lootRollRarity) {
                        case COMMON -> commonRewardRandomGenerator.addEntry(reward, itemData.getDouble("weight"));
                        case RARE -> rareRewardRandomGenerator.addEntry(reward, itemData.getDouble("weight"));
                        case ULTRA_RARE -> ultraRareRewardRandomGenerator.addEntry(reward, itemData.getDouble("weight"));
                    }
                }
            }
        }
    }

    // Creates new item based on generated reward
    private ItemStack handleLootBoxReward(LootboxReward generatedReward) {
        int amount = randomIntBetween(generatedReward.getMinAmount(), generatedReward.getMaxAmount());
        return new ItemStack(generatedReward.getMaterial(), amount);
    }

    // Generates the reward based on loot roll rarity
    private ItemStack generateReward(LootRollRarity lootRollRarity) {
        return switch (lootRollRarity) {
            case RARE -> handleLootBoxReward(rareRewardRandomGenerator.getRandom());
            case ULTRA_RARE, LEGENDARY -> handleLootBoxReward(ultraRareRewardRandomGenerator.getRandom());
            default -> handleLootBoxReward(commonRewardRandomGenerator.getRandom());
        };
    }

    // Is given a loot box rarity and then generates 3 items based on the roll rarities of the loot box rarity
    // If roll rarity is nothing then nothing will be generated, if no items are generated then pity reward is given
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
