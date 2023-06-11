package com.tam.mybike.data.source

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Ride
import kotlinx.coroutines.flow.Flow

interface DatabaseSource {

    suspend fun insertBike(bike: Bike)
    suspend fun insertRide(ride: Ride)
    suspend fun updateBike(bike: Bike)
    suspend fun updateRide(ride: Ride)
    suspend fun deleteBike(bikeId: Int)
    suspend fun deleteRide(rideId: Int)
    fun getBikesFlow(): Flow<List<Bike>>
    fun getRidesFlow(): Flow<List<Ride>>
    fun getBikeWithIdFlow(bikeId: Int): Flow<Bike?>
    fun getRideWithIdFlow(rideId: Int): Flow<Ride>
    fun getBikeRidesFlow(bikeId: Int): Flow<List<Ride>>

}