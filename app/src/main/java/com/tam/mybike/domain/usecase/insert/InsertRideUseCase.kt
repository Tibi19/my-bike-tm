package com.tam.mybike.domain.usecase.insert

import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class InsertRideUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(ride: Ride) {
        repository.insertRide(ride)
    }

}