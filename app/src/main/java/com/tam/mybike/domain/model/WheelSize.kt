package com.tam.mybike.domain.model

private const val INCHES_SMALL = 28
private const val INCHES_BIG = 29

enum class WheelSize(val inches: Int) {
    SMALL(INCHES_SMALL),
    BIG(INCHES_BIG)
}