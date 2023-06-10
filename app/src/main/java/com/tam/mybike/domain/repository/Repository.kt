package com.tam.mybike.domain.repository

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertBike(bike: Bike)
    suspend fun insertRide(ride: Ride)
    suspend fun updateBike(bike: Bike)
    suspend fun updateRide(ride: Ride)
    suspend fun deleteBike(bikeId: Int)
    suspend fun deleteRide(rideId: Int)
    suspend fun getBikesFlow(): Flow<List<Bike>>
    suspend fun getRidesFlow(): Flow<List<Ride>>
    suspend fun getBikeWithIdFlow(bikeId: Int): Flow<Bike>
    suspend fun getRideWithIdFlow(rideId: Int): Flow<Ride>
    suspend fun getBikeRidesFlow(bikeId: Int): Flow<List<Ride>>
    suspend fun getSettingsDistanceUnit(): DistanceUnit
    suspend fun getSettingsDefaultBikeId(): Int
    suspend fun getSettingsReminderDistance(): Distance

}