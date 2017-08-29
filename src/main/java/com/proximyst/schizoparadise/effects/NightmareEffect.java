package com.proximyst.schizoparadise.effects;

import org.bukkit.entity.Player;

public class NightmareEffect extends Effect {
    public NightmareEffect() {
        super("Nightmare");
    }

    @Override
    public void apply(Player player) {
        // Not really anything to do. BedHandler in eventhandlers handles this.
    }
}
