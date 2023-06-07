package com.tam.mybike.ui.screen.bikes

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.DistanceUnit

data class BikesState(
    val bikes: List<Bike> = emptyList(),
    val bikeToProgressMap: Map<Int, Float> = emptyMap(),
    val distanceUnit: DistanceUnit = DistanceUnit.DEFAULT,
)