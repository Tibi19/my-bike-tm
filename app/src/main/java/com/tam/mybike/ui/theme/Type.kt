package com.tam.mybike.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = SIZE_TEXT_X_LARGE
    ),
    titleMedium = TextStyle(fontSize = SIZE_TEXT_X_LARGE),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = SIZE_TEXT_LARGE
    ),
    headlineMedium = TextStyle(fontSize = SIZE_TEXT_LARGE),
    bodyMedium = TextStyle(fontSize = SIZE_TEXT_MEDIUM),
    labelMedium = TextStyle(fontSize = SIZE_TEXT_XX_SMALL)
)