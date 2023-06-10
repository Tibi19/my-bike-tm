package com.tam.mybike.data.mapper

import com.tam.mybike.data.entity.BikeEntity
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance

fun Bike.toBikeEntity(): BikeEntity =
    BikeEntity(
        id = id,
        name = name,
        type = type,
        wheelSize = wheelSize,
        colorHex = colorHex.toLong(),
        serviceInAmount = serviceIn.amount,
        distanceUnit = serviceIn.unit
    )

fun BikeEntity.toBike(): Bike =
    Bike(
        id = id,
        name = name,
        type = type,
        wheelSize = wheelSize,
        colorHex = colorHex.toULong(),
        serviceIn = Distance(serviceInAmount, distanceUnit)
    )

fun List<BikeEntity>.toBikes(): List<Bike> =
    map { bikeEntity ->
        bikeEntity.toBike()
    }