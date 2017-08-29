package com.proximyst.schizoparadise.effects;

import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiPredicate;

@RequiredArgsConstructor
@Getter
public abstract class Effect {
    private static final Set<BiPredicate<Effect, Player>> applicationPredicates = new LinkedHashSet<>();

    static {
        // Put checks here, if ever needed.
    }

    /**
     * The name of the effect/symptom.
     */
    private final String name;

    /**
     * Applies the effect/symptom to the player specified.
     *
     * @param player Player to apply this to.
     */
    public abstract void apply(Player player);

    /**
     * Applies the effect/symptom to the player specified.
     *
     * @param player The player to apply this to.
     * @see #apply(Player) The method which is called.
     */
    public void tryApply(Player player) {
        if (applicationPredicates.stream().anyMatch(pred -> !pred.test(this, player))) return;

        SchizophrenicPlayer.getPlayer(player).setCurrentEffect(this);
        apply(player);
    }
}
