package com.tam.mybike.ui.screen.bike.details

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride

data class BikeDetailsState(
    val bike: Bike? = null,
    val progress: Float = 0f,
    val rides: List<Ride> = emptyList(),
    val distanceUnit: DistanceUnit = DistanceUnit.DEFAULT,
    val totalRidesDistance: Distance = Distance(0, distanceUnit)
)
