package com.proximyst.schizoparadise.effects;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class HallucinativeSoundEffect extends Effect {
    public HallucinativeSoundEffect() {
        super("Hallucinative Sound");
    }

    @Override
    public void apply(Player player) {
        player.playSound(player.getLocation().clone().add(
                ThreadLocalRandom.current().nextDouble(10) - 5,
                ThreadLocalRandom.current().nextDouble(3) - 6,
                ThreadLocalRandom.current().nextDouble(10) - 5
        ), Sound.ENTITY_ZOMBIE_AMBIENT, .8f, .8f);
    }
}
