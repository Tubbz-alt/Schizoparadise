package com.proximyst.schizoparadise;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChainFactory;
import com.proximyst.schizoparadise.commands.Schizoadmin;
import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import com.proximyst.schizoparadise.effects.Effect;
import com.proximyst.schizoparadise.effects.EyeBlinkEffect;
import com.proximyst.schizoparadise.effects.NightmareEffect;
import com.proximyst.schizoparadise.effects.TripEffect;
import com.proximyst.schizoparadise.eventhandlers.BedHandler;
import com.proximyst.schizoparadise.eventhandlers.DamageHandler;
import com.proximyst.schizoparadise.scheduling.EffectTicking;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Getter // Nothing to hide. If a hook is needed, it's here.
public class Paradise extends JavaPlugin {
    private final Set<Effect> effects = new HashSet<>();
    private BukkitCommandManager commandManager;
    private EffectTicking effectTicker;
    private TaskChainFactory chainFactory;

    @Override
    public void onEnable() {
        chainFactory = BukkitTaskChainFactory.create(this);
        commandManager = new BukkitCommandManager(this);

        Stream.of(
                new TripEffect(chainFactory),
                new NightmareEffect(),
                new EyeBlinkEffect(chainFactory)
        ).forEach(effects::add);

        commandManager.registerCommand(new Schizoadmin(this));
        effectTicker = new EffectTicking(this);
        effectTicker.runTaskTimer(this, 0L, 20L * 5); // every 5th second, everyone should have a 5%*symptom chance of experiencing schizophrenia symptoms

        Stream.of(
                new BedHandler(),
                new DamageHandler()
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
