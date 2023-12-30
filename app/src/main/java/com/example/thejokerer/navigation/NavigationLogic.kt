package com.example.thejokerer.navigation

import androidx.navigation.NavBackStackEntry

/**
 * Checks if navigation to a specified destination is allowed based on the current navigation state.
 *
 * The `canNavigate` function evaluates whether navigation to the specified [destination] is allowed
 * based on the current [current] navigation back stack entry. It returns `true` if navigation is allowed
 * and `false` otherwise.
 *
 * @param current The current navigation back stack entry.
 * @param destination The destination route to check for navigation.
 * @return `true` if navigation to the specified destination is allowed, `false` otherwise.
 */
fun canNavigate(current: NavBackStackEntry?, destination: String): Boolean {
    val currentDest = current?.destination?.route
    return if (current != null) currentDest != destination else true
}
