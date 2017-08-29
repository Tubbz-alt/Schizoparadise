package com.proximyst.schizoparadise;

import co.aikar.commands.BukkitCommandManager;
import com.proximyst.schizoparadise.commands.Schizoadmin;
import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import com.proximyst.schizoparadise.effects.Effect;
import com.proximyst.schizoparadise.eventhandlers.BedHandler;
import com.proximyst.schizoparadise.scheduling.EffectTicking;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Paradise extends JavaPlugin {
    /**
     * @deprecated Try to rather use dependency injection where applicable. This is only for debug purposes.
     */
    @Deprecated @Getter private static Paradise instance;
    @Getter private final Set<Effect> effects = new HashSet<>();
    private BukkitCommandManager commandManager;
    private EffectTicking effectTicker;

    public Paradise() {
        //noinspection deprecation --- I marked it as deprecated, IntelliJ. Please.
        instance = this;
    }

    @Override
    public void onEnable() {
        commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new Schizoadmin(this));
        effectTicker = new EffectTicking(this);
        effectTicker.runTaskTimer(this, 0L, 20L * 5); // every 5th second, everyone should have a 5%*symptom chance of experiencing schizophrenia symptoms

        Stream.of(
                new BedHandler()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    @Override
    public void onDisable() {
        new SchizophrenicPlayer(null).clear();
        HandlerList.unregisterAll(this);
        try {
            effectTicker.cancel();
        } catch (IllegalStateException ex) {
            getLogger().warning("The ticking scheduler wasn't scheduled, and thus couldn't be cancelled. No reason as to why this is.");
            ex.printStackTrace();
        }
    }
}
