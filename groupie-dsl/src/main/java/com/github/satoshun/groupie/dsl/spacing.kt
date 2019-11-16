package com.github.satoshun.groupie.dsl

import android.view.View
import androidx.core.view.updatePadding

fun BuilderGroupAdapter.padding(
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

fun BuilderGroupAdapter.padding(
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

internal class SingleGroupieItemBuilder(
  private val delegate: BuilderGroupAdapter,
  private val beforeBlock: View.(Int) -> Unit
) : GroupieItemBuilder {
  override fun item(layoutRes: Int, data: Any?, block: View.(Int) -> Unit) {
    delegate.item(layoutRes, data) {
      beforeBlock(it)
      block(it)
    }
  }
}
