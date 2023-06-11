package com.tam.mybike.ui.navigation.util

import androidx.navigation.NavHostController

fun NavHostController.navigateAndReplaceStartRoute(newStartRoute: String) {
    val currentRoute = currentDestination?.route ?: return
    if (currentRoute == newStartRoute) return

    popBackStack(currentRoute, true)
    navigate(newStartRoute)
}