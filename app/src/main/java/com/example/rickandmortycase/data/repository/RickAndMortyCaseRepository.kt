package com.example.rickandmortycase.data.repository

import com.example.rickandmortycase.data.model.response.RickAndMortyCaseResponse
import com.example.rickandmortycase.data.remote.RemoteDataSourceProvider
import javax.inject.Inject

class RickAndMortyCaseRepository @Inject constructor(private val remoteDataSourceProvider: RemoteDataSourceProvider) {

    suspend fun getAllCharacters(): RickAndMortyCaseResponse =
        remoteDataSourceProvider.getAllCharacters()

}