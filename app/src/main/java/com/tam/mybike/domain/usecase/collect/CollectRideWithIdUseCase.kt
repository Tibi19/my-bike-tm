package com.tam.mybike.domain.usecase.collect

import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class CollectRideWithIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(rideId: Int, onCollect: (Ride) -> Unit) {
        repository
            .getRideWithIdFlow(rideId)
            .collect { ride ->
                onCollect(ride)
            }
    }

}

