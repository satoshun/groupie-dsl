package com.github.satoshun.groupie.dsl

import android.view.View
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

internal typealias ItemViewInterceptor = View.() -> Unit

internal class GroupieItemInterceptor(
  private val parent: GroupieItemBuilder,
  private val itemViewInterceptor: ItemViewInterceptor
) : GroupieItemBuilder {
  override fun item(layoutRes: Int, block: View.() -> Unit) {
    parent.item(layoutRes) {
      itemViewInterceptor()
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
        this@GroupieItemInterceptor.itemViewInterceptor(this)
        block(it)
      }
    }
  }
}
