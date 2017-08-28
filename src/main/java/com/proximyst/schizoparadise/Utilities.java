package com.proximyst.schizoparadise;

import org.bukkit.ChatColor;
import org.bukkit.World;

public enum Utilities {
    INST;

    public static final String colour(Object... in) {
        StringBuilder builder = new StringBuilder();
        for (Object o : in) {
            builder.append(o);
        }
        return ChatColor.translateAlternateColorCodes('&', builder.toString());
    }

    public boolean isNight(World world) {
        long time = world.getTime();
        return time > 6000 && time < 18000;
    }

    public boolean isDay(World world) {
        return !isNight(world);
    }
}
