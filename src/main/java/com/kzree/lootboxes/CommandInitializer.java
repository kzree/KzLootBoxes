package com.kzree.lootboxes;

import com.kzree.lootboxes.commands.GiveLootBoxKeyCommand;

import java.util.Objects;

public class CommandInitializer {
    private final LootBoxes plugin;

    public CommandInitializer(LootBoxes plugin) {
        this.plugin = plugin;
    }

    public void initializeCommands() {
        Objects.requireNonNull(this.plugin.getCommand("givelootboxkey")).setExecutor(new GiveLootBoxKeyCommand(this.plugin));
    }
}
