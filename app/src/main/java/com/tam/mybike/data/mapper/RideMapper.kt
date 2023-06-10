package com.tam.mybike.data.mapper

import com.tam.mybike.data.entity.RideEntity
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.Ride

fun Ride.toRideEntity(): RideEntity =
    RideEntity(
        id = id,
        name = name,
        bike = bike.toBikeEntity(),
        distanceAmount = distance.amount,
        distanceUnit = distance.unit,
        minutes = minutes,
        dateMillis = dateMillis
    )

fun RideEntity.toRide(): Ride =
    Ride(
        id = id,
        name = name,
        bike = bike.toBike(),
        distance = Distance(distanceAmount, distanceUnit),
        minutes = minutes,
        dateMillis = dateMillis
    )

fun List<RideEntity>.toRides(): List<Ride> =
    map { rideEntity ->
        rideEntity.toRide()
    }