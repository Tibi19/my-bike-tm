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
    fun getBikesFlow(): Flow<List<Bike>>
    fun getRidesFlow(): Flow<List<Ride>>
    fun getBikeWithIdFlow(bikeId: Int): Flow<Bike>
    fun getRideWithIdFlow(rideId: Int): Flow<Ride>
    fun getBikeRidesFlow(bikeId: Int): Flow<List<Ride>>
    fun saveSettingsDistanceUnit(distanceUnit: DistanceUnit)
    fun saveSettingsDefaultBikeId(bikeId: Int)
    fun saveSettingsReminderDistance(distance: Distance)
    fun saveSettingsIsReminderOn(isReminderOn: Boolean)
    fun getSettingsDistanceUnit(): DistanceUnit
    fun getSettingsDefaultBikeId(): Int
    fun getSettingsReminderDistance(): Distance
    fun getSettingsIsReminderOn(): Boolean

}