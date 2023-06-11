package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride
import javax.inject.Inject

class GetRidesTotalDistanceUseCase @Inject constructor() {

    operator fun invoke(rides: List<Ride>, distanceUnit: DistanceUnit): Distance {
        val totalDistanceAmount = rides.sumOf { it.distance.amount }
        return Distance(totalDistanceAmount, distanceUnit)
    }

}