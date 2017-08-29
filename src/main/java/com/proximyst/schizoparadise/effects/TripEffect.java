package com.proximyst.schizoparadise.effects;

import co.aikar.taskchain.TaskChainFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class TripEffect extends Effect {
    private final TaskChainFactory factory;

    public TripEffect(TaskChainFactory factory) {
        super("Trip", 20);
        this.factory = factory;
    }

    @Override
    public void apply(Player player) {
        double x = ThreadLocalRandom.current().nextDouble(5 * 2) - 5; // (5*2)-5 to make sure it can be negative.
        double y = ThreadLocalRandom.current().nextDouble(4 * 2) - 4;
        double z = ThreadLocalRandom.current().nextDouble(5 * 2) - 5;
        factory.newChain()
                .syncFirst(() -> {
                    Location location = player.getLocation();
                    player.getVelocity().add(new Vector(x, y, z));
                    return location;
                })
                .delay(5)
                .syncLast((loc) -> {
                    if (player != null && player.isOnline())
                        player.teleport(loc);
                })
                .execute();
    }
}
