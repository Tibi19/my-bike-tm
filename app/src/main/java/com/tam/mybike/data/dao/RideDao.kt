package com.tam.mybike.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tam.mybike.data.entity.RideEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RideDao {

    @Insert
    suspend fun insertRide(rideEntity: RideEntity)

    @Update
    suspend fun updateRide(rideEntity: RideEntity)

    @Query("DELETE FROM t_rides WHERE id = :rideId")
    suspend fun deleteRide(rideId: Int)

    @Query("SELECT * FROM t_rides WHERE id = :rideId")
    fun getRideWithId(rideId: Int): Flow<RideEntity>

    @Query("SELECT * FROM t_rides")
    fun getRides(): Flow<List<RideEntity>>

    @Query("SELECT * FROM t_rides WHERE bike_id = :bikeId")
    fun getBikeRides(bikeId: Int): Flow<List<RideEntity>>

}