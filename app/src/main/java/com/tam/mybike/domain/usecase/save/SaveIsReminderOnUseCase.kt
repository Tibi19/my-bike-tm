package com.tam.mybike.domain.usecase.save

import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class SaveIsReminderOnUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(isReminderOn: Boolean) {
        repository.saveSettingsIsReminderOn(isReminderOn)
    }

}