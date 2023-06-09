package com.tam.mybike.ui.screen.ride.edit

import androidx.lifecycle.SavedStateHandle
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.ui.screen.ride.form.RideFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditRideViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : RideFormViewModel() {

    init {
        savedStateHandle.get<Int>("TODO////PLACEHOLDER_ID_KEY")?.let { rideId ->
            loadRide(rideId)
        }
        loadSettingsUnit()
        loadBikes()
    }

    private fun loadRide(rideId: Int) {
        TODO("Not yet implemented")
    }

    override fun confirmForm() {
        val rideId = savedStateHandle.get<Int>("TODO////PLACEHOLDER_ID_KEY") ?: return
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
        updateRide(ride)
    }

    private fun updateRide(ride: Ride) {
        TODO("Not yet implemented")
    }

}