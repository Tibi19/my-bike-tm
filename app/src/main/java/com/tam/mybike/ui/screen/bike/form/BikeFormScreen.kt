package com.tam.mybike.ui.screen.bike.form

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tam.mybike.R
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.component.BikeImage
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.component.WaveColumn
import com.tam.mybike.ui.component.button.ColorPicker
import com.tam.mybike.ui.component.button.ConfirmButton
import com.tam.mybike.ui.component.button.DefaultSwitch
import com.tam.mybike.ui.component.field.ChoiceHolder
import com.tam.mybike.ui.component.field.DropdownField
import com.tam.mybike.ui.component.field.InputField
import com.tam.mybike.ui.component.text.RowTitle
import com.tam.mybike.ui.theme.OFFSET_Y_BIKE_FORM_WAVE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_XX_LARGE
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.RATIO_BIKE_WIDTH_TO_SCREEN_WIDTH
import com.tam.mybike.ui.theme.SIZE_PAGER_INDICATOR
import com.tam.mybike.ui.theme.SurfaceStatusBarEffect
import com.tam.mybike.ui.theme.TEXT_ADD_BIKE
import com.tam.mybike.ui.theme.TEXT_BIKE_NAME
import com.tam.mybike.ui.theme.TEXT_CLOSE_ICON_CONTENT
import com.tam.mybike.ui.theme.TEXT_DEFAULT_BIKE
import com.tam.mybike.ui.theme.TEXT_INCHES_NOTATION
import com.tam.mybike.ui.theme.TEXT_SERVICE_IN
import com.tam.mybike.ui.theme.TEXT_WHEEL_SIZE
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.util.suffix
import com.tam.mybike.ui.util.text
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BikeFormScreen(
    screenTitle: String,
    confirmText: String,
    stateFlow: StateFlow<BikeFormState>,
    onEvent: (BikeFormEvent) -> Unit,
    goToPreviousScreen: () -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()

    MaterialTheme.SurfaceStatusBarEffect()

    WaveColumn(
        waveBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        offsetWaveY = OFFSET_Y_BIKE_FORM_WAVE,
        contentPaddingForSnappingHeight = PaddingValues(vertical = PADDING_SMALL)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = PADDING_XX_LARGE)
                .padding(horizontal = PADDING_MEDIUM)
        ) {
            RowTitle(text = screenTitle)
            Icon(
                painter = painterResource(id = R.drawable.icon_x),
                contentDescription = TEXT_CLOSE_ICON_CONTENT,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable { goToPreviousScreen() }
            )
        }

        ColorPicker(
            selectedColor = state.selectedColor,
            onSelectionChange = { newColor ->
                val onColorChangeEvent = BikeFormEvent.OnColorChange(newColor)
                onEvent(onColorChangeEvent)
            },
            modifier = Modifier.padding(bottom = PADDING_XX_LARGE)
        )

        BikeTypesPager(
            formState = state,
            onEvent = onEvent,
            modifier = Modifier.padding(bottom = PADDING_MEDIUM)
        )

        InputField(
            value = state.bikeName,
            onValueChange = { newBikeName ->
                val bikeNameChangeEvent = BikeFormEvent.OnBikeNameChange(newBikeName)
                onEvent(bikeNameChangeEvent)
            },
            label = TEXT_BIKE_NAME,
            isRequired = true,
            modifier = Modifier
                .padding(horizontal = PADDING_MEDIUM)
                .padding(bottom = PADDING_SMALL)
        )

        DropdownField(
            selectedChoice = ChoiceHolder(
                key = state.wheelSize.name,
                text = "${state.wheelSize.inches}$TEXT_INCHES_NOTATION"
            ),
            onChoiceChange = { newChoice ->
                val newWheelSize = WheelSize.valueOf(newChoice.key)
                val changeWheelSizeEvent = BikeFormEvent.OnWheelSizeChange(newWheelSize)
                onEvent(changeWheelSizeEvent)
            },
            choices = WheelSize.values().map { wheelSize ->
                ChoiceHolder(
                    key = wheelSize.name,
                    text = "${wheelSize.inches}$TEXT_INCHES_NOTATION"
                )
            },
            label = TEXT_WHEEL_SIZE,
            dropdownHorizontalPadding = PADDING_MEDIUM,
            modifier = Modifier
                .padding(horizontal = PADDING_MEDIUM)
                .padding(bottom = PADDING_SMALL)
        )

        InputField(
            value = state.serviceIn?.amount?.toString() ?: "",
            onValueChange = { newServiceInAmount ->
                val newServiceIn = if (newServiceInAmount.isNotEmpty()) {
                    Distance(newServiceInAmount.toInt(), state.distanceUnit)
                } else {
                    null
                }
                val serviceInChangeEvent = BikeFormEvent.OnServiceInChange(newServiceIn)
                onEvent(serviceInChangeEvent)
            },
            keyboardOptions = KeyboardOptions.Default
                .copy(keyboardType = KeyboardType.Number),
            label = TEXT_SERVICE_IN,
            isRequired = true,
            trailingText = state.distanceUnit.suffix.uppercase(),
            modifier = Modifier.padding(horizontal = PADDING_MEDIUM)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = PADDING_MEDIUM)
        ) {
            Text(
                text = TEXT_DEFAULT_BIKE,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(WEIGHT_FILL))
            DefaultSwitch(
                isOn = state.isDefaultBike,
                onSwitch = {
                    val switchIsDefaultBike = BikeFormEvent.OnSwitchIsDefaultBike
                    onEvent(switchIsDefaultBike)
                },
                modifier = Modifier.padding(end = PADDING_X_SMALL)
            )
        }

        Spacer(modifier = Modifier.weight(WEIGHT_FILL))

        val isConfirmEnabled = state.bikeName.isNotEmpty() && state.serviceIn != null
        ConfirmButton(
            confirmText = confirmText,
            enabled = isConfirmEnabled,
            onClick = {
                onEvent(BikeFormEvent.OnConfirmForm)
                goToPreviousScreen()
            },
            modifier = Modifier.padding(horizontal = PADDING_MEDIUM)
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ColumnScope.BikeTypesPager(
    formState: BikeFormState,
    onEvent: (BikeFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val bikeTypes = BikeType.values()
    val pagerState = rememberPagerState(
        initialPage = bikeTypes.indexOf(formState.selectedBikeType),
        initialPageOffsetFraction = 0f,
        pageCount = { bikeTypes.size }
    )

    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    val bikeWidth = screenWidth * RATIO_BIKE_WIDTH_TO_SCREEN_WIDTH
    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(bikeWidth),
        contentPadding = PaddingValues(
            horizontal = getPagerHorizontalPadding(
                screenWidth = screenWidth,
                bikeWidth = bikeWidth
            )
        ),
        modifier = modifier
    ) { page ->
        val bikeType = bikeTypes[page]
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BikeImage(
                bikeType = bikeType,
                wheelSize = formState.wheelSize,
                color = formState.selectedColor,
                modifier = Modifier
                    .padding(horizontal = PADDING_SMALL)
                    .padding(bottom = PADDING_X_SMALL)
            )
            Text(
                text = bikeType.text,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = PADDING_MEDIUM)
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        repeat(bikeTypes.size) { i ->
            val color = if (i == pagerState.currentPage) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
            Box(
                modifier = Modifier
                    .padding(PADDING_XX_SMALL)
                    .size(SIZE_PAGER_INDICATOR)
                    .background(color = color, shape = CircleShape)
            )
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collectLatest { page ->
                val currentPagerBikeType = bikeTypes[page]
                val changeBikeTypeEvent = BikeFormEvent.OnBikeTypeChange(currentPagerBikeType)
                onEvent(changeBikeTypeEvent)
            }
    }
}

@Composable
private fun getPagerHorizontalPadding(screenWidth: Dp, bikeWidth: Dp): Dp =
    (screenWidth - bikeWidth) / 2

@Preview
@Composable
fun BikeFormScreenPreview() =
    ScreenDarkPreview {
        val mockState = BikeFormState(
            bikeName = "Nukeproof Scout 290",
            serviceIn = Distance(1000, DistanceUnit.KM),
            selectedBikeType = BikeType.MTB,
            wheelSize = WheelSize.BIG
        )
        val stateFlow = remember {
            MutableStateFlow(mockState)
        }
        BikeFormScreen(
            screenTitle = TEXT_ADD_BIKE,
            confirmText = TEXT_ADD_BIKE,
            stateFlow = stateFlow,
            onEvent = {},
            goToPreviousScreen = {}
        )
    }
