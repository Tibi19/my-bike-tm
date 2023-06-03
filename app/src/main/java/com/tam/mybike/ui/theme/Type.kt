package com.tam.mybike.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

val Typography = Typography(
    titleLarge = getSemiBoldTextStyle(fontSize = SIZE_TEXT_X_LARGE),
    titleMedium = getNormalTextStyle(fontSize = SIZE_TEXT_X_LARGE),
    headlineLarge = getSemiBoldTextStyle(fontSize = SIZE_TEXT_LARGE),
    headlineMedium = getNormalTextStyle(fontSize = SIZE_TEXT_LARGE),
    bodyLarge = getSemiBoldTextStyle(fontSize = SIZE_TEXT_MEDIUM),
    bodyMedium = getNormalTextStyle(fontSize = SIZE_TEXT_MEDIUM),
    bodySmall = TextStyle(
        fontSize = SIZE_TEXT_SMALL,
        color = Gray
    ),
    labelLarge = TextStyle(
        fontSize = SIZE_TEXT_SMALL,
        color = Color.White
    ),
    labelMedium = getNormalTextStyle(fontSize = SIZE_TEXT_XX_SMALL),
)

private fun getNormalTextStyle(fontSize: TextUnit) =
    TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = fontSize
    )

private fun getSemiBoldTextStyle(fontSize: TextUnit) =
    TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = fontSize
    )