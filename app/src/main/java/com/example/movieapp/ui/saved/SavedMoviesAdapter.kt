package com.example.movieapp.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.models.Item
import com.example.movieapp.databinding.ItemMovieSaveBinding

class SavedMoviesAdapter : ListAdapter<Item, SavedMoviesAdapter.SavedMoviesViewHolder>(DiffCallback()) {


    var onItemClick: ((Item) -> Unit)? =null

    class SavedMoviesViewHolder(private val binding:ItemMovieSaveBinding):
            RecyclerView.ViewHolder(binding.root){

                fun bind(item: Item, onItemClick: ((Item) -> Unit)?){
                    binding.tvTitle.text = item.title
                    Glide.with(binding.ivMovieImage.context)
                        .load("https://image.tmdb.org/t/p/w500${item.backdropPath}")
                        .into(binding.ivMovieImage)
                    binding.llMain.setOnClickListener {
                        onItemClick?.invoke(item)
                    }
                }

            }

    class DiffCallback : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }



        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMoviesViewHolder {
        val binding = ItemMovieSaveBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SavedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie!=null){
            holder.bind(movie,onItemClick)
        }
    }
}