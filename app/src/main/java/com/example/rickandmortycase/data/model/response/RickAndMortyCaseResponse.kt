package com.example.rickandmortycase.data.model.response


import com.google.gson.annotations.SerializedName

data class RickAndMortyCaseResponse(
    val info: Info?,
    val results: List<Result>?
)