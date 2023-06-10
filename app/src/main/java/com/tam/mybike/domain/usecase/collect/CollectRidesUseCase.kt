package com.tam.mybike.domain.usecase.collect

import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class CollectRidesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(onCollect: (List<Ride>) -> Unit) {
        repository
            .getRidesFlow()
            .collect { rides ->
                onCollect(rides)
            }
    }

}
