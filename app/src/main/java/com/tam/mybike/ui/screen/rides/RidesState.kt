package com.tam.mybike.ui.screen.rides

import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride
import java.time.LocalDate
import java.util.SortedMap

data class RidesState(
    val ridesByMonth: SortedMap<Int, List<Ride>> = sortedMapOf(),
    val bikeTypeToDistanceMap: Map<BikeType, Distance> = emptyMap(),
    val distanceUnit: DistanceUnit = DistanceUnit.DEFAULT,
    val totalDistance: Distance = Distance(0, distanceUnit)
)
