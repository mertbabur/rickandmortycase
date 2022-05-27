package com.example.rickandmortycase.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmortycase.data.model.response.Result
import com.example.rickandmortycase.databinding.FragmentHomeBinding
import com.example.rickandmortycase.state.RequestState
import com.example.rickandmortycase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    private lateinit var homeCharactersAdapter: HomeCharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectGetAllCharacters()

    }

    private fun collectGetAllCharacters() {
        viewModel.getAllCharacters()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllCharactersState.collect { requestState ->
                    when (requestState) {
                        is RequestState.Loading -> {
                            binding.pbHomeCharacter.isVisible = true
                        }
                        is RequestState.Failure -> {
                            binding.pbHomeCharacter.isVisible = false

                        }
                        is RequestState.Success -> {
                            binding.pbHomeCharacter.isVisible = false
                            requestState.data.results?.let { initRecyclerView(it) }
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView(results: List<Result>) {
        val characterAdapter = HomeCharactersAdapter(results, object : OnItemClickListener {
            override fun onItemClickListener(index: Int) {
                Log.e("asd", index.toString())
            }
        })
        homeCharactersAdapter = characterAdapter
        binding.rvHome.adapter = homeCharactersAdapter
    }
}