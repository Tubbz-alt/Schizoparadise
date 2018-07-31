package com.proximyst.schizoparadise.effect

import com.proximyst.schizoparadise.Main
import com.proximyst.schizoparadise.data.SchizophrenicPlayer
import org.bukkit.Material
import org.bukkit.metadata.MetadataValueAdapter
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

class Disc11Effect(private val main : Main) : Effect() {
  override val name : String = "Disc 11"

  override fun apply(player : SchizophrenicPlayer) {
    val location = player.location.clone().add(
        ThreadLocalRandom.current().nextDouble(-5.0, 5.0), -3.0, ThreadLocalRandom.current().nextDouble(-5.0, 5.0)
    )
    main.taskChainFactory
        .newChain<Unit>()
        .sync {
          location.world.playEffect(location, org.bukkit.Effect.RECORD_PLAY, Material.RECORD_11)
          player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 3000, 1))
          player.setMetadata("Blindness", object : MetadataValueAdapter(main) {
            override fun invalidate() {
            }

            override fun value() : Any {
              return true
            }
          })
        }
        .delay(ThreadLocalRandom.current().nextInt(5, 20), TimeUnit.SECONDS)
        .sync {
          location.world.playEffect(location, org.bukkit.Effect.RECORD_PLAY, 0)
        }
        .delay(10)
        .sync {
          if (player.isOnline) {
            player.removePotionEffect(PotionEffectType.BLINDNESS)
            player.removeMetadata("Blindness", main)
          }
        }
        .execute()
  }
}