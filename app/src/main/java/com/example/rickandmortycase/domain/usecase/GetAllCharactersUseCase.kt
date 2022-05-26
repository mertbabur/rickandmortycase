package com.example.rickandmortycase.domain.usecase

import com.example.rickandmortycase.data.repository.RickAndMortyCaseRepository
import com.example.rickandmortycase.state.RequestState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val rickAndMortyCaseRepository: RickAndMortyCaseRepository) {

    suspend operator fun invoke() = flow {
        emit(RequestState.Loading())
        try {
            emit(RequestState.Success(rickAndMortyCaseRepository.getAllCharacters()))
        } catch (e: Exception) {
            emit(RequestState.Failure(e))
        }
    }

}