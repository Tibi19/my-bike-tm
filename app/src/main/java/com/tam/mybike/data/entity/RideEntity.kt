package com.tam.mybike.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tam.mybike.data.converter.DistanceUnitConverter
import com.tam.mybike.domain.model.DistanceUnit

@Entity(tableName = "t_rides")
@TypeConverters(DistanceUnitConverter::class)
data class RideEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @Embedded(prefix = "bike_")
    val bike: BikeEntity,
    val distanceAmount: Int,
    val distanceUnit: DistanceUnit,
    val minutes: Int,
    val dateMillis: Long
)