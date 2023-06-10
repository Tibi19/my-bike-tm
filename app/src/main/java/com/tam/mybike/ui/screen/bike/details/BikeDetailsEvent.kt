package com.tam.mybike.ui.screen.bike.details

sealed class BikeDetailsEvent {
    data class OnBikeDelete(val bikeId: Int) : BikeDetailsEvent()
    data class OnRideDelete(val rideId: Int?) : BikeDetailsEvent()
}
