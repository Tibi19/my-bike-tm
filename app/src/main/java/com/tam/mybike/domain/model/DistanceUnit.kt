package com.tam.mybike.domain.model

enum class DistanceUnit {
    KM,
    MI;

    companion object {
        val DEFAULT = DistanceUnit.KM
    }
}