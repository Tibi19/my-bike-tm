package com.tam.mybike.ui.screen.bike.add

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.ui.screen.bike.form.BikeFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBikeViewModel @Inject constructor() : BikeFormViewModel() {

    init {
        loadSettingsUnit()
    }

    override fun confirmForm() {
        val bike = with(mutableState.value) {
            Bike(
                name = bikeName,
                type = selectedBikeType,
                wheelSize = wheelSize,
                colorHex = selectedColor.value,
                serviceIn = serviceIn ?: Distance(0, distanceUnit)
            )
        }
        addBike(bike)
    }

    private fun addBike(bike: Bike) {
        TODO("Not yet implemented")
    }

}