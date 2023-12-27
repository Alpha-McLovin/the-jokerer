package com.example.thejokerer.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface JokeApiService {
    // suspend is added to force the user to call this in a coroutine scope
    @GET("jokes")
    suspend fun getJokes(): List<ApiJoke>
}

// helper function
fun JokeApiService.getJokesAsFlow(): Flow<List<ApiJoke>> = flow {
    try {
        emit(getJokes())
    }
    catch(e: Exception){
        Log.e("API", "getJokesAsFlow: "+e.stackTraceToString(), )
    }
}