package com.example.thejokerer.navigation

import androidx.navigation.NavBackStackEntry



public fun canNavigate(current: NavBackStackEntry?, destination: String): Boolean {
        val currentDest = current?.destination?.route
        return if (current != null) currentDest != destination else true
    }
