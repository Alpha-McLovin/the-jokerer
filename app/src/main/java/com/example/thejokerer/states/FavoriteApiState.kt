package com.example.thejokerer.states

sealed interface FavoriteState {
    object Error : FavoriteState
    object Loading : FavoriteState
    object Success : FavoriteState
}