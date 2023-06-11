package com.tam.mybike.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
private fun ChangeStatusBarColorEffect(color: Color) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(key1 = systemUiController) {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
        onDispose {}
    }

}

@Composable
fun MaterialTheme.SurfaceStatusBarEffect() {
    ChangeStatusBarColorEffect(color = colorScheme.surface)
}

@Composable
fun MaterialTheme.SurfaceVariantStatusBarEffect() {
    ChangeStatusBarColorEffect(color = colorScheme.surfaceVariant)
}