package com.github.satoshun.groupie.dsl

import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

@DslMarker
annotation class GroupieDSL

@GroupieDSL
interface GroupieDSLTag

interface GroupieItemBuilder : GroupieDSLTag {
  fun item(
    @LayoutRes layoutRes: Int,
    block: View.() -> Unit
  )

  fun <T> CoroutineScope.item(
    source: LiveData<T>,
    @LayoutRes layoutRes: Int,
    block: View.(T?) -> Unit
  )
}

fun groupieAdapter(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply {
    block()
    addAll()
  }

private val ID_COUNTER = AtomicLong(0)

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>(),
  GroupieItemBuilder,
  GroupieDSLTag {

  private var items: MutableList<Group> = mutableListOf()

  fun update(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
    BuilderGroupAdapter().apply {
      block()
      updateAll()
    }

  override fun item(
    @LayoutRes layoutRes: Int,
    block: View.() -> Unit
  ) {
    items.add(
      BuilderItem(
        ID_COUNTER.decrementAndGet(),
        layoutRes,
        block
      )
    )
  }

  override fun <T> CoroutineScope.item(
    source: LiveData<T>,
    @LayoutRes layoutRes: Int,
    block: View.(T?) -> Unit
  ) {
    val item = StateBuilderItem<T>(
      ID_COUNTER.decrementAndGet(),
      layoutRes,
      null,
      block
    )
    items.add(item)

    launch {
      source.asFlow().collect {
        item.state = it
        item.notifyChanged(it)
      }
    }
  }

  @GroupieDSL
  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(ExpandableGroup) -> Unit,
    expandedBlock: BuilderExpandableGroup.() -> Unit
  ) {
    items.add(
      BuilderExpandableGroup(
        ExpandableBuilderItem(
          layoutRes,
          block
        )
      ).apply(expandedBlock)
    )
  }

  internal fun addAll() {
    addAll(items)
    items.clear()
  }

  private fun updateAll() {
    update(items)
    items.clear()
  }
}

@GroupieDSL
class BuilderExpandableGroup(group: Group) : ExpandableGroup(group) {
  fun item(
    @LayoutRes layoutRes: Int,
    block: View.() -> Unit
  ) {
    add(
      BuilderItem(
        ID_COUNTER.decrementAndGet(),
        layoutRes,
        block
      )
    )
  }

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(ExpandableGroup) -> Unit,
    expandableBlock: BuilderExpandableGroup.() -> Unit
  ) {
    add(
      BuilderExpandableGroup(
        ExpandableBuilderItem(
          layoutRes,
          block
        )
      ).apply(expandableBlock)
    )
  }
}

internal data class BuilderItem(
  private val _id: Long,
  @LayoutRes private val layoutRes: Int,
  private val block: View.() -> Unit
) : Item<GroupieViewHolder>(_id) {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block()
  }
}

internal data class StateBuilderItem<T>(
  private val _id: Long,
  @LayoutRes private val layoutRes: Int,
  var state: T?,
  private val block: View.(T?) -> Unit
) : Item<GroupieViewHolder>(_id) {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(state)
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
