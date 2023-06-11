package com.tam.mybike.ui.screen.ride.form

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tam.mybike.R
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.component.button.ConfirmButton
import com.tam.mybike.ui.component.field.ChoiceHolder
import com.tam.mybike.ui.component.field.DateField
import com.tam.mybike.ui.component.field.DropdownField
import com.tam.mybike.ui.component.field.DurationField
import com.tam.mybike.ui.component.field.DurationState
import com.tam.mybike.ui.component.field.InputField
import com.tam.mybike.ui.component.text.RowTitle
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_ADD_RIDE
import com.tam.mybike.ui.theme.TEXT_BIKE
import com.tam.mybike.ui.theme.TEXT_CLOSE_ICON_CONTENT
import com.tam.mybike.ui.theme.TEXT_DISTANCE
import com.tam.mybike.ui.theme.TEXT_RIDE
import com.tam.mybike.ui.theme.TEXT_RIDE_TITLE
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.util.suffix
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideFormScreen(
    screenTitle: String,
    confirmText: String,
    stateFlow: StateFlow<RideFormState>,
    onEvent: (RideFormEvent) -> Unit,
    goToPreviousScreen: () -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()
    val dateState = rememberDatePickerState(state.dateMillis)
    
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(top = PADDING_SMALL, bottom = PADDING_MEDIUM)
            .padding(horizontal = PADDING_MEDIUM)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = PADDING_LARGE)
        ) {
            RowTitle(text = screenTitle)
            Icon(
                painter = painterResource(id = R.drawable.icon_x),
                contentDescription = TEXT_CLOSE_ICON_CONTENT,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable { goToPreviousScreen() }
            )
        }
        
        InputField(
            value = state.title,
            onValueChange = { newTitle ->
                val changeTitleEvent = RideFormEvent.OnChangeTitle(newTitle)
                onEvent(changeTitleEvent)
            },
            label = TEXT_RIDE_TITLE,
            isRequired = false
        )
        
        DropdownField(
            selectedChoice = state.selectedBike?.let { bike ->
                ChoiceHolder(
                    key = bike.id.toString(),
                    text = bike.name
                )
            } ?: ChoiceHolder(key = "", text = ""),
            onChoiceChange = { newBikeChoice ->
                val newBike = state.bikes.find { bike -> 
                    bike.id == newBikeChoice.key.toInt()
                } ?: return@DropdownField
                val changeBikeEvent = RideFormEvent.OnChangeBike(newBike)
                onEvent(changeBikeEvent)
            },
            choices = state.bikes.map { bike ->
                ChoiceHolder(
                    key = bike.id.toString(),
                    text = bike.name
                )
            },
            label = TEXT_BIKE,
            dropdownHorizontalPadding = PADDING_MEDIUM
        )
        
        InputField(
            value = state.distance?.amount?.toString() ?: "",
            onValueChange = { newDistanceAmount ->
                val newDistance = if (newDistanceAmount.isNotEmpty()) {
                    Distance(newDistanceAmount.toInt(), state.distanceUnit)
                } else {
                    null
                }
                val distanceChangeEvent = RideFormEvent.OnChangeDistance(newDistance)
                onEvent(distanceChangeEvent)
            },
            label = TEXT_DISTANCE,
            trailingText = state.distanceUnit.suffix.uppercase(),
            isRequired = true
        )
        
        DurationField(
            duration = DurationState.fromMinutes(state.durationMinutes ?: 0),
            onDurationChange = { newDurationState ->
                val newDuration = newDurationState.toMinutes()
                val changeDurationEvent = RideFormEvent.OnChangeDuration(newDuration)
                onEvent(changeDurationEvent)
            }
        )

        DateField(
            dateState = dateState,
            onDateChange = { newDateMillis ->
                newDateMillis ?: return@DateField
                dateState.selectedDateMillis = newDateMillis
                val changeDateEvent = RideFormEvent.OnChangeDate(newDateMillis)
                onEvent(changeDateEvent)
            }
        )

        Spacer(modifier = Modifier.weight(WEIGHT_FILL))

        val isConfirmEnabled = state.selectedBike != null && state.distance != null && state.durationMinutes != null
        ConfirmButton(
            confirmText = confirmText,
            enabled = isConfirmEnabled,
            onClick = {
                val fallbackTitle = getFallbackTitle(state.dateMillis)
                val confirmEvent = RideFormEvent.OnConfirmForm(fallbackTitle)
                onEvent(confirmEvent)
                goToPreviousScreen()
            }
        )
    }
}

private fun getFallbackTitle(dateMillis: Long): String {
    val date = LocalDateTime
        .ofInstant(
            Instant.ofEpochMilli(dateMillis),
            ZoneId.systemDefault()
        )
    return "${date.dayOfWeek.name} ${date.dayOfMonth} $TEXT_RIDE"
}

@Preview
@Composable
private fun RideFormScreenPreview() =
    ScreenDarkPreview {
        val mockBikes = listOf(
            Bike(
                id = 1,
                name = "Nukeproof Scout 290",
                type = BikeType.MTB,
                wheelSize = WheelSize.BIG,
                colorHex = BikeColors[4].value,
                serviceIn = Distance(170, DistanceUnit.KM)
            ),
            Bike(
                id = 2,
                name = "E-Bike Cannondale",
                type = BikeType.ELECTRIC,
                wheelSize = WheelSize.BIG,
                colorHex = BikeColors[0].value,
                serviceIn = Distance(900, DistanceUnit.KM)
            ),
            Bike(
                id = 3,
                name = "Highroad Scout 220",
                type = BikeType.ROADBIKE,
                wheelSize = WheelSize.SMALL,
                colorHex = BikeColors[5].value,
                serviceIn = Distance(200, DistanceUnit.KM)
            )
        )
        val mockState = RideFormState(
            title = "Faget MTB Tour",
            selectedBike = mockBikes[0],
            bikes = mockBikes,
            distance = Distance(60, DistanceUnit.KM),
            durationMinutes = 134,
            dateMillis = 1680097200000L
        )
        val stateFlow = remember {
            MutableStateFlow(mockState).asStateFlow()
        }
        RideFormScreen(
            screenTitle = TEXT_ADD_RIDE,
            confirmText = TEXT_ADD_RIDE,
            stateFlow = stateFlow,
            onEvent = {},
            goToPreviousScreen = {}
        )
    }
