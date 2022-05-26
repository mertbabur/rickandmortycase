package com.example.rickandmortycase.data.network

import com.example.rickandmortycase.data.model.response.RickAndMortyCaseResponse
import retrofit2.http.GET

interface RickAndMortyCaseService {

    @GET("/api/character")
    suspend fun getAllCharacters(): RickAndMortyCaseResponse

}