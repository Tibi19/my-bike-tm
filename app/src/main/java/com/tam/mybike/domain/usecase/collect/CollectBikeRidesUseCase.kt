package com.tam.mybike.domain.usecase.collect

import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class CollectBikeRidesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(bikeId: Int, onCollect: (List<Ride>) -> Unit) {
        repository
            .getBikeRidesFlow(bikeId)
            .collect { rides ->
                onCollect(rides)
            }
    }

}