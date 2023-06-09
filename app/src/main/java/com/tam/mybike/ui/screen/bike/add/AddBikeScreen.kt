package com.tam.mybike.ui.screen.bike.add

import androidx.compose.runtime.Composable
import com.tam.mybike.ui.screen.bike.form.BikeFormEvent
import com.tam.mybike.ui.screen.bike.form.BikeFormScreen
import com.tam.mybike.ui.screen.bike.form.BikeFormState
import com.tam.mybike.ui.theme.TEXT_ADD_BIKE
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AddBikeScreen(
    stateFlow: StateFlow<BikeFormState>,
    onEvent: (BikeFormEvent) -> Unit,
    goToPreviousScreen: () -> Unit
) =
    BikeFormScreen(
        screenTitle = TEXT_ADD_BIKE,
        confirmText = TEXT_ADD_BIKE,
        stateFlow = stateFlow,
        onEvent = onEvent,
        goToPreviousScreen = goToPreviousScreen
    )