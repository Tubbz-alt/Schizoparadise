package com.proximyst.schizoparadise.effects;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class HallucinativeSoundEffect extends Effect {
    private final Sound sounds[] = {
            Sound.ENTITY_ZOMBIE_AMBIENT,
            Sound.ENTITY_BAT_AMBIENT,
            Sound.ENTITY_CAT_AMBIENT,
            Sound.ENTITY_ENDERMEN_AMBIENT,
            Sound.AMBIENT_CAVE,
            Sound.ENTITY_SHULKER_AMBIENT,
            Sound.ENTITY_SKELETON_AMBIENT
    };

    public HallucinativeSoundEffect() {
        super("Hallucinative Sound");
    }

    @Override
    public void apply(Player player) {
        player.playSound(player.getLocation().clone().add(
                ThreadLocalRandom.current().nextDouble(10) - 5,
                -1 * ThreadLocalRandom.current().nextDouble(5, 20),
                ThreadLocalRandom.current().nextDouble(10) - 5
        ), sounds[ThreadLocalRandom.current().nextInt(sounds.length)], .8f, .8f);
    }
}
