package com.proximyst.schizoparadise.effects;

import com.proximyst.schizoparadise.Utilities;
import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;

@RequiredArgsConstructor
@Getter
public abstract class Effect {
    private static final Set<BiPredicate<Effect, Player>> applicationPredicates = new LinkedHashSet<>();

    static {
        // NIGHTMARE CHECK
        applicationPredicates.add((effect, player) -> Utilities.INST.isDay(player.getWorld()) || effect instanceof NightmareEffect);
    }

    /**
     * The name of the effect/symptom.
     */
    private final String name;
    /**
     * The chance of the effect/symptom to occur in the first place. This adds up with the tick chance.
     */
    // TODO: Make chance customisable.
    private final double chance; // chance is in percentage. 1-100

    /**
     * Applies the effect/symptom to the player specified.
     *
     * @param player Player to apply this to.
     */
    public abstract void apply(Player player);

    /**
     * Applies the effect/symptom to the player specified if it fits the chance upon call.
     *
     * @param player The player to apply this to.
     * @see #getChance() {@link #getChance()} specifies the chance in percentage for this to happen.
     * @see #apply(Player) The method which is called upon correct chance.
     */
    public void tryApply(Player player) {
        double chance = ThreadLocalRandom.current().nextDouble(0, 100);
        if (chance <= getChance()) {
            if (applicationPredicates.stream().anyMatch(pred -> !pred.test(this, player))) return;

            SchizophrenicPlayer.getPlayer(player).setCurrentEffect(this);
            apply(player);
        }
    }
}
