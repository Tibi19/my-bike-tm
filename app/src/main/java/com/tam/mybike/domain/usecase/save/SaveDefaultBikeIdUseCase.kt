package com.tam.mybike.domain.usecase.save

import com.tam.mybike.domain.repository.Repository
import javax.inject.Inject

class SaveDefaultBikeIdUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(defaultBikeId: Int) {
        repository.saveSettingsDefaultBikeId(defaultBikeId)
    }

}