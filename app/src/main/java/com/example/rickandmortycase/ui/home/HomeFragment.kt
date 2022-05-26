package com.example.rickandmortycase.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.coroutines.flow.collect
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmortycase.R
import com.example.rickandmortycase.databinding.FragmentHomeBinding
import com.example.rickandmortycase.state.RequestState
import com.example.rickandmortycase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {



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
                            Log.e("laoding","loading ...")
                        }
                        is RequestState.Failure -> {
                            Log.e("fail","fail ...")

                        }
                        is RequestState.Success -> {
                            Log.e("succes",requestState.data.toString())
                        }
                    }
                }
            }
        }
    }

}