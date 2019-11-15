package com.github.satoshun.groupie.dsl

import android.view.ViewGroup
import androidx.core.view.updateLayoutParams

fun BuilderGroupAdapter.fixedSpacer(width: GroupieDp, height: GroupieDp) {
  item(R.layout.groupie_dsl_spacer) {
    updateLayoutParams<ViewGroup.LayoutParams> {
      this.width = width.px(context)
      this.height = height.px(context)
    }
  }
}

fun BuilderGroupAdapter.widthSpacer(width: GroupieDp) {
  fixedSpacer(width = width, height = 0.dp)
}

fun BuilderGroupAdapter.heightSpacer(height: GroupieDp) {
  fixedSpacer(width = 0.dp, height = height)
}
