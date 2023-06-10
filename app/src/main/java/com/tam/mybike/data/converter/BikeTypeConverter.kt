package com.tam.mybike.data.converter

import androidx.room.TypeConverter
import com.tam.mybike.domain.model.BikeType

class BikeTypeConverter {

    @TypeConverter
    fun stringToBikeType(bikeTypeString: String): BikeType =
        BikeType.valueOf(bikeTypeString)

    @TypeConverter
    fun bikeTypeToString(bikeType: BikeType): String =
        bikeType.name

}