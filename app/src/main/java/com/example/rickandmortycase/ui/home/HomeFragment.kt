package com.example.rickandmortycase.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.coroutines.flow.collect
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmortycase.R
import com.example.rickandmortycase.state.RequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

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