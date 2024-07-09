package com.example.movieapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.nowplaying.NowPlayingAdapter
import com.example.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesAdapter = MoviesAdapter()
        nowPlayingAdapter = NowPlayingAdapter()
        setRecyclerView()
        setUpObserver()
    }

    private fun setUpObserver() {
       /* lifecycleScope.launch {
            viewModel.movieResponse.collect {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        Log.d("TAG", "setUpObserver: ")
                    }
                    is Resource.Success -> {
                        moviesAdapter.submitData(it.dataFetched)

                    }
                }
            }
        }*/

        lifecycleScope.launch {
            viewModel.nowPlayingResponse.collect{
                when(it){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        nowPlayingAdapter.submitData(it.dataFetched)
                    }
                }
            }
        }
    }

    private fun setRecyclerView() = binding.rvTodos.apply {
        binding.rvTodos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTodos.adapter = nowPlayingAdapter
    }
}