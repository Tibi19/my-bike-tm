package com.tam.mybike.domain.usecase.collect

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.usecase.get.GetDefaultBikeIdUseCase
import javax.inject.Inject

class CollectDefaultBikeUseCase @Inject constructor(
    private val getDefaultBikeId: GetDefaultBikeIdUseCase,
    private val collectBikeWithId: CollectBikeWithIdUseCase
) {

    suspend operator fun invoke(onCollect: (Bike?) -> Unit) {
        val defaultBikeId = getDefaultBikeId()
        collectBikeWithId(defaultBikeId, onCollect)
    }

}