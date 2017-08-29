package com.proximyst.schizoparadise.effects;

import co.aikar.taskchain.TaskChainFactory;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TripEffect extends Effect {
    private final TaskChainFactory factory;
    @Getter private final Set<UUID> ineffect = new HashSet<>();

    public TripEffect(TaskChainFactory factory) {
        super("Trip");
        this.factory = factory;
    }

    @Override
    public void apply(Player player) {
        double x = ThreadLocalRandom.current().nextDouble(5 * 2) - 5; // (5*2)-5 to make sure it can be negative
        double y = ThreadLocalRandom.current().nextDouble(4, 4 * 2);
        double z = ThreadLocalRandom.current().nextDouble(5 * 2) - 5;
        Location location = player.getLocation();
        factory.newChain()
                .sync(() -> {
                    ineffect.add(player.getUniqueId());
                    player.setVelocity(player.getVelocity().add(new Vector(x, y, z)));
                    player.getVelocity().add(new Vector(x, y, z));
                })
                .delay(30)
                .sync(() -> {
                    ineffect.remove(player.getUniqueId());
                    if (player.isOnline())
                        player.teleport(location);
                })
                .execute();
    }
}
