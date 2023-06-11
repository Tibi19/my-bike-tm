package com.tam.mybike.ui.screen.ride.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.usecase.collect.CollectBikesUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class RideFormViewModel(
    private val collectBikes: CollectBikesUseCase,
    private val getSettingsUnit: GetSettingsUnitUseCase
) : ViewModel() {

    protected val mutableState = MutableStateFlow(RideFormState())
    val state = mutableState.asStateFlow()

    protected fun loadSettingsUnit() {
        mutableState.update {
            it.copy(distanceUnit = getSettingsUnit())
        }
    }

    protected fun observeBikes() =
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

    protected abstract fun confirmForm(fallbackTitle: String)

    fun onEvent(event: RideFormEvent) =
        when (event) {
            is RideFormEvent.OnChangeBike -> changeBike(event.newBike)
            is RideFormEvent.OnChangeDate -> changeDate(event.newDate)
            is RideFormEvent.OnChangeDistance -> changeDistance(event.newDistance)
            is RideFormEvent.OnChangeDuration -> changeDuration(event.newDuration)
            is RideFormEvent.OnChangeTitle -> changeTitle(event.newTitle)
            is RideFormEvent.OnConfirmForm -> confirmForm(event.fallbackTitle)
        }

    private fun changeBike(newBike: Bike) {
        mutableState.update {
            it.copy(selectedBike = newBike)
        }
    }

    private fun changeDate(newDateMillis: Long) {
        mutableState.update {
            it.copy(dateMillis = newDateMillis)
        }
    }

    private fun changeDistance(newDistance: Distance?) {
        mutableState.update {
            it.copy(distance = newDistance)
        }
    }

    private fun changeDuration(newDuration: Int?) {
        mutableState.update {
            it.copy(durationMinutes = newDuration)
        }
    }

    private fun changeTitle(newTitle: String) {
        mutableState.update {
            it.copy(title = newTitle)
        }
    }

}
