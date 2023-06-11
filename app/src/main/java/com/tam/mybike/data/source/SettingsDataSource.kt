package com.tam.mybike.data.source

import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit

interface SettingsDataSource {

    fun saveDistanceUnit(distanceUnit: DistanceUnit)
    fun saveDefaultBikeId(bikeId: Int)
    fun saveReminderDistance(distance: Distance)
    fun saveIsReminderOn(isReminderOn: Boolean)
    fun getDistanceUnit(): DistanceUnit
    fun getDefaultBikeId(): Int
    fun getReminderDistance(): Distance
    fun getIsReminderOn(): Boolean

}