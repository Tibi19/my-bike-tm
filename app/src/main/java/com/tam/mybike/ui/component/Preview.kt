package com.tam.mybike.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tam.mybike.ui.theme.MyBikeTheme
import com.tam.mybike.ui.theme.WIDTH_PREVIEW

@Composable
fun ScreenPreview(content: @Composable () -> Unit) =
    MyBikeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = Color.White,
            modifier = Modifier
                .fillMaxHeight()
                .width(WIDTH_PREVIEW)
        ) {
            content()
        }
    }

@Composable
fun WrapHeightPreview(content: @Composable () -> Unit) =
    MyBikeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = Color.White,
            modifier = Modifier.width(WIDTH_PREVIEW)
        ) {
            content()
        }
    }