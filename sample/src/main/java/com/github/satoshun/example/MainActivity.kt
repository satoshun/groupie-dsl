package com.github.satoshun.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItem11Binding
import com.github.satoshun.example.databinding.MainItem1Binding
import com.github.satoshun.example.databinding.MainItem2Binding
import com.github.satoshun.groupie.dsl.dp
import com.github.satoshun.groupie.dsl.groupieAdapter
import com.github.satoshun.groupie.dsl.heightSpacer
import com.github.satoshun.groupie.dsl.margin
import com.github.satoshun.groupie.dsl.padding
import com.github.satoshun.groupie.dsl.text
import com.github.satoshun.groupie.dsl.widthSpacer

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

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
  }
}
