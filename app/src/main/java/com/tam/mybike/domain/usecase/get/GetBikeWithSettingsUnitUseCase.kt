package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.usecase.ConvertToSettingsUnitUseCase
import javax.inject.Inject

class GetBikeWithSettingsUnitUseCase @Inject constructor(
    private val getDistanceUnit: GetSettingsUnitUseCase,
    private val convertToSettingsUnit: ConvertToSettingsUnitUseCase
) {

    operator fun invoke(bike: Bike): Bike {
        val distanceUnit = getDistanceUnit()
        if (bike.serviceIn.unit == distanceUnit) {
            return bike
        }

        val newServiceIn = convertToSettingsUnit(bike.serviceIn)
        return bike.copy(serviceIn = newServiceIn)
    }

}