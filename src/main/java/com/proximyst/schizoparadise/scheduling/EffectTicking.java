package com.proximyst.schizoparadise.scheduling;

import com.proximyst.schizoparadise.Paradise;
import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class EffectTicking extends BukkitRunnable {
    private final Paradise main;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            SchizophrenicPlayer.getPlayer(player).trySymptom(main);
        }
    }
}
