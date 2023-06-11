package com.tam.mybike.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tam.mybike.R
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.component.button.DefaultSwitch
import com.tam.mybike.ui.component.field.ChoiceHolder
import com.tam.mybike.ui.component.field.DropdownField
import com.tam.mybike.ui.component.field.InputField
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.PATTERN_REGEX_LETTERS
import com.tam.mybike.ui.theme.SIZE_BIKE_CHOICE_ICON
import com.tam.mybike.ui.theme.TEXT_BIKE_CHOICE_ICON_CONTENT
import com.tam.mybike.ui.theme.TEXT_DEFAULT_BIKE
import com.tam.mybike.ui.theme.TEXT_DISTANCE_UNITS
import com.tam.mybike.ui.theme.TEXT_SERVICE_REMINDER
import com.tam.mybike.ui.theme.TEXT_SETTINGS
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.util.suffix
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SettingsScreen(
    stateFlow: StateFlow<SettingsState>,
    onEvent: (SettingsEvent) -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL)
            .fillMaxSize()
    ) {
        Title(
            text = TEXT_SETTINGS,
            modifier = Modifier.padding(bottom = PADDING_LARGE)
        )

        DropdownField(
            selectedChoice = ChoiceHolder(
                key = state.distanceUnit.name,
                text = state.distanceUnit.suffix.uppercase()
            ),
            onChoiceChange = { newChoice ->
                val newDistanceUnit = DistanceUnit.valueOf(newChoice.key)
                val distanceUnitChangeEvent = SettingsEvent.OnDistanceUnitChange(newDistanceUnit)
                onEvent(distanceUnitChangeEvent)
            },
            choices = DistanceUnit.values().map { unit ->
                ChoiceHolder(
                    key = unit.name,
                    text = unit.name
                )
            },
            label = TEXT_DISTANCE_UNITS,
            dropdownHorizontalPadding = PADDING_MEDIUM,
            modifier = Modifier.padding(bottom = PADDING_X_SMALL)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = PADDING_X_SMALL)
        ) {
            InputField(
                value = "${state.reminderDistance.amount}${state.reminderDistance.unit.suffix}",
                onValueChange = { newValue ->
                    val newValueOnlyDigits = newValue
                        .replace(PATTERN_REGEX_LETTERS.toRegex(), "")
                    val newValueInt = if (newValueOnlyDigits.isEmpty()) 0 else newValueOnlyDigits.toInt()
                    val newDistance = Distance(newValueInt, state.reminderDistance.unit)
                    val reminderDistanceChangeEvent = SettingsEvent.OnReminderDistanceChange(newDistance)
                    onEvent(reminderDistanceChangeEvent)
                },
                keyboardOptions = KeyboardOptions.Default
                    .copy(keyboardType = KeyboardType.Number),
                label = TEXT_SERVICE_REMINDER,
                isRequired = false,
                modifier = Modifier
                    .weight(WEIGHT_FILL)
            )
            DefaultSwitch(
                isOn = state.isReminderOn,
                onSwitch = {
                    val switchIsReminderOnEvent = SettingsEvent.OnSwitchIsReminderOn
                    onEvent(switchIsReminderOnEvent)
                },
                modifier = Modifier.padding(start = PADDING_MEDIUM, end = PADDING_X_SMALL)
            )
        }

        DropdownField(
            selectedChoice = state.defaultBike?.let { bike ->
                ChoiceHolder(
                    key = bike.id.toString(),
                    text = bike.name
                )
            } ?: ChoiceHolder(key = "", text = ""),
            onChoiceChange = { newChoice ->
                val newDefaultBike = state.bikes.find { bike ->
                    bike.id == newChoice.key.toInt()
                } ?: return@DropdownField
                val changeDefaultBikeEvent = SettingsEvent.OnDefaultBikeChange(newDefaultBike)
                onEvent(changeDefaultBikeEvent)
            },
            choices = state.bikes.map { bike ->
                ChoiceHolder(
                    key = bike.id.toString(),
                    text = bike.name
                )
            },
            dropdownItemIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.bike_placeholder),
                    contentDescription = TEXT_BIKE_CHOICE_ICON_CONTENT,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(SIZE_BIKE_CHOICE_ICON)
                )
            },
            label = TEXT_DEFAULT_BIKE,
            dropdownHorizontalPadding = PADDING_MEDIUM,
            modifier = Modifier.padding(bottom = PADDING_X_SMALL)
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() =
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
        val mockState = SettingsState(
            distanceUnit = DistanceUnit.KM,
            reminderDistance = Distance(100, DistanceUnit.KM),
            isReminderOn = true,
            defaultBike = mockBikes[0],
            bikes = mockBikes
        )
        val stateFlow = remember {
            MutableStateFlow(mockState).asStateFlow()
        }
        SettingsScreen(
            stateFlow = stateFlow,
            onEvent = {}
        )
    }
