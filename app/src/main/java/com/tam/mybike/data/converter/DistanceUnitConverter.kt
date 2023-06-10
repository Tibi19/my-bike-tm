package com.tam.mybike.data.converter

import androidx.room.TypeConverter
import com.tam.mybike.domain.model.DistanceUnit

class DistanceUnitConverter {

    @TypeConverter
    fun stringToDistanceUnit(unitString: String): DistanceUnit =
        DistanceUnit.valueOf(unitString)

    @TypeConverter
    fun distanceUnitToString(unit: DistanceUnit): String =
        unit.name

}