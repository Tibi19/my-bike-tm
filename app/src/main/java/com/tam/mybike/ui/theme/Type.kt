package com.tam.mybike.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

val Typography = Typography(
    titleLarge = getLargeTextStyle(fontSize = SIZE_TEXT_X_LARGE),
    titleMedium = getMediumTextStyle(fontSize = SIZE_TEXT_X_LARGE),
    headlineLarge = getLargeTextStyle(fontSize = SIZE_TEXT_LARGE),
    headlineMedium = getMediumTextStyle(fontSize = SIZE_TEXT_LARGE),
    bodyLarge = getLargeTextStyle(fontSize = SIZE_TEXT_MEDIUM),
    bodyMedium = getMediumTextStyle(fontSize = SIZE_TEXT_MEDIUM),
    bodySmall = TextStyle(
        fontSize = SIZE_TEXT_SMALL,
        color = Gray
    ),
    labelMedium = getMediumTextStyle(fontSize = SIZE_TEXT_XX_SMALL),
)

private fun getMediumTextStyle(fontSize: TextUnit) =
    TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = fontSize
    )

private fun getLargeTextStyle(fontSize: TextUnit) =
    TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = fontSize
    )