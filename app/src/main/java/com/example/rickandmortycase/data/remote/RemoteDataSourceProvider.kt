package com.example.rickandmortycase.data.remote

import com.example.rickandmortycase.data.model.response.RickAndMortyCaseResponse
import com.example.rickandmortycase.data.network.RickAndMortyCaseService
import javax.inject.Inject

class RemoteDataSourceProvider @Inject constructor(private val rickAndMortyCaseService: RickAndMortyCaseService) :
    RemoteDataSource {

    override suspend fun getAllCharacters(): RickAndMortyCaseResponse =
        rickAndMortyCaseService.getAllCharacters()

}