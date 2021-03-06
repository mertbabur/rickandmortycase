package com.example.rickandmortycase.state

sealed class RequestState<T>{
    class Loading<T>: RequestState<T>()
    data class Failure<T>(val exception: Throwable): RequestState<T>()
    data class Success<T>(val data: T): RequestState<T>()
}