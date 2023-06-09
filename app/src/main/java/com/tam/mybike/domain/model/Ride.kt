package com.tam.mybike.domain.model

data class Ride(
    val id: Int = 0,
    val name: String,
    val bike: Bike,
    val distance: Distance,
    val minutes: Int,
    val dateMillis: Long
)
