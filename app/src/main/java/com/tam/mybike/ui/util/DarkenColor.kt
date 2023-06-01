package com.tam.mybike.ui.util

import androidx.compose.ui.graphics.Color

fun Color.darken(darkenFactor: Float) =
    copy(
        red = red * darkenFactor,
        green = green * darkenFactor,
        blue = blue * darkenFactor
    )