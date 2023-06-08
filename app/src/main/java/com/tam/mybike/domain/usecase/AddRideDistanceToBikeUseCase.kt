package com.tam.mybike.domain.usecase

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.Ride
import javax.inject.Inject

private const val SERVICE_IN_MIN = 0

class AddRideDistanceToBikeUseCase @Inject constructor(
    private val convertToSettingsUnit: ConvertToSettingsUnitUseCase
) {

    operator fun invoke(ride: Ride, bike: Bike): Bike {
        val rideDistance = convertToSettingsUnit(ride.distance)
        val bikeServiceIn = convertToSettingsUnit(bike.serviceIn)
        val newBikeServiceInAmount = (bikeServiceIn.amount - rideDistance.amount).coerceAtLeast(SERVICE_IN_MIN)
        return bike.copy(
            serviceIn = Distance(
                amount = newBikeServiceInAmount,
                unit = bikeServiceIn.unit
            )
        )
    }

}