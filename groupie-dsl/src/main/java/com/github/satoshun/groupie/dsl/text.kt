package com.github.satoshun.groupie.dsl

import android.widget.TextView

fun BuilderGroupAdapter.text(
  text: String
) {
  item(R.layout.groupie_dsl_text) {
    val textView = this as TextView
    textView.text = text
  }
}
