package com.tam.mybike.domain.usecase.delete

import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class DeleteRideUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(rideId: Int) {
        repository.deleteRide(rideId)
    }

}