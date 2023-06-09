package com.tam.mybike.ui.screen.bike.edit

import androidx.lifecycle.SavedStateHandle
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.ui.screen.bike.form.BikeFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditBikeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BikeFormViewModel() {

    init {
        savedStateHandle.get<Int>("TODO////PLACEHOLDER_ID_KEY")?.let { bikeId ->
            loadBike(bikeId)
        }
        loadSettingsUnit()
    }

    private fun loadBike(bikeId: Int) {
        TODO("Not yet implemented")
    }

    override fun confirmForm() {
        val bikeId = savedStateHandle.get<Int>("TODO////PLACEHOLDER_ID_KEY") ?: return
        val bike = with(mutableState.value) {
            Bike(
                id = bikeId,
                name = bikeName,
                type = selectedBikeType,
                wheelSize = wheelSize,
                colorHex = selectedColor.value,
                serviceIn = serviceIn ?: Distance(0, distanceUnit)
            )
        }
        updateBike(bike)
    }

    private fun updateBike(bike: Bike) {
        TODO("Not yet implemented")
    }

}