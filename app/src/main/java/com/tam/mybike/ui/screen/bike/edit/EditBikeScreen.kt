package com.tam.mybike.ui.screen.bike.edit

import androidx.compose.runtime.Composable
import com.tam.mybike.ui.screen.bike.form.BikeFormEvent
import com.tam.mybike.ui.screen.bike.form.BikeFormScreen
import com.tam.mybike.ui.screen.bike.form.BikeFormState
import com.tam.mybike.ui.theme.TEXT_ADD_BIKE
import com.tam.mybike.ui.theme.TEXT_EDIT_BIKE
import com.tam.mybike.ui.theme.TEXT_SAVE
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EditBikeScreen(
    stateFlow: StateFlow<BikeFormState>,
    onEvent: (BikeFormEvent) -> Unit,
    goToPreviousScreen: () -> Unit
) =
    BikeFormScreen(
        screenTitle = TEXT_EDIT_BIKE,
        confirmText = TEXT_SAVE,
        stateFlow = stateFlow,
        onEvent = onEvent,
        goToPreviousScreen = goToPreviousScreen
    )