package com.tam.mybike.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tam.mybike.data.dao.BikeDao
import com.tam.mybike.data.dao.RideDao
import com.tam.mybike.data.entity.BikeEntity
import com.tam.mybike.data.entity.RideEntity

const val DATABASE_NAME = "database_my_bike"

@Database(
    entities = [
        BikeEntity::class,
        RideEntity::class
    ],
    version = 1
)
abstract class MyBikeDatabase : RoomDatabase() {
    abstract val bikeDao: BikeDao
    abstract val rideDao: RideDao
}