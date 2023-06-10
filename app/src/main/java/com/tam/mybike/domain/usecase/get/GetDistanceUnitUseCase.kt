package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class GetDistanceUnitUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): DistanceUnit =
        repository.getSettingsDistanceUnit()

}