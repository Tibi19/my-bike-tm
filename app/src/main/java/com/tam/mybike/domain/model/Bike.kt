package com.tam.mybike.domain.model

data class Bike(
    val id: Int,
    val name: String,
    val type: BikeType,
    val wheelSize: WheelSize,
    val colorHex: ULong,
    val serviceIn: Distance
)
