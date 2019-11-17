package com.github.satoshun.groupie.dsl

import androidx.annotation.FloatRange

fun GroupieItemBuilder.opacity(
  @FloatRange(from = 0.0, to = 1.0) opacity: Float,
  child: GroupieItemBuilder.() -> Unit
) {
  SingleGroupieItemBuilder(this) {
    this.alpha = opacity
  }.child()
}
