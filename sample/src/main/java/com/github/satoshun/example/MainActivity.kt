package com.github.satoshun.example

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItem1Binding
import com.github.satoshun.example.databinding.MainItem2Binding
import com.github.satoshun.groupie.dsl.dp
import com.github.satoshun.groupie.dsl.groupieAdapter

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)

    binding.recycler.adapter = groupieAdapter {
      item(R.layout.main_item1) {
        val binding = MainItem1Binding.bind(this)
        binding.title.text = "Main1"
      }

      heightSpacer(16.dp)

      item(R.layout.main_item2) {
        val binding = MainItem2Binding.bind(this)
        binding.title.text = "Main2"
      }
    }
  }
}
