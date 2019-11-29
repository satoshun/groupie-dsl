package com.github.satoshun.groupie.dsl

import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BuilderExpandableGroup(group: Group) : ExpandableGroup(group),
  GroupieItemBuilder,
  GroupieDSLTag {
  override fun item(
    @LayoutRes layoutRes: Int,
    block: View.() -> Unit
  ) {
    add(BuilderItem(layoutRes, block))
  }

  override fun <T> CoroutineScope.item(
    source: LiveData<T>,
    layoutRes: Int,
    block: View.(T?) -> Unit
  ) {
    val item = StateBuilderItem<T>(
      layoutRes,
      null,
      block
    )
    add(item)

    launch {
      source.asFlow().collect {
        item.state = it
        item.notifyChanged(it)
      }
    }
  }
}

class ExpandableBuilderItem(
  @LayoutRes private val layoutRes: Int,
  private val block: View.(ExpandableGroup) -> Unit
) : Item<GroupieViewHolder>(), ExpandableItem {

  private var onToggleListener: ExpandableGroup? = null

  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(onToggleListener!!)
  }

  override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
    this.onToggleListener = onToggleListener
  }
}
