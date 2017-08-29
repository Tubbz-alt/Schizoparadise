package com.proximyst.schizoparadise.eventhandlers;

import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import com.proximyst.schizoparadise.effects.TripEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageHandler implements Listener {
    @EventHandler
    public void tripDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        SchizophrenicPlayer wrapped = SchizophrenicPlayer.getPlayer((Player) event.getEntity());
        if (!(wrapped.getCurrentEffect() instanceof TripEffect)
                || !((TripEffect) wrapped.getCurrentEffect()).getIneffect().contains(wrapped.getPlayer().getUniqueId()))
            return;
        switch (event.getCause()) {
            case FALL:
            case CONTACT:
            case FIRE_TICK:
            case FLY_INTO_WALL:
            case VOID:
                event.setCancelled(true);
            default: // To serve the rest. Standard java conventions.
                break;
        }
    }
}
