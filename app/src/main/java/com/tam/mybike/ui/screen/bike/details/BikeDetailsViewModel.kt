package com.tam.mybike.ui.screen.bike.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.usecase.collect.CollectBikeRidesUseCase
import com.tam.mybike.domain.usecase.collect.CollectBikeWithIdUseCase
import com.tam.mybike.domain.usecase.delete.DeleteBikeUseCase
import com.tam.mybike.domain.usecase.delete.DeleteRideUseCase
import com.tam.mybike.domain.usecase.get.GetBikeProgressUseCase
import com.tam.mybike.domain.usecase.get.GetBikeWithSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetRideWithSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetRidesTotalDistanceUseCase
import com.tam.mybike.ui.navigation.ARG_BIKE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val collectBike: CollectBikeWithIdUseCase,
    private val collectBikeRides: CollectBikeRidesUseCase,
    private val getBikeWithSettingsUnit: GetBikeWithSettingsUnitUseCase,
    private val getRideWithSettingsUnit: GetRideWithSettingsUnitUseCase,
    private val getBikeProgress: GetBikeProgressUseCase,
    private val getRidesTotalDistance: GetRidesTotalDistanceUseCase,
    private val getSettingsUnit: GetSettingsUnitUseCase,
    private val deleteBike: DeleteBikeUseCase,
    private val deleteRide: DeleteRideUseCase
) : ViewModel() {

    private val mutableState = MutableStateFlow(BikeDetailsState())
    val state = mutableState.asStateFlow()

    init {
        loadSettingsUnit()
        savedStateHandle
            .get<Int>(ARG_BIKE_ID)
            ?.let { bikeId ->
                observeBike(bikeId)
                observeBikeRides(bikeId)
            }
    }

    private fun loadSettingsUnit() {
        mutableState.update {
            it.copy(distanceUnit = getSettingsUnit())
        }
    }

    private fun observeBike(bikeId: Int) =
        viewModelScope.launch {
            collectBike(bikeId) { bike ->
                val bikeWithSettingsUnit = getBikeWithSettingsUnit(bike)
                updateBike(bikeWithSettingsUnit)
                updateProgress(bikeWithSettingsUnit)
            }
        }

    private fun updateBike(bike: Bike) {
        mutableState.update {
            it.copy(bike = bike)
        }
    }

    private fun updateProgress(bike: Bike) {
        mutableState.update {
            it.copy(progress = getBikeProgress(bike))
        }
    }

    private fun observeBikeRides(bikeId: Int) =
        viewModelScope.launch {
            collectBikeRides(bikeId) { rides ->
                val ridesWithSettingsUnit = rides.map { ride ->
                    getRideWithSettingsUnit(ride)
                }
                updateRides(ridesWithSettingsUnit)
                updateRidesTotalDistance(ridesWithSettingsUnit)
            }
        }

    private fun updateRides(rides: List<Ride>) {
        mutableState.update {
            it.copy(rides = rides)
        }
    }

    private fun updateRidesTotalDistance(rides: List<Ride>) {
        val totalRidesDistance = getRidesTotalDistance(rides, mutableState.value.distanceUnit)
        mutableState.update {
            it.copy(totalRidesDistance = totalRidesDistance)
        }
    }

    fun onEvent(event: BikeDetailsEvent) {
        when (event) {
            is BikeDetailsEvent.OnBikeDelete -> removeBike(event.bikeId)
            is BikeDetailsEvent.OnRideDelete -> removeRide(event.rideId)
        }
    }

    private fun removeBike(bikeId: Int) =
        viewModelScope.launch {
            deleteBike(bikeId)
        }

    private fun removeRide(rideId: Int?) {
        rideId ?: return
        viewModelScope.launch {
            deleteRide(rideId)
        }
    }

}

