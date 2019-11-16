package com.github.satoshun.groupie.dsl

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.view.updateLayoutParams

fun GroupieItemBuilder.text(
  text: String,
  width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
  height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
  @StyleRes textAppearance: Int = 0,
  overflow: TextUtils.TruncateAt? = null,
  textAlignment: Int = View.TEXT_ALIGNMENT_INHERIT,
  maxLines: Int = -1,
  block: TextView.(Int) -> Unit = {}
) {
  item(R.layout.groupie_dsl_text) {
    updateLayoutParams<ViewGroup.LayoutParams> {
      this.width = width
      this.height = height
    }

    val textView = this as TextView
    textView.text = text

    textView.setTextAppearance(context, textAppearance)

    if (maxLines == -1) {
      textView.maxHeight = Int.MAX_VALUE
    } else {
      textView.maxLines = maxLines
    }

    textView.ellipsize = overflow

    textView.textAlignment = textAlignment

    textView.block(it)
  }
}
