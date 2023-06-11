package com.tam.mybike.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.usecase.collect.CollectBikesUseCase
import com.tam.mybike.domain.usecase.collect.CollectDefaultBikeUseCase
import com.tam.mybike.domain.usecase.get.GetIsReminderOnUseCase
import com.tam.mybike.domain.usecase.get.GetReminderDistanceUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import com.tam.mybike.domain.usecase.save.SaveDefaultBikeIdUseCase
import com.tam.mybike.domain.usecase.save.SaveIsReminderOnUseCase
import com.tam.mybike.domain.usecase.save.SaveReminderDistanceUseCase
import com.tam.mybike.domain.usecase.save.SaveSettingsUnitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val collectDefaultBike: CollectDefaultBikeUseCase,
    private val collectBikes: CollectBikesUseCase,
    private val getSettingsUnit: GetSettingsUnitUseCase,
    private val getReminderDistance: GetReminderDistanceUseCase,
    private val getIsReminderOn: GetIsReminderOnUseCase,
    private val saveSettingsUnit: SaveSettingsUnitUseCase,
    private val saveReminderDistance: SaveReminderDistanceUseCase,
    private val saveIsReminderOn: SaveIsReminderOnUseCase,
    private val saveDefaultBikeId: SaveDefaultBikeIdUseCase
) : ViewModel() {

    private val mutableState = MutableStateFlow(SettingsState())
    val state = mutableState.asStateFlow()

    init {
        updateSettingsUnit()
        updateDefaultBike()
        updateReminderDistance()
        updateIsReminderOn()
        observeBikes()
    }

    private fun updateDefaultBike() =
        viewModelScope.launch {
            collectDefaultBike { bike ->
                mutableState.update {
                    it.copy(defaultBike = bike)
                }
                this.coroutineContext.job.cancel()
            }
        }

    private fun updateSettingsUnit() {
        mutableState.update {
            it.copy(distanceUnit = getSettingsUnit())
        }
    }

    private fun updateReminderDistance() {
        mutableState.update {
            it.copy(reminderDistance = getReminderDistance())
        }
    }

    private fun updateIsReminderOn() {
        mutableState.update {
            it.copy(isReminderOn = getIsReminderOn())
        }
    }

    private fun observeBikes() =
        viewModelScope.launch {
            collectBikes { bikes ->
                updateBikes(bikes)
            }
        }

    private fun updateBikes(bikes: List<Bike>) {
        mutableState.update {
            it.copy(bikes = bikes)
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnDistanceUnitChange -> changeDistanceUnit(event.newUnit)
            is SettingsEvent.OnReminderDistanceChange -> changeReminderDistance(event.newDistance)
            is SettingsEvent.OnSwitchIsReminderOn -> switchIsReminderOn()
            is SettingsEvent.OnDefaultBikeChange -> changeDefaultBike(event.newBike)
        }
    }

    private fun changeDistanceUnit(newUnit: DistanceUnit) {
        saveSettingsUnit(newUnit)
        updateSettingsUnit()
        updateReminderDistance()
    }

    private fun changeReminderDistance(newDistance: Distance) {
        saveReminderDistance(newDistance)
        updateReminderDistance()
    }

    private fun switchIsReminderOn() {
        saveIsReminderOn(!mutableState.value.isReminderOn)
        updateIsReminderOn()
    }

    private fun changeDefaultBike(newBike: Bike) {
        saveDefaultBikeId(newBike.id)
        updateDefaultBike()
    }

}

