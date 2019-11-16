package com.github.satoshun.groupie.dsl

import android.view.View
import androidx.annotation.LayoutRes
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import java.util.concurrent.atomic.AtomicLong

@DslMarker
annotation class GroupieDSL

@GroupieDSL
interface GroupieDSLTag

interface GroupieItemBuilder : GroupieDSLTag {
  fun item(
    @LayoutRes layoutRes: Int,
    data: Any? = null,
    block: View.(Int) -> Unit
  )
}

@GroupieDSL
fun groupieAdapter(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply {
    block()
    addAll()
  }

private val ID_COUNTER = AtomicLong(0)

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>(), GroupieDSLTag {
  private var items: MutableList<Group> = mutableListOf()

  fun update(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
    BuilderGroupAdapter().apply {
      block()
      updateAll()
    }

  fun item(
    @LayoutRes layoutRes: Int,
    data: Any? = null,
    block: View.(Int) -> Unit
  ) {
    items.add(
      BuilderItem(
        data?.hashCode()?.toLong() ?: ID_COUNTER.decrementAndGet(),
        layoutRes,
        data,
        block
      )
    )
  }

  @GroupieDSL
  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(Int, ExpandableGroup) -> Unit,
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
    data: Any? = null,
    block: View.(Int) -> Unit
  ) {
    add(
      BuilderItem(
        data?.hashCode()?.toLong() ?: ID_COUNTER.decrementAndGet(),
        layoutRes,
        data,
        block
      )
    )
  }

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(Int, ExpandableGroup) -> Unit,
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

data class BuilderItem(
  private val _id: Long,
  @LayoutRes private val layoutRes: Int,
  private val any: Any?,
  private val block: View.(Int) -> Unit
) : Item<GroupieViewHolder>(_id) {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(position)
  }
}

class ExpandableBuilderItem(
  @LayoutRes private val layoutRes: Int,
  private val block: View.(Int, ExpandableGroup) -> Unit
) : Item<GroupieViewHolder>(), ExpandableItem {

  private var onToggleListener: ExpandableGroup? = null

  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(position, onToggleListener!!)
  }

  override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
    this.onToggleListener = onToggleListener
  }
}
