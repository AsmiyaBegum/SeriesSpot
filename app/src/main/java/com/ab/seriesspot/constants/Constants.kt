package com.ab.seriesspot.constants

// Define the constant in a separate file or a companion object
object NavArgumentsKeys {
    const val KEY_ROUTE = "route_key"
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
}
