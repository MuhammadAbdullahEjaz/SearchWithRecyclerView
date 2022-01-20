package com.example.recyclerviewsearch.adapters

import android.util.Log
import com.example.recyclerviewsearch.model.data.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsearch.R
import com.example.recyclerviewsearch.databinding.ColorRvItemBinding

class ColorsRvAdapter(private val data:List<Color>):RecyclerView.Adapter<ColorsRvAdapter.ColorViewHolder>() {

    private var count:Int = 0

    inner class ColorViewHolder(val binding:ColorRvItemBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(color:Color){
            binding.colorName = color.name
            binding.rgbTV.text = binding.root.resources.getString(R.string.rgbTV, color.R, color.G, color.B)
            binding.colorIV.setCardBackgroundColor(android.graphics.Color.rgb(color.R,color.G, color.B))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ColorRvItemBinding.inflate(layoutInflater, parent, false)
        Log.d("fetch","onCreateViewHolder called times $count")
        count++
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = data[position]
        holder.onBind(color)
        Log.d("fetch","onBindViewHolder called")
    }

    override fun getItemCount(): Int {
        return data.size
    }
}