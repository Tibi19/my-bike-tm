package com.tam.mybike.domain.usecase

import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.usecase.get.GetDistanceUnitUseCase
import javax.inject.Inject

private const val KM_TO_MI_CONVERSION_FACTOR = 0.621371
private const val MI_TO_KM_CONVERSION_FACTOR = 1.609344

class ConvertToSettingsUnitUseCase @Inject constructor(
    private val getDistanceUnit: GetDistanceUnitUseCase
) {

    operator fun invoke(distance: Distance): Distance =
        when (val settingsDistanceUnit = getDistanceUnit()) {
            distance.unit -> distance
            DistanceUnit.KM ->
                Distance(
                    amount = convertMiToKm(distance.amount),
                    unit = settingsDistanceUnit
                )
            DistanceUnit.MI ->
                Distance(
                    amount = convertKmToMi(distance.amount),
                    unit = settingsDistanceUnit
                )
        }

    private fun convertKmToMi(kmDistance: Int) =
        (kmDistance * KM_TO_MI_CONVERSION_FACTOR).toInt()

    private fun convertMiToKm(miDistance: Int) =
        (miDistance * MI_TO_KM_CONVERSION_FACTOR).toInt()

}