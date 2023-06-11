package com.tam.mybike.data.source

import com.tam.mybike.data.dao.BikeDao
import com.tam.mybike.data.dao.RideDao
import com.tam.mybike.data.mapper.toBike
import com.tam.mybike.data.mapper.toBikeEntity
import com.tam.mybike.data.mapper.toBikes
import com.tam.mybike.data.mapper.toRide
import com.tam.mybike.data.mapper.toRideEntity
import com.tam.mybike.data.mapper.toRides
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Ride
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDataSource @Inject constructor(
    private val bikeDao: BikeDao,
    private val rideDao: RideDao
) : DatabaseSource {

    override suspend fun insertBike(bike: Bike) {
        bikeDao.insertBike(bike.toBikeEntity())
    }

    override suspend fun insertRide(ride: Ride) {
        rideDao.insertRide(ride.toRideEntity())
    }

    override suspend fun updateBike(bike: Bike) {
        bikeDao.updateBike(bike.toBikeEntity())
    }

    override suspend fun updateRide(ride: Ride) {
        rideDao.updateRide(ride.toRideEntity())
    }

    override suspend fun deleteBike(bikeId: Int) {
        bikeDao.deleteBike(bikeId)
    }

    override suspend fun deleteRide(rideId: Int) {
        rideDao.deleteRide(rideId)
    }

    override fun getBikesFlow(): Flow<List<Bike>> =
        bikeDao
            .getBikes()
            .map { bikeEntities ->
                bikeEntities.toBikes()
            }

    override fun getRidesFlow(): Flow<List<Ride>> =
        rideDao
            .getRides()
            .map { rideEntities ->
                rideEntities.toRides()
            }

    override fun getBikeWithIdFlow(bikeId: Int): Flow<Bike?> =
        bikeDao
            .getBikeWithId(bikeId)
            .map { bikeEntity ->
                bikeEntity?.toBike()
            }

    override fun getRideWithIdFlow(rideId: Int): Flow<Ride> =
        rideDao
            .getRideWithId(rideId)
            .map { rideEntity ->
                rideEntity.toRide()
            }

    override fun getBikeRidesFlow(bikeId: Int): Flow<List<Ride>> =
        rideDao
            .getBikeRides(bikeId)
            .map { rideEntities ->
                rideEntities.toRides()
            }

}
