package com.tam.mybike.ui.screen.bike.form

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.WheelSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BikeFormViewModel : ViewModel() {

    protected val mutableState = MutableStateFlow(BikeFormState())
    val state = mutableState.asStateFlow()

    protected fun loadSettingsUnit() {
        TODO("Not yet implemented")
    }

    abstract fun confirmForm()

    fun onEvent(event: BikeFormEvent) =
        when (event) {
            is BikeFormEvent.OnColorChange -> changeColor(event.newColor)
            is BikeFormEvent.OnBikeTypeChange -> changeBikeType(event.newBikeType)
            is BikeFormEvent.OnBikeNameChange -> changeBikeName(event.newName)
            is BikeFormEvent.OnServiceInChange -> changeServiceIn(event.newServiceIn)
            is BikeFormEvent.OnWheelSizeChange -> changeWheelSize(event.newWheelSize)
            is BikeFormEvent.OnSwitchIsDefaultBike -> switchIsDefaultBike()
            is BikeFormEvent.OnConfirmForm -> confirmForm()
        }

    private fun changeColor(newColor: Color) {
        mutableState.update {
            it.copy(selectedColor = newColor)
        }
    }

    private fun changeBikeType(newBikeType: BikeType) {
        mutableState.update {
            it.copy(selectedBikeType = newBikeType)
        }
    }

    private fun changeBikeName(newBikeName: String) {
        mutableState.update {
            it.copy(bikeName = newBikeName)
        }
    }

    private fun changeWheelSize(newWheelSize: WheelSize) {
        mutableState.update {
            it.copy(wheelSize = newWheelSize)
        }
    }

    private fun changeServiceIn(newServiceIn: Distance?) {
        mutableState.update {
            it.copy(serviceIn = newServiceIn)
        }
    }

    private fun switchIsDefaultBike() {
        mutableState.update {
            it.copy(isDefaultBike = !it.isDefaultBike)
        }
    }

}