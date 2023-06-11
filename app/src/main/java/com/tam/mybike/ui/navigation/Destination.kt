package com.tam.mybike.ui.navigation

const val ARG_BIKE_ID = "bike_id"
const val ARG_RIDE_ID = "ride_id"

sealed class Destination(val route: String) {
    object Bikes : Destination("bikes")
    object Rides : Destination("rides")
    object Settings : Destination("settings")
    object AddBike : Destination("add/bike")
    object AddRide : Destination("add/ride")

    object BikeDetails : Destination("bike/details/{$ARG_BIKE_ID}") {
        fun createRoute(bikeId: Int) = "bike/details/$bikeId"
    }

    object EditBike : Destination("edit/bike/{$ARG_BIKE_ID}") {
        fun createRoute(bikeId: Int) = "edit/bike/$bikeId"
    }

    object EditRide : Destination("edit/ride/{$ARG_RIDE_ID}") {
        fun createRoute(rideId: Int) = "edit/ride/$rideId"
    }
}