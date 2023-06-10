package com.tam.mybike.ui.screen.rides

sealed class RidesEvent {
    data class DeleteRide(val rideId: Int?) : RidesEvent()
}
