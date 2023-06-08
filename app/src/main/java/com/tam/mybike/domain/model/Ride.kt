package com.tam.mybike.domain.model

data class Ride(
    val id: Int,
    val name: String,
    val bike: Bike,
    val distance: Distance,
    val minutes: Int,
    val dateMillis: Long
)
