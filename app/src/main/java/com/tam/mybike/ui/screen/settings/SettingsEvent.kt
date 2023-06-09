package com.tam.mybike.ui.screen.settings

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit

sealed class SettingsEvent {
    data class OnDistanceUnitChange(val newUnit: DistanceUnit): SettingsEvent()
    data class OnReminderDistanceChange(val newDistance: Distance): SettingsEvent()
    object OnSwitchIsReminderOn: SettingsEvent()
    data class OnDefaultBikeChange(val newBike: Bike): SettingsEvent()
}
