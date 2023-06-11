package com.tam.mybike.data

import com.tam.mybike.data.source.DatabaseSource
import com.tam.mybike.data.source.SettingsDataSource
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val databaseSource: DatabaseSource,
    private val settingsSource: SettingsDataSource,
) : Repository {

    override suspend fun insertBike(bike: Bike) {
        databaseSource.insertBike(bike)
    }

    override suspend fun insertRide(ride: Ride) {
        databaseSource.insertRide(ride)
    }

    override suspend fun updateBike(bike: Bike) {
        databaseSource.updateBike(bike)
    }

    override suspend fun updateRide(ride: Ride) {
        databaseSource.updateRide(ride)
    }

    override suspend fun deleteBike(bikeId: Int) {
        databaseSource.deleteBike(bikeId)
    }

    override suspend fun deleteRide(rideId: Int) {
        databaseSource.deleteRide(rideId)
    }

    override fun getBikesFlow(): Flow<List<Bike>> =
        databaseSource.getBikesFlow()

    override fun getRidesFlow(): Flow<List<Ride>> =
        databaseSource.getRidesFlow()

    override fun getBikeWithIdFlow(bikeId: Int): Flow<Bike?> =
        databaseSource.getBikeWithIdFlow(bikeId)

    override fun getRideWithIdFlow(rideId: Int): Flow<Ride> =
        databaseSource.getRideWithIdFlow(rideId)

    override fun getBikeRidesFlow(bikeId: Int): Flow<List<Ride>> =
        databaseSource.getBikeRidesFlow(bikeId)

    override fun saveSettingsDistanceUnit(distanceUnit: DistanceUnit) {
        settingsSource.saveDistanceUnit(distanceUnit)
    }

    override fun saveSettingsDefaultBikeId(bikeId: Int) {
        settingsSource.saveDefaultBikeId(bikeId)
    }

    override fun saveSettingsReminderDistance(distance: Distance) {
        settingsSource.saveReminderDistance(distance)
    }

    override fun saveSettingsIsReminderOn(isReminderOn: Boolean) {
        settingsSource.saveIsReminderOn(isReminderOn)
    }

    override fun getSettingsDistanceUnit(): DistanceUnit =
        settingsSource.getDistanceUnit()

    override fun getSettingsDefaultBikeId(): Int =
        settingsSource.getDefaultBikeId()

    override fun getSettingsReminderDistance(): Distance =
        settingsSource.getReminderDistance()

    override fun getSettingsIsReminderOn(): Boolean =
        settingsSource.getIsReminderOn()

}