# Groupie construction DSL

## motivation

Reduction of code for simple GroupieAdapter and items.

### usage

These sample code use ViewBinding

### vertical orientation

```kotlin
binding.vertical.layoutManager = LinearLayoutManager(this)
binding.vertical.adapter = groupAdapter {
  repeat(5) {
    item(R.layout.main_item) {
      val binding = MainItemBinding.bind(this)
      binding.title.text = "$it"
    }
  }

  heightSpacer(16.dp)

  padding(16.dp) {
    item(R.layout.main_item) {
      ...
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

  val dataSource = LiveData(...)
  opacity(0.3f) {
    margin(16.dp) {
      // emit this bind method when changed dataSource
      lifecycleScope.item(dataSource, R.layout.main_item) {
        ...
      }
    }
  }
}
```

### horizontal orientation

```kotlin
binding.horizontal.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
binding.horizontal.adapter = groupAdapter {
  item(R.layout.main_item) {
     ...
  }

  widthSpacer(32.dp)

  margin(24.dp) {
    padding(16.dp) {
      item(R.layout.main_item) {
        ...
      }
    }
  }
}
```

### expandable

```kotlin
binding.recyclerView.adapter = groupAdapter {
  // header item
  expandable(R.layout.sample_expandable_item) {
    // expanded items
    item(R.layout.sample_expanded_item) {
      ...
    }
    ...
  }
}
```

## install

```groovy
implementation "com.github.satoshun.groupie.dsl:groupie-dsl:0.0.1"
```

## others

TODO
