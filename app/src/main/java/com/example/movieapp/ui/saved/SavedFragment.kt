package com.example.movieapp.ui.saved

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSavedBinding
import com.example.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SavedFragment : Fragment() {

    private var  _binding:FragmentSavedBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<SavedViewModel>()

    private lateinit var savedMoviesAdapter: SavedMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater,container,false)

        savedMoviesAdapter = SavedMoviesAdapter()
        savedMoviesAdapter.onItemClick = {item->
            Log.e("19072024","Item>>> ${item.title}")
            viewModel.deleteItem(item)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSavedDatabaseResults()
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.savedDbResponse.collect{
                when(it){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        savedMoviesAdapter.submitList(it.dataFetched)
                    }
                }
            }
        }
    }

    private fun setUpViews() {
        binding.rcvSaved.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = savedMoviesAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}