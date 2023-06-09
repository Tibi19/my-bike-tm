package com.tam.mybike.ui.screen.ride.add

import com.tam.mybike.domain.model.Ride
import com.tam.mybike.ui.screen.ride.form.RideFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRideViewModel @Inject constructor() : RideFormViewModel() {

    init {
        loadSettingsUnit()
        loadBikes()
    }

    override fun confirmForm() {
        val ride = with(mutableState.value) {
            val bike = selectedBike ?: return
            val distance = distance ?: return
            val minutes = durationMinutes ?: return
            Ride(
                name = title,
                bike = bike,
                distance = distance,
                minutes = minutes,
                dateMillis = dateMillis
            )
        }
        addRide(ride)
    }

    private fun addRide(ride: Ride) {
        TODO("Not yet implemented")
    }

}