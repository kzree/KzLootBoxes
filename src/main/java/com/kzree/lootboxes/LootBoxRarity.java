package com.kzree.lootboxes;

import org.bukkit.ChatColor;

public enum LootBoxRarity {
    COMMON(ChatColor.BLUE, ChatColor.GRAY, "Common"),
    RARE(ChatColor.LIGHT_PURPLE, ChatColor.WHITE, "Rare"),
    ULTRA_RARE(ChatColor.GOLD, ChatColor.RED, "Ultra rare");

    private final ChatColor primaryColor;
    private final ChatColor secondaryColor;
    private final String formattedName;

    LootBoxRarity(ChatColor primaryColor, ChatColor secondaryColor, String formattedName) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.formattedName = formattedName;
    }

    public ChatColor getPrimaryColor() {
        return primaryColor;
    }

    public ChatColor getSecondaryColor() {
        return secondaryColor;
    }

    public String getFormattedName() {
        return formattedName;
    }
}
