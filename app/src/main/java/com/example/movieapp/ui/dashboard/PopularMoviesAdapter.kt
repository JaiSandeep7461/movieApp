package com.example.movieapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.models.popular.Result
import com.example.movieapp.databinding.ItemTodoBinding

class PopularMoviesAdapter : PagingDataAdapter<Result, PopularMoviesAdapter.MovieViewHolder>(DiffCallback()) {

    var onItemClick: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, onItemClick)
        }
    }

    class MovieViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result, onItemClick: ((Result) -> Unit)?) {
            binding.tvTitle.text = item.title
            Glide.with(binding.ivMovieImage.context)
                .load("https://image.tmdb.org/t/p/w500${item.backdropPath}")
                .into(binding.ivMovieImage)
            binding.llMain.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}