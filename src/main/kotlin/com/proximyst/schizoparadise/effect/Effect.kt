package com.proximyst.schizoparadise.effect

import com.proximyst.schizoparadise.data.SchizophrenicPlayer

abstract class Effect {
  abstract val name : String

  abstract fun apply(player : SchizophrenicPlayer)
}