package com.tam.mybike.domain.usecase.get

import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class GetIsReminderOnUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Boolean =
        repository.getSettingsIsReminderOn()

}