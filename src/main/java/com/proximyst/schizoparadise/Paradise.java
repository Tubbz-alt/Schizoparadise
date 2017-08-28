package com.proximyst.schizoparadise;

import com.proximyst.schizoparadise.effects.Effect;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Paradise extends JavaPlugin {
    /**
     * @deprecated Try to rather use dependency injection where applicable. This is only for debug purposes.
     */
    @Deprecated @Getter private static Paradise instance;
    @Getter private final Set<Effect> effects = new HashSet<>();

    public Paradise() {
        instance = this;
    }

    @Override
    public void onEnable() {
    }
}
