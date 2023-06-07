package com.tam.mybike.ui.util

import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.ui.theme.TEXT_KM_UNIT
import com.tam.mybike.ui.theme.TEXT_MI_UNIT

val DistanceUnit.text
    get() = when(this) {
        DistanceUnit.KM -> TEXT_KM_UNIT
        DistanceUnit.MI -> TEXT_MI_UNIT
    }