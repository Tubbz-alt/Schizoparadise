package com.proximyst.schizoparadise.effects;

import co.aikar.taskchain.TaskChainFactory;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public class EyeBlinkEffect extends Effect {
    private final TaskChainFactory factory;

    public EyeBlinkEffect(TaskChainFactory factory) {
        super("Blink", 80);
        this.factory = factory;
    }

    @Override
    public void apply(Player player) {
        if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) return;
        factory.newChain()
                .sync(() -> player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0)))
                .delay(ThreadLocalRandom.current().nextInt(3, 9)) // Wait random ticks, but not long, anyways.
                .sync(() -> player.removePotionEffect(PotionEffectType.BLINDNESS))
                .execute();
    }
}
