package com.proximyst.schizoparadise.eventhandlers;

import com.proximyst.schizoparadise.Utilities;
import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import com.proximyst.schizoparadise.effects.NightmareEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedHandler implements Listener {
    @EventHandler
    public void onEnterBed(PlayerBedEnterEvent event) {
        if (SchizophrenicPlayer.getPlayer(event.getPlayer()).getCurrentEffect() instanceof NightmareEffect && Utilities.INST.isNight(event.getPlayer().getWorld())) {
            event.setCancelled(true);
            // TODO: Custom message
            event.getPlayer().sendMessage(Utilities.colour("&a&l> &cAs the night pollutes your mind, you're unable to sleep."));
        }
    }
}
