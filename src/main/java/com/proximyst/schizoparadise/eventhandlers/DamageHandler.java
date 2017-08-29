package com.proximyst.schizoparadise.eventhandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageHandler implements Listener {
    @EventHandler
    public void tripDamage(EntityDamageEvent event) {
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
