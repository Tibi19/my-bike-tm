package com.tam.mybike.ui.screen.bikes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.usecase.collect.CollectBikesUseCase
import com.tam.mybike.domain.usecase.delete.DeleteBikeUseCase
import com.tam.mybike.domain.usecase.get.GetBikeProgressUseCase
import com.tam.mybike.domain.usecase.get.GetBikeWithSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikesViewModel @Inject constructor(
    private val collectBikes: CollectBikesUseCase,
    private val getBikeProgress: GetBikeProgressUseCase,
    private val getSettingsUnit: GetSettingsUnitUseCase,
    private val getBikeWithSettingsUnit: GetBikeWithSettingsUnitUseCase,
    private val deleteBike: DeleteBikeUseCase
) : ViewModel() {

    private val mutableState = MutableStateFlow(BikesState())
    val state = mutableState.asStateFlow()

    init {
        loadSettingsUnit()
        observeBikes()
    }

    private fun loadSettingsUnit() {
        mutableState.update {
            it.copy(distanceUnit = getSettingsUnit())
        }
    }

    private fun observeBikes() =
        viewModelScope.launch {
            collectBikes { bikes ->
                val bikesWithSettingsUnit = bikes.map { bike ->
                    getBikeWithSettingsUnit(bike)
                }
                updateBikes(bikesWithSettingsUnit)
                updateBikeToProgressMap(bikesWithSettingsUnit)
                updateIsLoading()
            }
        }

    private fun updateBikes(bikes: List<Bike>) {
        mutableState.update {
            it.copy(bikes = bikes)
        }
    }

    private fun updateBikeToProgressMap(bikes: List<Bike>) {
        val bikeToProgressMap = bikes.associate { bike ->
            bike.id to getBikeProgress(bike)
        }
        mutableState.update {
            it.copy(bikeToProgressMap = bikeToProgressMap)
        }
    }

    private fun updateIsLoading() {
        mutableState.update {
            it.copy(isLoading = false)
        }
    }

    fun onEvent(event: BikesEvent) {
        if (event !is BikesEvent.DeleteBike) return
        removeBike(event.bikeId)
    }

    private fun removeBike(bikeId: Int?) {
        bikeId ?: return
        viewModelScope.launch {
            deleteBike(bikeId)
        }
    }

}