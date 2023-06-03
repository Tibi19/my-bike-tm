package com.tam.mybike.ui.util

import com.tam.mybike.ui.theme.PATTERN_DATE
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDate(dateMillis: Long): String {
    val localDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(dateMillis),
        ZoneId.systemDefault()
    )
    val formatter = DateTimeFormatter.ofPattern(
        PATTERN_DATE,
        Locale.getDefault()
    )
    return localDateTime.format(formatter)
}