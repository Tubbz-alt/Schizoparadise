package com.proximyst.schizoparadise.data

import com.proximyst.schizoparadise.Main
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffectType
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class SchizophrenicPlayer(player : Player) : Player by player {
  companion object {
    private val wrappedPlayers = mutableMapOf<UUID, SchizophrenicPlayer>()
    fun new(player : Player) = wrappedPlayers.computeIfAbsent(player.uniqueId) { _ -> SchizophrenicPlayer(player) }
    fun getOrNull(player : Player) = wrappedPlayers[player.uniqueId]
  }

  fun onQuit() {
    if (player.hasMetadata("Blindness")) {
      player.removePotionEffect(PotionEffectType.BLINDNESS)
    }
    wrappedPlayers.remove(this.uniqueId)
  }

  fun trySymptom(
      main : Main,
      chance : Double = ThreadLocalRandom.current().nextDouble(100.0),
      index : Int = ThreadLocalRandom.current().nextInt(0, main.effects.size)
  ) {
    if (index > main.effects.size - 1 || chance > 5.0) { // 5% chance
      return
    }
    val effect = main.effects.toTypedArray()[index]
    effect.apply(this)
  }
}