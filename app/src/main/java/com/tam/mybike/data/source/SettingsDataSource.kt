package com.tam.mybike.data.source

import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit

interface SettingsDataSource {

    fun saveSettingsDistanceUnit(distanceUnit: DistanceUnit)
    fun saveSettingsDefaultBikeId(bikeId: Int)
    fun saveSettingsReminderDistance(distance: Distance)
    fun getSettingsDistanceUnit(): DistanceUnit
    fun getSettingsDefaultBikeId(): Int
    fun getSettingsReminderDistance(): Distance

}