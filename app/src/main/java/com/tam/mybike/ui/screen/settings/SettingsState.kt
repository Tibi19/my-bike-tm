package com.tam.mybike.ui.screen.settings

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit

data class SettingsState(
    val distanceUnit: DistanceUnit = DistanceUnit.DEFAULT,
    val reminderDistance: Distance = Distance(0, distanceUnit),
    val isReminderOn: Boolean = false,
    val defaultBike: Bike? = null,
    val bikes: List<Bike> = emptyList()
)
