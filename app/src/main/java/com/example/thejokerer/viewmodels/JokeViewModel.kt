package com.example.thejokerer.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.thejokerer.JokeApplication
import com.example.thejokerer.data.JokeRepository
import com.example.thejokerer.model.Joke
import com.example.thejokerer.states.FavoriteState
import com.example.thejokerer.states.JokeApiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException

class JokeViewModel ( private val jokeRepository: JokeRepository) : ViewModel() {
    var apiState: JokeApiState by mutableStateOf(JokeApiState.Loading)
        private set

    var favoriteState: FavoriteState by mutableStateOf(FavoriteState.Loading)
        private set

    var isFavoriteState: Boolean by mutableStateOf(false)
        private set

    lateinit var uiListState: StateFlow<List<Joke>>

    init {
        getApiJoke()
        getFavoriteJokes()
    }


    fun getFavoriteJokes(){
        viewModelScope.launch {

            favoriteState = try {
                uiListState = jokeRepository.getFavoriteJokes()
                    .stateIn(
                    scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = listOf(),
                )
                FavoriteState.Success
            } catch (e: IOException){
                Log.e("favorite jokes" , e.message.toString())
                FavoriteState.Error
            }
        }
    }


    fun getApiJoke() {
        viewModelScope.launch {
            apiState = try {
                isFavoriteState = false
                JokeApiState.Success(jokeRepository.getNewJoke())
            } catch (ex: IOException) {
                JokeApiState.NoInternet
            } catch (e: Exception) {
                Log.e("JokeViewModel", e.message.toString())
                JokeApiState.Error
            }
        }
    }

    fun getApiItJoke() {
        viewModelScope.launch {
            apiState = try {
                isFavoriteState = false
                JokeApiState.Success(jokeRepository.getNewItJoke())
            } catch (ex: IOException) {
                JokeApiState.NoInternet
            } catch (e: Exception) {
                Log.e("JokeViewModel", e.message.toString())
                JokeApiState.Error
            }
        }
    }

    fun getApiDarkJoke() {
        viewModelScope.launch {
            apiState = try {
                isFavoriteState = false
                JokeApiState.Success(jokeRepository.getNewDarkJoke())
            } catch (ex: IOException) {
                JokeApiState.NoInternet
            } catch (e: Exception) {
                Log.e("JokeViewModel", e.message.toString())
                JokeApiState.Error
            }
        }
    }

    fun getApiPun() {
        viewModelScope.launch {
            apiState = try {
                isFavoriteState = false
                JokeApiState.Success(jokeRepository.getNewPun())
            } catch (ex: IOException) {
                JokeApiState.NoInternet
            } catch (e: Exception) {
                Log.e("JokeViewModel", e.message.toString())
                JokeApiState.Error
            }
        }
    }

    fun getApiMiscelleaneous() {
        viewModelScope.launch {
            apiState = try {
                isFavoriteState = false
                JokeApiState.Success(jokeRepository.getNewMiscellaneous())
            } catch (ex: IOException) {
                JokeApiState.NoInternet
            } catch (e: Exception) {
                Log.e("JokeViewModel", e.message.toString())
                JokeApiState.Error
            }
        }
    }



    fun addFavorite(joke: Joke) {
        viewModelScope.launch {
            try {
                isFavoriteState = true
                jokeRepository.insertFavoriteJoke(joke)
            } catch (ex: Exception) {
                Log.e("JokeViewModel", ex.message.toString())
            }
        }
    }

    fun removeFavorite(joke: Joke) {
        viewModelScope.launch {
            try {
                isFavoriteState = false
                jokeRepository.deleteFavoriteJoke(joke)
            } catch (ex: Exception) {
                Log.e("JokeViewModel", ex.message.toString())
            }
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as JokeApplication)
                val jokeRepository = application.container.jokeRepository
                JokeViewModel(jokeRepository)
            }
        }
    }
}