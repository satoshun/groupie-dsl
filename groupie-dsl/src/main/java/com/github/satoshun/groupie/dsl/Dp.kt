package com.github.satoshun.groupie.dsl

import android.content.Context

data class GroupieDp(val value: Float)

internal fun GroupieDp.px(context: Context): Int =
  (value * context.resources.displayMetrics.density).toInt()

val Int.dp: GroupieDp get() = GroupieDp(this.toFloat())
val Float.dp: GroupieDp get() = GroupieDp(this)
val Double.dp: GroupieDp get() = GroupieDp(this.toFloat())
