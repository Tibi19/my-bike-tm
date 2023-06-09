package com.tam.mybike.ui.screen.ride.add

import androidx.compose.runtime.Composable
import com.tam.mybike.ui.screen.ride.form.RideFormEvent
import com.tam.mybike.ui.screen.ride.form.RideFormScreen
import com.tam.mybike.ui.screen.ride.form.RideFormState
import com.tam.mybike.ui.theme.TEXT_ADD_RIDE
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AddRideScreen(
    stateFlow: StateFlow<RideFormState>,
    onEvent: (RideFormEvent) -> Unit,
    goToPreviousScreen: () -> Unit
) =
    RideFormScreen(
        screenTitle = TEXT_ADD_RIDE,
        confirmText = TEXT_ADD_RIDE,
        stateFlow = stateFlow,
        onEvent = onEvent,
        goToPreviousScreen = goToPreviousScreen
    )