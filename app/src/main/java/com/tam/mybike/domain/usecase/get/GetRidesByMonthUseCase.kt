package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.Ride
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.SortedMap
import javax.inject.Inject

class GetRidesByMonthUseCase @Inject constructor() {

    operator fun invoke(rides: List<Ride>): SortedMap<Int, List<Ride>> =
        rides
            .sortedByDescending { it.dateMillis }
            .groupBy { ride ->
                LocalDateTime
                    .ofInstant(
                        Instant.ofEpochMilli(ride.dateMillis),
                        ZoneId.systemDefault()
                    )
                    .monthValue
            }
            .toSortedMap(
                compareByDescending { it }
            )

}