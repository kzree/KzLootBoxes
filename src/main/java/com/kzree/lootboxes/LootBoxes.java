package com.kzree.lootboxes;

import org.bukkit.plugin.java.JavaPlugin;

public final class LootBoxes extends JavaPlugin {
    private final CommandInitializer commandInitializer;

    public LootBoxes() {
        this.commandInitializer = new CommandInitializer(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        commandInitializer.initializeCommands();
    }
}
