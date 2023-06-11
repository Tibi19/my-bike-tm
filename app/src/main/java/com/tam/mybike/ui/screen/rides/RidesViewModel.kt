package com.tam.mybike.ui.screen.rides

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.usecase.collect.CollectRidesUseCase
import com.tam.mybike.domain.usecase.delete.DeleteRideUseCase
import com.tam.mybike.domain.usecase.get.GetRideWithSettingsUnitUseCase
import com.tam.mybike.domain.usecase.get.GetRidesByMonthUseCase
import com.tam.mybike.domain.usecase.get.GetRidesDistanceByBikeTypeUseCase
import com.tam.mybike.domain.usecase.get.GetRidesTotalDistanceUseCase
import com.tam.mybike.domain.usecase.get.GetSettingsUnitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RidesViewModel @Inject constructor(
    private val collectRides: CollectRidesUseCase,
    private val getSettingsUnit: GetSettingsUnitUseCase,
    private val getRideWithSettingsUnit: GetRideWithSettingsUnitUseCase,
    private val getRidesByMonth: GetRidesByMonthUseCase,
    private val getRidesDistanceByBikeType: GetRidesDistanceByBikeTypeUseCase,
    private val getRidesTotalDistance: GetRidesTotalDistanceUseCase,
    private val deleteRide: DeleteRideUseCase
) : ViewModel() {

    private val mutableState = MutableStateFlow(RidesState())
    val state = mutableState.asStateFlow()

    init {
        loadSettingsUnit()
        observeRides()
    }

    private fun observeRides() =
        viewModelScope.launch {
            collectRides { rides ->
                val ridesWithSettingsUnit = rides.map { ride ->
                    getRideWithSettingsUnit(ride)
                }
                updateRidesByMonth(ridesWithSettingsUnit)
                updateBikeTypeToDistanceMap(ridesWithSettingsUnit)
                updateTotalDistance(ridesWithSettingsUnit)
            }
        }

    private fun updateTotalDistance(rides: List<Ride>) {
        val totalDistance = getRidesTotalDistance(rides, mutableState.value.distanceUnit)
        mutableState.update {
            it.copy(totalDistance = totalDistance)
        }
    }

    private fun updateRidesByMonth(rides: List<Ride>) {
        mutableState.update {
            it.copy(
                ridesByMonth = getRidesByMonth(rides)
            )
        }
    }

    private fun updateBikeTypeToDistanceMap(rides: List<Ride>) {
        val ridesDistanceByBikeType = getRidesDistanceByBikeType(rides, mutableState.value.distanceUnit)
        mutableState.update {
            it.copy(bikeTypeToDistanceMap = ridesDistanceByBikeType)
        }
    }

    private fun loadSettingsUnit() {
        mutableState.update {
            it.copy(distanceUnit = getSettingsUnit())
        }
    }

    fun onEvent(event: RidesEvent) {
        if (event !is RidesEvent.DeleteRide) return
        removeRide(event.rideId)
    }

    private fun removeRide(rideId: Int?) {
        rideId ?: return
        viewModelScope.launch {
            deleteRide(rideId)
        }
    }

}