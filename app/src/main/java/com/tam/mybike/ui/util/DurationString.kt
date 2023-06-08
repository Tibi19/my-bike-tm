package com.tam.mybike.ui.util

import com.tam.mybike.ui.theme.TEXT_DURATION_DESCRIPTION_HOURS
import com.tam.mybike.ui.theme.TEXT_DURATION_DESCRIPTION_MINUTES

private const val MINUTES_IN_HOUR = 60

fun Int.toDurationString(): String {
    val minutes = this % MINUTES_IN_HOUR
    val hours = this / MINUTES_IN_HOUR
    return getStringFromDuration(hours, minutes)
}

fun getStringFromDuration(hours: Int, minutes: Int) =
    "$hours$TEXT_DURATION_DESCRIPTION_HOURS$minutes$TEXT_DURATION_DESCRIPTION_MINUTES"