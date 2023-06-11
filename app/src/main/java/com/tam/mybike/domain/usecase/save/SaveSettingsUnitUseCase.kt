package com.tam.mybike.domain.usecase.save

import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class SaveSettingsUnitUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(distanceUnit: DistanceUnit) {
        repository.saveSettingsDistanceUnit(distanceUnit)
    }

}