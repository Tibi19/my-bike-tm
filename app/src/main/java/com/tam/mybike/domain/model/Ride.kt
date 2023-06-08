package com.tam.mybike.domain.model

data class Ride(
    val id: Int,
    val name: String,
    val bikeId: Int,
    val distance: Distance,
    val minutes: Int,
    val date: Long
)
