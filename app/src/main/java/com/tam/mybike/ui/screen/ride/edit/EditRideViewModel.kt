package com.tam.mybike.ui.screen.ride.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.usecase.collect.CollectBikesUseCase
import com.tam.mybike.domain.usecase.collect.CollectRideWithIdUseCase
import com.tam.mybike.domain.usecase.get.GetRideWithSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import com.tam.mybike.domain.usecase.update.UpdateRideUseCase
import com.tam.mybike.ui.navigation.ARG_RIDE_ID
import com.tam.mybike.ui.screen.ride.form.RideFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditRideViewModel @Inject constructor(
    collectBikes: CollectBikesUseCase,
    getSettingsUnit: GetSettingsUnitUseCase,
    private val collectRide: CollectRideWithIdUseCase,
    private val getRideWithSettingsUnit: GetRideWithSettingsUnitUseCase,
    private val updateRide: UpdateRideUseCase,
    private val savedStateHandle: SavedStateHandle
) : RideFormViewModel(
    collectBikes,
    getSettingsUnit
) {

    init {
        loadSettingsUnit()
        savedStateHandle.get<Int>(ARG_RIDE_ID)?.let { rideId ->
            loadRide(rideId)
        }
        observeBikes()
    }

    private fun loadRide(rideId: Int) =
        viewModelScope.launch {
            collectRide(rideId) { ride ->
                val rideWithSettingsUnit = getRideWithSettingsUnit(ride)
                updateStateWithRide(rideWithSettingsUnit)
                this.coroutineContext.job.cancel()
            }
        }

    private fun updateStateWithRide(ride: Ride) {
        mutableState.update {
            it.copy(
                title = ride.name,
                selectedBike = ride.bike,
                distance = ride.distance,
                durationMinutes = ride.minutes,
                dateMillis = ride.dateMillis
            )
        }
    }

    override fun confirmForm(fallbackTitle: String) {
        val rideId = savedStateHandle.get<Int>(ARG_RIDE_ID) ?: return
        val ride = with(mutableState.value) {
            val bike = selectedBike ?: return
            val distance = distance ?: return
            val minutes = durationMinutes ?: return
            Ride(
                id = rideId,
                name = title,
                bike = bike,
                distance = distance,
                minutes = minutes,
                dateMillis = dateMillis
            )
        }
        editRide(ride)
    }

    private fun editRide(ride: Ride) =
        viewModelScope.launch {
            updateRide(ride)
        }

}