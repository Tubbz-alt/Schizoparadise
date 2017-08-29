package com.proximyst.schizoparadise.effects;

import co.aikar.taskchain.TaskChainFactory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class Disc11Effect extends Effect {
    private final TaskChainFactory chainFactory;

    public Disc11Effect(TaskChainFactory chainFactory) {
        super("Disc 11");
        this.chainFactory = chainFactory;
    }

    @Override
    public void apply(Player player) {
        Location location = player.getLocation().clone().add(
                new Vector(ThreadLocalRandom.current().nextInt(-5, 5), -3, ThreadLocalRandom.current().nextInt(-5, 5))
        );
        chainFactory.newChain()
                .sync(() -> location.getWorld().playEffect(location, org.bukkit.Effect.RECORD_PLAY, Material.RECORD_11))
                .delay(ThreadLocalRandom.current().nextInt(20, 60))
                .sync(() -> location.getWorld().playEffect(location, org.bukkit.Effect.RECORD_PLAY, 0))
                .execute();
    }
}
