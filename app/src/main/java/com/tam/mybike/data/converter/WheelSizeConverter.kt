package com.tam.mybike.data.converter

import androidx.room.TypeConverter
import com.tam.mybike.domain.model.WheelSize

class WheelSizeConverter {

    @TypeConverter
    fun stringToWheelSize(wheelSizeString: String): WheelSize =
        WheelSize.valueOf(wheelSizeString)

    @TypeConverter
    fun wheelSizeToString(wheelSize: WheelSize): String =
        wheelSize.name

}