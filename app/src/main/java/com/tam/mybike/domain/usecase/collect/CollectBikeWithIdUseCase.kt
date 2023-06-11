package com.tam.mybike.domain.usecase.collect

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class CollectBikeWithIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(bikeId: Int, onCollect: (Bike) -> Unit) {
        if (bikeId < 0) return
        repository
            .getBikeWithIdFlow(bikeId)
            .collect { bike ->
                onCollect(bike)
            }
    }

}