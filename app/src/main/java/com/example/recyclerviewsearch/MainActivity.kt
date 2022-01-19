package com.example.recyclerviewsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import com.example.recyclerviewsearch.adapters.ColorsRvAdapter
import com.example.recyclerviewsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((this.application as MainApplication).repository)
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        viewModel.getColors()
        viewModel.colorsList.observe(this){ colors ->
            Log.d("fetch", "color is $colors")
            binding.colorsRV.adapter = ColorsRvAdapter(colors)
        }

        binding.searchSV.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrBlank()){
                    viewModel.searchColors(newText)
                }
                else{
                    viewModel.showAllColors()
                    return false
                }
                return  true
            }
        })
    }
}