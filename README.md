# Groupie construction DSL

## motivation

Reduction of code for simple GroupieAdapter and items.

### vertical

```kotlin
binding.vertical.layoutManager = LinearLayoutManager(this)
binding.vertical.adapter = groupieAdapter {
  item(R.layout.main_item1) {
    val binding = MainItem1Binding.bind(this)
    binding.title.text = "Main1"
  }

  heightSpacer(16.dp)

  padding(16.dp) {
    item(R.layout.main_item2) {
      val binding = MainItem2Binding.bind(this)
      binding.title.text = "Main2"
    }
  }

  heightSpacer(16.dp)

  margin(left = 24.dp, right = 24.dp) {
    text(
      text = "HeadLine5",
      textAppearance = R.style.TextAppearance_MaterialComponents_Headline5
    )
    text(
      text = "HeadLine6",
      textAppearance = R.style.TextAppearance_MaterialComponents_Headline6,
      textAlignment = View.TEXT_ALIGNMENT_VIEW_END
    )
  }
}
```

### horizontal

```kotlin
binding.horizontal.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
binding.horizontal.adapter = groupieAdapter {
  item(R.layout.main_item11) {
    val binding = MainItem11Binding.bind(this)
    binding.title.text = "Main11"
  }

  widthSpacer(32.dp)

  margin(24.dp) {
    padding(16.dp) {
      item(R.layout.main_item11) {
        val binding = MainItem11Binding.bind(this)
        binding.title.text = "Main111"
      }
    }
  }
}
```

## others

TODO
