package com.tam.mybike.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tam.mybike.data.converter.BikeTypeConverter
import com.tam.mybike.data.converter.DistanceUnitConverter
import com.tam.mybike.data.converter.WheelSizeConverter
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize

@Entity(tableName = "t_bikes")
@TypeConverters(
    BikeTypeConverter::class,
    WheelSizeConverter::class,
    DistanceUnitConverter::class
)
data class BikeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: BikeType,
    val wheelSize: WheelSize,
    val colorHex: Long,
    val serviceInAmount: Int,
    val distanceUnit: DistanceUnit
)
