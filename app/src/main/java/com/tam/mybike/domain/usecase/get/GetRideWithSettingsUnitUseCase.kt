package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.usecase.ConvertToSettingsUnitUseCase
import javax.inject.Inject

class GetRideWithSettingsUnitUseCase @Inject constructor(
    private val getSettingsUnit: GetSettingsUnitUseCase,
    private val convertToSettingsUnit: ConvertToSettingsUnitUseCase,
    private val getBikeWithSettingsUnit: GetBikeWithSettingsUnitUseCase
) {

    operator fun invoke(ride: Ride): Ride {
        val distanceUnit = getSettingsUnit()
        if (ride.distance.unit == distanceUnit && ride.bike.serviceIn.unit == distanceUnit) {
            return ride
        }

        val newDistance = convertToSettingsUnit(ride.distance)
        val newBike = getBikeWithSettingsUnit(ride.bike)
        return ride.copy(
            distance = newDistance,
            bike = newBike
        )
    }

}