package com.proximyst.schizoparadise.data;

import com.proximyst.schizoparadise.Paradise;
import com.proximyst.schizoparadise.effects.Effect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Getter
public class SchizophrenicPlayer {
    private static final Map<UUID, SchizophrenicPlayer> schizophrenics = new HashMap<>();

    private final Player player;
    @Setter private Effect currentEffect = null;

    public static SchizophrenicPlayer getPlayer(Player bukkitPlayer) {
        SchizophrenicPlayer player = schizophrenics.get(bukkitPlayer.getUniqueId());
        if (player != null) {
            return player;
        }
        player = new SchizophrenicPlayer(bukkitPlayer);
        schizophrenics.put(bukkitPlayer.getUniqueId(), player);
        return player;
    }

    /**
     * Clear the schizophrenic map.
     *
     * @throws SecurityException Thrown when one accesses the method without a null player.
     */
    public void clear() throws SecurityException {
        if (player != null) {
            throw new SecurityException("Only instances with null player are allowed to clear.");
        }
        SchizophrenicPlayer.schizophrenics.clear();
    }

    /**
     * Tries to apply a symptom to the player using the specified chance.
     *
     * @param main The SchizoParadise main instance.
     * @param chance The chance to check against.
     */
    public void trySymptom(Paradise main, double chance) {
        trySymptom(main, chance, ThreadLocalRandom.current().nextInt(0, main.getEffects().size()));
    }

    /**
     * Tries to apply the specified index of Effect using the specified chance.
     *
     * @param main The SchizoParadise main instance.
     * @param chance The chance to check against.
     * @param index The index of the effect to use.
     */
    public void trySymptom(Paradise main, double chance, double index) {
        Effect[] effectArray = main.getEffects().toArray(new Effect[main.getEffects().size()]);
//        main.getLogger().info("DEBUG: effectArray.length -> " + effectArray.length);
//        main.getLogger().info("DEBUG: chance -> " + chance);
        Effect effect = chance <= 5.0 ? effectArray[Math.min((int) Math.max(0, index), effectArray.length - 1)] : null;
//        main.getLogger().info("DEBUG: effect -> " + (effect == null ? "None" : effect.getName()));
        if (effect == null) return;
//        main.getLogger().info("DEBUG: Applying!");
        effect.tryApply(player);
    }

    /**
     * Tries to apply a symptom to the player.
     *
     * @param main The SchizoParadise main instance.
     */
    public void trySymptom(Paradise main) {
        trySymptom(main, ThreadLocalRandom.current().nextDouble(0, 100));
    }
}
