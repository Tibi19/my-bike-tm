package com.tam.mybike.domain.model

import androidx.compose.ui.graphics.Color

data class Bike(
    val id: Int,
    val name: String,
    val type: BikeType,
    val wheelSize: WheelSize,
    val color: Color,
    val serviceIn: Distance
)
