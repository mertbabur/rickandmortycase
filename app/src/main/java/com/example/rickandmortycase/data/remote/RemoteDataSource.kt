package com.example.rickandmortycase.data.remote

import com.example.rickandmortycase.data.model.response.RickAndMortyCaseResponse

interface RemoteDataSource {

    suspend fun getAllCharacters(): RickAndMortyCaseResponse

}