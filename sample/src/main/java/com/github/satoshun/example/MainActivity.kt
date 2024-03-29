package com.github.satoshun.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItem11Binding
import com.github.satoshun.example.databinding.MainItem1Binding
import com.github.satoshun.example.databinding.MainItem2Binding
import com.github.satoshun.groupie.dsl.dp
import com.github.satoshun.groupie.dsl.groupAdapter
import com.github.satoshun.groupie.dsl.heightSpacer
import com.github.satoshun.groupie.dsl.margin
import com.github.satoshun.groupie.dsl.opacity
import com.github.satoshun.groupie.dsl.padding
import com.github.satoshun.groupie.dsl.text
import com.github.satoshun.groupie.dsl.widthSpacer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val dataSource = MutableLiveData(0)
    binding.vertical.layoutManager = LinearLayoutManager(this)
    binding.vertical.adapter = groupAdapter {
      expandable(R.layout.sample_expandable_item) {
        item(R.layout.sample_expanded_item) {
          (this as TextView).text = "expanded"
        }
      }

      repeat(5) {
        item(R.layout.main_item1) {
          val binding = MainItem1Binding.bind(this)
          binding.title.text = "$it"
        }
      }

      heightSpacer(16.dp)

      padding(16.dp) {
        item(R.layout.main_item2) {
          val binding = MainItem2Binding.bind(this)
          binding.title.text = "main_item2"
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

      opacity(0.3f) {
        margin(16.dp) {
          lifecycleScope.item(dataSource, R.layout.main_item1) {
            val binding = MainItem1Binding.bind(this)
            binding.title.text = it.toString()
          }
        }
      }
    }

    binding.horizontal.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    binding.horizontal.adapter = groupAdapter {
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

    lifecycleScope.launch {
      while (true) {
        delay(3000)
        dataSource.value = (dataSource.value ?: 0) + 1
      }
    }
  }
}
