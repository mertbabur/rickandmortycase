package com.example.rickandmortycase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycase.data.model.response.RickAndMortyCaseResponse
import com.example.rickandmortycase.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortycase.state.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {

    private var _getAllCharactersState =
        MutableStateFlow<RequestState<RickAndMortyCaseResponse>?>(null)
    val getAllCharactersState: StateFlow<RequestState<RickAndMortyCaseResponse>?> =
        _getAllCharactersState

    fun getAllCharacters() {
        viewModelScope.launch {
            getAllCharactersUseCase.invoke().collect { rickAndMortyCaseResponse ->
                _getAllCharactersState.value = rickAndMortyCaseResponse
            }
        }
    }

}