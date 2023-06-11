package com.tam.mybike.ui.screen.bike.edit

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.usecase.collect.CollectBikeWithIdUseCase
import com.tam.mybike.domain.usecase.get.GetBikeWithSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetDefaultBikeIdUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import com.tam.mybike.domain.usecase.save.SaveDefaultBikeIdUseCase
import com.tam.mybike.domain.usecase.update.UpdateBikeUseCase
import com.tam.mybike.ui.navigation.ARG_BIKE_ID
import com.tam.mybike.ui.screen.bike.form.BikeFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditBikeViewModel @Inject constructor(
    getSettingsUnit: GetSettingsUnitUseCase,
    private val collectBike: CollectBikeWithIdUseCase,
    private val getBikeWithSettingsUnit: GetBikeWithSettingsUnitUseCase,
    private val getDefaultBikeId: GetDefaultBikeIdUseCase,
    private val updateBike: UpdateBikeUseCase,
    private val saveDefaultBikeId: SaveDefaultBikeIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : BikeFormViewModel(getSettingsUnit) {

    init {
        loadSettingsUnit()
        savedStateHandle.get<Int>(ARG_BIKE_ID)?.let { bikeId ->
            loadBike(bikeId)
        }
    }

    private fun loadBike(bikeId: Int) =
        viewModelScope.launch {
            collectBike(bikeId) { bike ->
                bike ?: return@collectBike
                val bikeWithSettingsUnit = getBikeWithSettingsUnit(bike)
                updateStateWithBike(bikeWithSettingsUnit)
                this.coroutineContext.job.cancel()
            }
        }

    private fun updateStateWithBike(bike: Bike) {
        mutableState.update {
            it.copy(
                selectedColor = Color(bike.colorHex),
                selectedBikeType = bike.type,
                bikeName = bike.name,
                wheelSize = bike.wheelSize,
                serviceIn = bike.serviceIn,
                isDefaultBike = bike.id == getDefaultBikeId()
            )
        }
    }

    override fun confirmForm() {
        val bikeId = savedStateHandle.get<Int>(ARG_BIKE_ID) ?: return
        val bike = with(mutableState.value) {
            Bike(
                id = bikeId,
                name = bikeName,
                type = selectedBikeType,
                wheelSize = wheelSize,
                colorHex = selectedColor.value,
                serviceIn = serviceIn ?: Distance(0, distanceUnit)
            )
        }
        editBike(bike)
        if (mutableState.value.isDefaultBike) {
            saveDefaultBikeId(bikeId)
        }
    }

    private fun editBike(bike: Bike) =
        viewModelScope.launch {
            updateBike(bike)
        }

}