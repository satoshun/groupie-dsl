package com.github.satoshun.groupie.dsl

import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

fun GroupieItemBuilder.margin(
  left: GroupieDp = 0.dp,
  top: GroupieDp = 0.dp,
  right: GroupieDp = 0.dp,
  bottom: GroupieDp = 0.dp,
  child: GroupieItemBuilder.() -> Unit
) {
  SingleGroupieItemBuilder(this) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
      this.leftMargin = left.px(context)
      this.topMargin = top.px(context)
      this.rightMargin = right.px(context)
      this.bottomMargin = bottom.px(context)
    }
  }
    .child()
}

fun GroupieItemBuilder.margin(
  margin: GroupieDp,
  child: GroupieItemBuilder.() -> Unit
) {
  margin(
    left = margin,
    top = margin,
    right = margin,
    bottom = margin,
    child = child
  )
}

fun GroupieItemBuilder.padding(
  left: GroupieDp = 0.dp,
  top: GroupieDp = 0.dp,
  right: GroupieDp = 0.dp,
  bottom: GroupieDp = 0.dp,
  child: GroupieItemBuilder.() -> Unit
) {
  SingleGroupieItemBuilder(this) {
    updatePadding(
      left = left.px(context),
      top = top.px(context),
      right = right.px(context),
      bottom = bottom.px(context)
    )
  }
    .child()
}

fun GroupieItemBuilder.padding(
  padding: GroupieDp,
  child: GroupieItemBuilder.() -> Unit
) {
  padding(
    left = padding,
    top = padding,
    right = padding,
    bottom = padding,
    child = child
  )
}
