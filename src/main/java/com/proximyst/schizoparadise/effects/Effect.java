package com.proximyst.schizoparadise.effects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Getter
public abstract class Effect {
    /**
     * The name of the effect/symptom.
     */
    private final String name;
    /**
     * The chance of the effect/symptom to occur in the first place. This adds up with the tick chance.
     */
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
     * @see #getChance() {@link #getChance()} specifies the chance in percentage for this to happen.
     * @see #apply(Player) The method which is called upon correct chance.
     * @param player The player to apply this to.
     */
    public void tryApply(Player player) {
        double chance = ThreadLocalRandom.current().nextDouble(0, 100);
        if (chance <= getChance()) {
            apply(player);
        }
    }
}
