package com.kzree.lootboxes;

import com.kzree.lootboxes.listeners.UseLootBoxKeyListener;

public class ListenerInitializer {
    private final LootBoxes plugin;

    public ListenerInitializer(LootBoxes plugin) {
        this.plugin = plugin;
    }

    public void initializeListeners() {
        this.plugin.getServer().getPluginManager().registerEvents(new UseLootBoxKeyListener(plugin), this.plugin);
    }
}
