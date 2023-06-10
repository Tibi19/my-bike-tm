package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.usecase.ConvertToSettingsUnitUseCase
import javax.inject.Inject

private const val MAX_PROGRESS_DISTANCE_KM = 1000
private const val PROGRESS_MIN = 0f
private const val PROGRESS_MAX = 1f

class GetBikeProgressUseCase @Inject constructor(
    private val convertToSettingsUnit: ConvertToSettingsUnitUseCase
) {

    operator fun invoke(bike: Bike): Float {
        val serviceIn = convertToSettingsUnit(bike.serviceIn)
        val maxProgressDistanceKm = Distance(
            amount = MAX_PROGRESS_DISTANCE_KM,
            unit = DistanceUnit.KM
        )
        val maxProgressDistance = convertToSettingsUnit(maxProgressDistanceKm)
        val distanceProgressed = maxProgressDistance.amount - serviceIn.amount
        val progress = distanceProgressed.toFloat() / maxProgressDistance.amount.toFloat()
        return progress.coerceIn(PROGRESS_MIN, PROGRESS_MAX)
    }

}