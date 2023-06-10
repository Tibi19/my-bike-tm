package com.tam.mybike.domain.usecase.update

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class UpdateBikeUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(bike: Bike) {
        repository.updateBike(bike)
    }

}