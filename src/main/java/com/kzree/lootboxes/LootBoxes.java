package com.kzree.lootboxes;

import org.bukkit.plugin.java.JavaPlugin;

public final class LootBoxes extends JavaPlugin {
    private final CommandInitializer commandInitializer;
    private final ListenerInitializer listenerInitializer;

    public LootBoxes() {
        this.commandInitializer = new CommandInitializer(this);
        this.listenerInitializer = new ListenerInitializer(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        commandInitializer.initializeCommands();
        listenerInitializer.initializeListeners();
    }
}
