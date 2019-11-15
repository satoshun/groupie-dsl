# Groupie construction DSL

## motivation

Reduction of code for simple GroupieAdapter and items.

```kotlin
binding.recycler.adapter = groupieAdapter {
  item(R.layout.main_item1) {
    val binding = MainItem1Binding.bind(this)
    binding.title.text = "Main1"
  }

  item(R.layout.main_item2) {
    val binding = MainItem2Binding.bind(this)
    binding.title.text = "Main2"
  }
}
```

## others

TODO
