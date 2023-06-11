package com.tam.mybike.ui.screen.ride.add

import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.usecase.collect.CollectBikesUseCase
import com.tam.mybike.domain.usecase.collect.CollectDefaultBikeUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import com.tam.mybike.domain.usecase.insert.InsertRideUseCase
import com.tam.mybike.ui.screen.ride.form.RideFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRideViewModel @Inject constructor(
    collectBikes: CollectBikesUseCase,
    getSettingsUnit: GetSettingsUnitUseCase,
    private val collectDefaultBike: CollectDefaultBikeUseCase,
    private val insertRide: InsertRideUseCase
) : RideFormViewModel(
    collectBikes,
    getSettingsUnit
) {

    init {
        loadSettingsUnit()
        loadSelectedBike()
        observeBikes()
    }

    private fun loadSelectedBike() =
        viewModelScope.launch {
            collectDefaultBike { bike ->
                updateSelectedBike(bike)
                this.coroutineContext.job.cancel()
            }
        }

    private fun updateSelectedBike(bike: Bike) {
        mutableState.update {
            it.copy(selectedBike = bike)
        }
    }

    override fun confirmForm(fallbackTitle: String) {
        val ride = with(mutableState.value) {
            val bike = selectedBike ?: return
            val distance = distance ?: return
            val minutes = durationMinutes ?: return
            Ride(
                name = title.ifEmpty { fallbackTitle },
                bike = bike,
                distance = distance,
                minutes = minutes,
                dateMillis = dateMillis
            )
        }
        addRide(ride)
    }

    private fun addRide(ride: Ride) =
        viewModelScope.launch {
            insertRide(ride)
        }

}