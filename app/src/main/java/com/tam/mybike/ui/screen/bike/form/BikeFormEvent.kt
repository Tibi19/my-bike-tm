package com.tam.mybike.ui.screen.bike.form

import androidx.compose.ui.graphics.Color
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.WheelSize

sealed class BikeFormEvent {
    data class OnColorChange(val newColor: Color) : BikeFormEvent()
    data class OnBikeTypeChange(val newBikeType: BikeType) : BikeFormEvent()
    data class OnBikeNameChange(val newName: String) : BikeFormEvent()
    data class OnWheelSizeChange(val newWheelSize: WheelSize) : BikeFormEvent()
    data class OnServiceInChange(val newServiceIn: Distance?) : BikeFormEvent()
    object OnSwitchIsDefaultBike : BikeFormEvent()
    object OnConfirmForm : BikeFormEvent()
}
