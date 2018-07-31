package com.proximyst.schizoparadise

import co.aikar.commands.BukkitCommandManager
import co.aikar.taskchain.BukkitTaskChainFactory
import co.aikar.taskchain.TaskChainFactory
import com.proximyst.schizoparadise.data.SchizophrenicPlayer
import com.proximyst.schizoparadise.effect.BlinkEffect
import com.proximyst.schizoparadise.effect.Disc11Effect
import com.proximyst.schizoparadise.effect.Effect
import com.proximyst.schizoparadise.effect.HallucinativeSoundEffect
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
  lateinit var commandManager : BukkitCommandManager
  lateinit var taskChainFactory : TaskChainFactory
  lateinit var effects : Set<Effect>

  override fun onEnable() {
    commandManager = BukkitCommandManager(this)
    taskChainFactory = BukkitTaskChainFactory.create(this)
    effects = setOf(Disc11Effect(this), BlinkEffect(), HallucinativeSoundEffect())

    server.pluginManager.registerEvents(object : Listener {
      @EventHandler
      fun quit(event : PlayerQuitEvent) {
        SchizophrenicPlayer.getOrNull(event.player)?.onQuit()
      }
    }, this)
  }

  override fun onDisable() {
    commandManager.unregisterCommands()
  }
}