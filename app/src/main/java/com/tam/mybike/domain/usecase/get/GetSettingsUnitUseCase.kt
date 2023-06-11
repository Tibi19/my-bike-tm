package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class GetSettingsUnitUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): DistanceUnit =
        repository.getSettingsDistanceUnit()

}