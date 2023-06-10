package com.tam.mybike.domain.usecase.insert

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class InsertBikeUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(bike: Bike) {
        repository.insertBike(bike)
    }

}