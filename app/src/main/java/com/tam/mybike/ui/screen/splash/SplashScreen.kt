package com.tam.mybike.ui.screen.splash

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.tam.mybike.R
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.theme.TEXT_SPLASH_BACKGROUND_CONTENT
import com.tam.mybike.ui.theme.TEXT_SPLASH_LOGO_CONTENT
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay

private const val TIME_SPLASH = 1000L

@Composable
fun SplashScreen(goToBikesScreen: () -> Unit) {

    val view = LocalView.current
    val window = (view.context as Activity).window

    FullScreenEffect(window)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.image_source_unsplash),
            contentDescription = TEXT_SPLASH_BACKGROUND_CONTENT,
            alignment = Alignment.CenterEnd,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Icon(
            painter = painterResource(R.drawable.logo),
            contentDescription = TEXT_SPLASH_LOGO_CONTENT,
            modifier = Modifier.align(Alignment.Center)
        )
    }

    LaunchedEffect(key1 = Unit) {
        awaitFrame()
        delay(TIME_SPLASH)
        restoreWindow(window)
        goToBikesScreen()
    }

}

@Composable
private fun FullScreenEffect(window: Window) =
    LaunchedEffect(key1 = Unit) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

private fun restoreWindow(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    } else {
        window.insetsController?.apply {
            show(WindowInsets.Type.statusBars())
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() =
    ScreenDarkPreview {
        SplashScreen(goToBikesScreen = {})
    }
