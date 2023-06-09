package com.tam.mybike.ui.screen.ride.form

import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance

sealed class RideFormEvent {
    data class OnChangeTitle(val newTitle: String) : RideFormEvent()
    data class OnChangeBike(val newBike: Bike) : RideFormEvent()
    data class OnChangeDistance(val newDistance: Distance?) : RideFormEvent()
    data class OnChangeDuration(val newDuration: Int?) : RideFormEvent()
    data class OnChangeDate(val newDate: Long) : RideFormEvent()
    object OnConfirmForm : RideFormEvent()
}
