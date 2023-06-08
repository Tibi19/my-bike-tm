package com.tam.mybike.ui.util

import java.time.Month

fun Int.toMonthName(): String? {
    val isThisAMonth = this in Month.JANUARY.value..Month.DECEMBER.value
    if (!isThisAMonth) return null

    return Month.of(this).name
}