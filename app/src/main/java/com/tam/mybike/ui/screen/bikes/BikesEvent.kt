package com.tam.mybike.ui.screen.bikes

sealed class BikesEvent {
    data class DeleteBike(val bikeId: Int): BikesEvent()
}