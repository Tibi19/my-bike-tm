package com.tam.mybike.domain.usecase.collect

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class CollectBikesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(onCollect: (List<Bike>) -> Unit) {
        repository
            .getBikesFlow()
            .collect { bikes ->
                onCollect(bikes)
            }
    }

}