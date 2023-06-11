package com.tam.mybike.ui.screen.bike.form

import androidx.compose.ui.graphics.Color
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.theme.Amber

data class BikeFormState(
    val selectedColor: Color = Amber,
    val selectedBikeType: BikeType = BikeType.ROADBIKE,
    val bikeName: String = "",
    val wheelSize: WheelSize = WheelSize.BIG,
    val distanceUnit: DistanceUnit = DistanceUnit.KM,
    val serviceIn: Distance? = null,
    val isDefaultBike: Boolean = false
)
