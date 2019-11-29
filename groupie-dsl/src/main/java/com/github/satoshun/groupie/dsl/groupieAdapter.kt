package com.github.satoshun.groupie.dsl

import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

fun groupAdapter(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply {
    block()
    addAll()
  }

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>(),
  GroupieItemBuilder,
  GroupieDSLTag {

  private var items: MutableList<Group> = mutableListOf()

  override fun item(
    @LayoutRes layoutRes: Int,
    block: View.() -> Unit
  ) {
    items.add(
      BuilderItem(
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

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(ExpandableGroup) -> Unit = { expandable ->
      setOnClickListener {
        expandable.onToggleExpanded()
      }
    },
    expandedBlock: GroupieItemBuilder.() -> Unit
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
}

internal data class BuilderItem(
  @LayoutRes private val layoutRes: Int,
  private val block: View.() -> Unit
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block()
  }
}

internal data class StateBuilderItem<T>(
  @LayoutRes private val layoutRes: Int,
  var state: T?,
  private val block: View.(T?) -> Unit
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(state)
  }
}
