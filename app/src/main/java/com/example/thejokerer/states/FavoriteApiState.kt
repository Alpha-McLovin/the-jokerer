package com.example.thejokerer.states

sealed interface FavoriteApiState {
    object Error : FavoriteApiState
    object Loading : FavoriteApiState
    object Success : FavoriteApiState
}