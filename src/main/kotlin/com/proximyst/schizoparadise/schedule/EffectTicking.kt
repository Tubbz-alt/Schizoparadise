package com.proximyst.schizoparadise.schedule

import com.proximyst.schizoparadise.Main
import com.proximyst.schizoparadise.data.SchizophrenicPlayer
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class EffectTicking(private val main : Main) : BukkitRunnable() {
  override fun run() {
    Bukkit.getOnlinePlayers().forEach { SchizophrenicPlayer.new(it).trySymptom(main) }
  }
}