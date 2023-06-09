package com.tam.mybike.ui.screen.ride.form

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit

data class RideFormState(
    val title: String = "",
    val selectedBike: Bike? = null,
    val bikes: List<Bike> = emptyList(),
    val distance: Distance? = null,
    val distanceUnit: DistanceUnit = DistanceUnit.DEFAULT,
    val durationMinutes: Int? = null,
    val dateMillis: Long = System.currentTimeMillis()
)