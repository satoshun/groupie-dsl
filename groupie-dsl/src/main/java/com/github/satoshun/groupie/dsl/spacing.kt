package com.github.satoshun.groupie.dsl

import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

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

internal class SingleGroupieItemBuilder(
  private val parent: GroupieItemBuilder,
  private val beforeBlock: View.() -> Unit
) : GroupieItemBuilder {
  override fun item(layoutRes: Int, block: View.() -> Unit) {
    parent.item(layoutRes) {
      beforeBlock()
      block()
    }
  }

  override fun <T> CoroutineScope.item(
    source: LiveData<T>,
    layoutRes: Int,
    block: View.(T?) -> Unit
  ) {
    with(parent) {
      item(source, layoutRes) {
        this@SingleGroupieItemBuilder.beforeBlock(this)
        block(it)
      }
    }
  }
}
