package com.example.thejokerer.network

import retrofit2.http.GET

interface JokeApiService {

    // suspend is added to force the user to call this in a coroutine scope
    @GET("any?type=single")
    suspend fun getJoke(): ApiJoke

    @GET("Pun?type=single")
    suspend fun getPun(): ApiJoke

    @GET("Programming?type=single")
    suspend fun getItJoke(): ApiJoke

    @GET("Miscellaneous?type=single")
    suspend fun getMiscellaneousJoke(): ApiJoke

    @GET("Dark?type=single")
    suspend fun getDarkJoke(): ApiJoke
}
