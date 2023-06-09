package com.tam.mybike.ui.screen.ride.edit

import androidx.compose.runtime.Composable
import com.tam.mybike.ui.screen.ride.form.RideFormEvent
import com.tam.mybike.ui.screen.ride.form.RideFormScreen
import com.tam.mybike.ui.screen.ride.form.RideFormState
import com.tam.mybike.ui.theme.TEXT_EDIT_RIDE
import com.tam.mybike.ui.theme.TEXT_SAVE
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EditRideScreen(
    stateFlow: StateFlow<RideFormState>,
    onEvent: (RideFormEvent) -> Unit,
    goToPreviousScreen: () -> Unit
) =
    RideFormScreen(
        screenTitle = TEXT_EDIT_RIDE,
        confirmText = TEXT_SAVE,
        stateFlow = stateFlow,
        onEvent = onEvent,
        goToPreviousScreen = goToPreviousScreen
    )