package com.tam.mybike.domain.usecase.update

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.repository.Repository
import com.tam.mybike.domain.usecase.ConvertToSettingsUnitUseCase
import javax.inject.Inject

private const val SERVICE_IN_MIN = 0

class UpdateBikeWithRideDistanceUseCase @Inject constructor(
    private val convertToSettingsUnit: ConvertToSettingsUnitUseCase,
    private val updateBike: UpdateBikeUseCase
) {

    suspend operator fun invoke(ride: Ride, bike: Bike) {
        val rideDistance = convertToSettingsUnit(ride.distance)
        val bikeServiceIn = convertToSettingsUnit(bike.serviceIn)
        val newBikeServiceInAmount = (bikeServiceIn.amount - rideDistance.amount).coerceAtLeast(SERVICE_IN_MIN)
        val editedBike = bike.copy(
            serviceIn = Distance(
                amount = newBikeServiceInAmount,
                unit = bikeServiceIn.unit
            )
        )
        updateBike(editedBike)
    }

}