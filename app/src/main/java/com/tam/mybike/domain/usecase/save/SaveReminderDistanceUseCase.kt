package com.tam.mybike.domain.usecase.save

import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class SaveReminderDistanceUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(reminderDistance: Distance) {
        repository.saveSettingsReminderDistance(reminderDistance)
    }

}