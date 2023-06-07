package com.tam.mybike.domain.repository

import com.tam.mybike.domain.model.DistanceUnit

interface Repository {

    fun getSettingsDistanceUnit(): DistanceUnit

}