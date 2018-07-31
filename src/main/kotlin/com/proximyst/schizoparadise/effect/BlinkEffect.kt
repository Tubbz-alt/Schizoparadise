package com.proximyst.schizoparadise.effect

import com.proximyst.schizoparadise.data.SchizophrenicPlayer
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.concurrent.ThreadLocalRandom

class BlinkEffect : Effect() {
  override val name : String = "Blink"

  override fun apply(player : SchizophrenicPlayer) {
    player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, ThreadLocalRandom.current ().nextInt(3, 9), 1))
  }
}