package com.proximyst.schizoparadise.scheduling;

import com.proximyst.schizoparadise.Paradise;
import com.proximyst.schizoparadise.effects.Effect;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class EffectTicking extends BukkitRunnable {
    private final Paradise main;

    @Override
    public void run() {
        Effect[] effectArray = (Effect[]) main.getEffects().toArray();
        for (Player player : Bukkit.getOnlinePlayers()) {
            double chance = ThreadLocalRandom.current().nextDouble(0, 100);
            Effect effect = chance <= 5.0 ? effectArray[Math.min(ThreadLocalRandom.current().nextInt(effectArray.length), effectArray.length)] : null;
            if (effect == null) continue;
            effect.tryApply(player);
        }
    }
}
