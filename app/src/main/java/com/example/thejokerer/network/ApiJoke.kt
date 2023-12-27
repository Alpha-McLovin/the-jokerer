package com.example.thejokerer.network

import com.example.thejokerer.data.database.dbJoke
import com.example.thejokerer.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiJoke (
    val joke: String,
)

// extension function for an ApiTask List to convert is to a Domain Task List
//fun Flow<List<ApiJoke>>.asDomainObjects(): Flow<List<Joke>> {
//    return map {
//        it.asDomainObjects()
//    }
//}

fun ApiJoke.asDomainObject(): Joke = Joke(joke = joke)






