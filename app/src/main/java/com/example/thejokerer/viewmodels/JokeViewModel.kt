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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException


/**
 * ViewModel responsible for handling joke-related operation in the application.
 *
 * The `JokeViewModel` class extends [ViewModel] and provides methods for interacting with the
 * JokeRepository to fetch jokes from the API, manage favorite jokes, and expose states for UI observation.
 *
 * @param jokeRepository The repository providing data access and management for jokes.
 */
class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    /**
     * Represents the loading state of jokes fetched from the API.
     */
    var apiState: JokeApiState by mutableStateOf(JokeApiState.Loading)
        private set

    /**
     * Represents the loading state of favorite jokes.
     */
    var favoriteState: FavoriteState by mutableStateOf(FavoriteState.Loading)
        private set

    /**
     * Represents the current favorite state of a joke (whether it is favorite or not).
     */
    var isFavoriteState: Boolean by mutableStateOf(false)
        private set

    /**
     * StateFlow representing the list of jokes for UI updates.
     */
    lateinit var uiListState: StateFlow<List<Joke>>

    /**
     * Initializes the ViewModel by fetching both API jokes and favorite jokes.
     */
    init {
        getApiJoke()
        getFavoriteJokes()
    }

    /**
     * Fetches favorite jokes from the repository and updates [uiListState].
     */
    fun getFavoriteJokes() {
        viewModelScope.launch {
            favoriteState = try {
                uiListState = jokeRepository.getFavoriteJokes()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf(),
                    )
                FavoriteState.Success
            } catch (e: IOException) {
                Log.e("favorite jokes", e.message.toString())
                FavoriteState.Error
            }
        }
    }

    /**
     * Fetches a random joke from the API and updates [apiState].
     */
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


    /**
     * Fetches an IT related joke from the API and updates [apiState].
     */
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

    /**
     * Fetches a dark humor joke from the API and updates [apiState].
     */
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

    /**
     * Fetches a pun from the API and updates [apiState].
     */
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

    /**
     * Fetches a miscellaneous joke from the API and updates [apiState].
     */
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


    /**
     * Adds a joke to the favorites list.
     *
     * @param joke The joke to be added to favorites.
     */
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

    /**
     * Removes a joke from the favorites list.
     *
     * @param joke The joke to be removed from favorites.
     */

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


    /**
     * Companion object holding a ViewModelProvider.Factory for creating instances of [JokeViewModel].
     */
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
