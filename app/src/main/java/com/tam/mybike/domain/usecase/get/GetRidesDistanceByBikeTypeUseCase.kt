package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride
import javax.inject.Inject

class GetRidesDistanceByBikeTypeUseCase @Inject constructor(
    private val getRidesTotalDistance: GetRidesTotalDistanceUseCase
) {

    operator fun invoke(rides: List<Ride>, distanceUnit: DistanceUnit): Map<BikeType, Distance> {
        val bikeTypeToRidesMap = rides.groupBy { ride ->
            ride.bike.type
        }
        return bikeTypeToRidesMap.mapValues { (_, bikeTypeRides) ->
            getRidesTotalDistance(bikeTypeRides, distanceUnit)
        }
    }

}