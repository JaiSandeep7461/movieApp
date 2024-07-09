package com.example.movieapp.ui.nowplaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.models.Result
import com.example.movieapp.databinding.ItemNowPlayingBinding

class NowPlayingAdapter:PagingDataAdapter<Result,NowPlayingAdapter.NowPlayingViewHolder>(DiffCallback()) {


    class NowPlayingViewHolder(private val binding: ItemNowPlayingBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Result){
            binding.tvTitle.text = item.originalLanguage
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        val nowPlaying = getItem(position)
        if(nowPlaying!=null){
            holder.bind(nowPlaying)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val binding = ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowPlayingViewHolder(binding)
    }

}