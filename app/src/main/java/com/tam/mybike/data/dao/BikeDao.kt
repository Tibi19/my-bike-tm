package com.tam.mybike.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tam.mybike.data.entity.BikeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeDao {

    @Insert
    suspend fun insertBike(bikeEntity: BikeEntity)

    @Update
    suspend fun updateBike(bikeEntity: BikeEntity)

    @Query("DELETE FROM t_bikes WHERE id = :bikeId")
    suspend fun deleteBike(bikeId: Int)

    @Query("SELECT * FROM t_bikes WHERE id = :bikeId")
    fun getBikeWithId(bikeId: Int): Flow<BikeEntity>

    @Query("SELECT * FROM t_bikes")
    fun getBikes(): Flow<List<BikeEntity>>

}