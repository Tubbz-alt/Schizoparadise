package com.proximyst.schizoparadise.effects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public abstract class Effect {
    private final String name;
    private final double chance; // chance is in percentage. 1-100

    public abstract void apply(Player player);
}
