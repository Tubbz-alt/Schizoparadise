package com.proximyst.schizoparadise;

import com.proximyst.schizoparadise.effects.Effect;
import com.proximyst.schizoparadise.scheduling.EffectTicking;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Paradise extends JavaPlugin {
    /**
     * @deprecated Try to rather use dependency injection where applicable. This is only for debug purposes.
     */
    @Deprecated @Getter private static Paradise instance;
    @Getter private final Set<Effect> effects = new HashSet<>();
    private EffectTicking effectTicker;

    public Paradise() {
        //noinspection deprecation --- I marked it as deprecated, IntelliJ. Please.
        instance = this;
    }

    @Override
    public void onEnable() {
        effectTicker = new EffectTicking(this);
        effectTicker.runTaskTimer(this, 0L, 20L * 5); // every 5th second, everyone should have a 5%*symptom chance of experiencing schizophrenia symptoms
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        try {
            effectTicker.cancel();
        } catch (IllegalStateException ex) {
            getLogger().warning("The ticking scheduler wasn't scheduled, and thus couldn't be cancelled. No reason as to why this is.");
            ex.printStackTrace();
        }
    }
}
