package com.tam.mybike.ui.screen.ride.form

import androidx.lifecycle.ViewModel
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Distance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class RideFormViewModel : ViewModel() {

    protected val mutableState = MutableStateFlow(RideFormState())
    val state = mutableState.asStateFlow()

    protected fun loadBikes() {
        TODO("Not yet implemented")
    }

    protected fun loadSettingsUnit() {
        TODO("Not yet implemented")
    }

    protected abstract fun confirmForm()

    fun onEvent(event: RideFormEvent) =
        when (event) {
            is RideFormEvent.OnChangeBike -> changeBike(event.newBike)
            is RideFormEvent.OnChangeDate -> changeDate(event.newDate)
            is RideFormEvent.OnChangeDistance -> changeDistance(event.newDistance)
            is RideFormEvent.OnChangeDuration -> changeDuration(event.newDuration)
            is RideFormEvent.OnChangeTitle -> changeTitle(event.newTitle)
            is RideFormEvent.OnConfirmForm -> confirmForm()
        }

    private fun changeBike(newBike: Bike) {
        mutableState.update {
            it.copy(selectedBike = newBike)
        }
    }

    private fun changeDate(newDateMillis: Long) {
        mutableState.update {
            it.copy(dateMillis = newDateMillis)
        }
    }

    private fun changeDistance(newDistance: Distance?) {
        mutableState.update {
            it.copy(distance = newDistance)
        }
    }

    private fun changeDuration(newDuration: Int?) {
        mutableState.update {
            it.copy(durationMinutes = newDuration)
        }
    }

    private fun changeTitle(newTitle: String) {
        mutableState.update {
            it.copy(title = newTitle)
        }
    }

}
