package com.tam.mybike.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationScaffold() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { scaffoldPaddingValues ->
        MainNavGraph(
            navController = navController,
            modifier = Modifier
                .padding(bottom = scaffoldPaddingValues.calculateBottomPadding())
        )
    }

}