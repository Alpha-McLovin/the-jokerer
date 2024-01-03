package com.example.thejokerer.fake

import com.example.thejokerer.network.ApiJoke
import com.example.thejokerer.network.JokeApiService

class FakeJokeApiService : JokeApiService {
    override suspend fun getJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke1.joke)
    }

    override suspend fun getPun(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }

    override suspend fun getItJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }

    override suspend fun getMiscellaneousJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }

    override suspend fun getDarkJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }
}
