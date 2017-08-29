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
     * Tries to apply a symptom to the player.
     *
     * @param main The SchizoParadise main instance.
     */
    public void trySymptom(Paradise main) {
        Effect[] effectArray = (Effect[]) main.getEffects().toArray();
        double chance = ThreadLocalRandom.current().nextDouble(0, 100);
        Effect effect = chance <= 5.0 ? effectArray[Math.min(ThreadLocalRandom.current().nextInt(effectArray.length), effectArray.length)] : null;
        if (effect == null) return;
        effect.tryApply(player);
    }
}