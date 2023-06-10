package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.repository.Repository
import com.tam.mybike.domain.usecase.ConvertToSettingsUnitUseCase
import javax.inject.Inject

class GetReminderDistanceUseCase @Inject constructor(
    private val repository: Repository,
    private val convertToSettingsUnit: ConvertToSettingsUnitUseCase
) {

    operator fun invoke(): Distance {
        val reminderDistance = repository.getSettingsReminderDistance()
        return convertToSettingsUnit(reminderDistance)
    }

}