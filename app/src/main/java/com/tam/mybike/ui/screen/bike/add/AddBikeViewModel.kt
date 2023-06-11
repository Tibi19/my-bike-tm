package com.tam.mybike.ui.screen.bike.add

import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.usecase.collect.CollectBikesUseCase
import com.tam.mybike.domain.usecase.collect.CollectDefaultBikeUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import com.tam.mybike.domain.usecase.insert.InsertBikeUseCase
import com.tam.mybike.domain.usecase.save.SaveDefaultBikeIdUseCase
import com.tam.mybike.ui.screen.bike.form.BikeFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBikeViewModel @Inject constructor(
    getSettingsUnit: GetSettingsUnitUseCase,
    private val saveDefaultBikeId: SaveDefaultBikeIdUseCase,
    private val collectDefaultBike: CollectDefaultBikeUseCase,
    private val collectBikes: CollectBikesUseCase,
    private val insertBike: InsertBikeUseCase
) : BikeFormViewModel(getSettingsUnit) {

    init {
        loadSettingsUnit()
        loadIsDefaultBike()
    }

    private fun loadIsDefaultBike() =
        viewModelScope.launch {
            collectDefaultBike { defaultBike ->
                if (defaultBike == null) {
                    updateStateAsDefaultBike()
                }
                this.coroutineContext.job.cancel()
            }
        }

    private fun updateStateAsDefaultBike() {
        mutableState.update {
            it.copy(isDefaultBike = true)
        }
    }

    override fun confirmForm() {
        val bike = with(mutableState.value) {
            Bike(
                name = bikeName,
                type = selectedBikeType,
                wheelSize = wheelSize,
                colorHex = selectedColor.value,
                serviceIn = serviceIn ?: Distance(0, distanceUnit)
            )
        }
        viewModelScope.launch {
            addBike(bike)
            if (mutableState.value.isDefaultBike) {
                saveInsertedBikeAsDefault()
            }
        }
    }

    private suspend fun addBike(bike: Bike) {
        insertBike(bike)
    }

    private suspend fun saveInsertedBikeAsDefault() =
        coroutineScope {
            collectBikes { bikes ->
                val insertedBike = bikes.maxBy { it.id }
                saveDefaultBikeId(insertedBike.id)
                coroutineContext.job.cancel()
            }
        }

}