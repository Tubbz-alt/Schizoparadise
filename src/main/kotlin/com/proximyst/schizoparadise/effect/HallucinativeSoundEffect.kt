package com.proximyst.schizoparadise.effect

import com.proximyst.schizoparadise.data.SchizophrenicPlayer
import org.bukkit.Sound
import java.util.concurrent.ThreadLocalRandom

class HallucinativeSoundEffect : Effect() {
  val sounds = arrayOf(
    Sound.ENTITY_ZOMBIE_AMBIENT,
    Sound.ENTITY_BAT_AMBIENT,
    Sound.ENTITY_CAT_AMBIENT,
    Sound.ENTITY_ENDERMEN_AMBIENT,
    Sound.AMBIENT_CAVE,
    Sound.ENTITY_SHULKER_AMBIENT,
    Sound.ENTITY_SKELETON_AMBIENT
  )

  override val name : String = "Hallucinative Sounds"

  override fun apply(player : SchizophrenicPlayer) {
    (0..ThreadLocalRandom.current().nextInt(1, 3)).forEach { _ ->
      player.playSound(
        player.location.clone().add(
          ThreadLocalRandom.current().nextDouble(10.0) - 5,
          -1 * ThreadLocalRandom.current().nextDouble(5.0, 20.0),
          ThreadLocalRandom.current().nextDouble(10.0) - 5
        ), sounds[ThreadLocalRandom.current().nextInt(sounds.size)], .8f, .8f
      )
    }
  }
}