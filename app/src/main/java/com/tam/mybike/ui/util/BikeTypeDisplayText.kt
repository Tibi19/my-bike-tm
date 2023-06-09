package com.tam.mybike.ui.util

import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.ui.theme.TEXT_BIKE_TYPE_ELECTRIC
import com.tam.mybike.ui.theme.TEXT_BIKE_TYPE_HYBRID
import com.tam.mybike.ui.theme.TEXT_BIKE_TYPE_MTB
import com.tam.mybike.ui.theme.TEXT_BIKE_TYPE_ROADBIKE

val BikeType.text
    get() = when(this) {
        BikeType.ROADBIKE -> TEXT_BIKE_TYPE_ROADBIKE
        BikeType.ELECTRIC -> TEXT_BIKE_TYPE_ELECTRIC
        BikeType.HYBRID -> TEXT_BIKE_TYPE_HYBRID
        BikeType.MTB -> TEXT_BIKE_TYPE_MTB
    }