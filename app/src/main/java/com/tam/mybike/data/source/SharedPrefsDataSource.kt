package com.tam.mybike.data.source

import android.app.Application
import android.content.Context
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val PREFERENCES_FILE_NAME = "com.tam.mybike.SHARED_PREFERENCES"
private const val KEY_DISTANCE_UNIT = "key_unit"
private const val KEY_DEFAULT_BIKE_ID = "key_default_bike"
private const val KEY_REMINDER_DISTANCE_AMOUNT = "key_reminder_distance_amount"
private const val KEY_REMINDER_DISTANCE_UNIT = "key_reminder_distance_unit"
private const val DEFAULT_VALUE_DEFAULT_BIKE_ID = -1
private const val DEFAULT_VALUE_REMINDER_DISTANCE_AMOUNT = 0

@Singleton
class SharedPrefsDataSource @Inject constructor(
    application: Application
) : SettingsDataSource {

    private val sharedPrefs = application
        .getSharedPreferences(
            PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )

    override fun saveSettingsDistanceUnit(distanceUnit: DistanceUnit) {
        with(sharedPrefs.edit()) {
            putString(KEY_DISTANCE_UNIT, distanceUnit.name)
            apply()
        }
    }

    override fun saveSettingsDefaultBikeId(bikeId: Int) {
        with(sharedPrefs.edit()) {
            putInt(KEY_DEFAULT_BIKE_ID, bikeId)
            apply()
        }
    }

    override fun saveSettingsReminderDistance(distance: Distance) {
        with(sharedPrefs.edit()) {
            putInt(KEY_REMINDER_DISTANCE_AMOUNT, distance.amount)
            putString(KEY_REMINDER_DISTANCE_UNIT, distance.unit.name)
            apply()
        }
    }

    override fun getSettingsDistanceUnit(): DistanceUnit {
        val distanceUnitName = sharedPrefs.getString(KEY_DISTANCE_UNIT, null)
            ?: return DistanceUnit.DEFAULT
        return DistanceUnit.valueOf(distanceUnitName)
    }

    override fun getSettingsDefaultBikeId(): Int =
        sharedPrefs.getInt(KEY_DEFAULT_BIKE_ID, DEFAULT_VALUE_DEFAULT_BIKE_ID)

    override fun getSettingsReminderDistance(): Distance {
        val reminderDistanceAmount = sharedPrefs.getInt(KEY_REMINDER_DISTANCE_AMOUNT, DEFAULT_VALUE_REMINDER_DISTANCE_AMOUNT)
        val reminderDistanceUnitName = sharedPrefs.getString(KEY_REMINDER_DISTANCE_UNIT, null)
            ?: return Distance(reminderDistanceAmount, DistanceUnit.DEFAULT)
        val reminderDistanceUnit = DistanceUnit.valueOf(reminderDistanceUnitName)
        return Distance(reminderDistanceAmount, reminderDistanceUnit)
    }

}