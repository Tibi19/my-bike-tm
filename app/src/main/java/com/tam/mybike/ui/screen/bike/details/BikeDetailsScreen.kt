package com.tam.mybike.ui.screen.bike.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tam.mybike.R
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.Ride
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.component.BikeImage
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.component.WaveColumn
import com.tam.mybike.ui.component.WrenchProgressBar
import com.tam.mybike.ui.component.element.RideDetails
import com.tam.mybike.ui.component.element.RideElementBox
import com.tam.mybike.ui.component.popup.DeleteDialog
import com.tam.mybike.ui.component.popup.Options
import com.tam.mybike.ui.component.text.LargeDetails
import com.tam.mybike.ui.component.text.RowTitle
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.Amber
import com.tam.mybike.ui.theme.OFFSET_Y_BIKE_DETAILS_WAVE
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_XX_LARGE
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.PADDING_X_LARGE
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.SIZE_BIKE_DETAILS_BACK_ICON
import com.tam.mybike.ui.theme.TEXT_BACK_ICON_CONTENT
import com.tam.mybike.ui.theme.TEXT_CONNECTOR_IN
import com.tam.mybike.ui.theme.TEXT_INCHES_NOTATION
import com.tam.mybike.ui.theme.TEXT_NO_BIKE_RIDES
import com.tam.mybike.ui.theme.TEXT_RIDES
import com.tam.mybike.ui.theme.TEXT_SERVICE
import com.tam.mybike.ui.theme.TEXT_TOTAL_RIDES_DISTANCE
import com.tam.mybike.ui.theme.TEXT_WHEELS
import com.tam.mybike.ui.util.formatDate
import com.tam.mybike.ui.util.suffix
import com.tam.mybike.ui.util.toDurationString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun BikeDetailsScreen(
    stateFlow: StateFlow<BikeDetailsState>,
    onEvent: (BikeDetailsEvent) -> Unit,
    goToPreviousScreen: () -> Unit,
    goToEditBike: (Int) -> Unit,
    goToEditRide: (Int) -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()
    val bike = state.bike ?: return

    val isDeleteBikeOpenState = remember { mutableStateOf(false) }
    val isDeleteRideOpenState = remember { mutableStateOf(false) }
    var rideToDelete by remember { mutableStateOf<Ride?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        WaveColumn(
            waveBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            offsetWaveY = OFFSET_Y_BIKE_DETAILS_WAVE,
            snapToScreenHeight = false,
            isContentOverflowingScreenHeight = true,
            modifier = Modifier.padding(horizontal = PADDING_MEDIUM)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = PADDING_SMALL,
                        bottom = PADDING_XX_LARGE
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = TEXT_BACK_ICON_CONTENT,
                    modifier = Modifier
                        .padding(end = PADDING_X_LARGE)
                        .size(SIZE_BIKE_DETAILS_BACK_ICON)
                        .clickable { goToPreviousScreen() }
                )
                RowTitle(
                    text = bike.name,
                    modifier = Modifier.offset(y = -PADDING_XX_SMALL)
                )
                Options(
                    onEditMenuOption = { goToEditBike(bike.id) },
                    onDeleteMenuOption = { isDeleteBikeOpenState.value = true },
                )
            }

            BikeImage(
                bikeType = bike.type,
                wheelSize = bike.wheelSize,
                color = Color(bike.colorHex),
                modifier = Modifier
                    .padding(top = PADDING_SMALL)
                    .align(Alignment.CenterHorizontally)
            )

            LargeDetails(
                intro = TEXT_WHEELS,
                description = "${bike.wheelSize.inches}$TEXT_INCHES_NOTATION"
            )
            LargeDetails(
                intro = TEXT_SERVICE,
                description = "${bike.serviceIn.amount}${bike.serviceIn.unit.suffix}",
                connector = TEXT_CONNECTOR_IN
            )

            WrenchProgressBar(
                progress = state.progress,
                modifier = Modifier.padding(top = PADDING_SMALL, bottom = PADDING_X_SMALL)
            )

            Text(
                text = "$TEXT_RIDES ${state.rides.size}",
                style = MaterialTheme.typography.titleMedium
            )
            LargeDetails(
                intro = TEXT_TOTAL_RIDES_DISTANCE,
                description = "${state.totalRidesDistance.amount}${state.totalRidesDistance.unit.suffix}",
                connector = "",
                modifier = Modifier.padding(bottom = PADDING_LARGE)
            )

            Title(
                text = TEXT_RIDES.uppercase(),
                style = MaterialTheme.typography.titleLarge
                    .copy(color = MaterialTheme.colorScheme.tertiary),
                modifier = Modifier.padding(bottom = PADDING_SMALL)
            )

            if (state.rides.isEmpty()) {
                Text(
                    text = TEXT_NO_BIKE_RIDES,
                    style = MaterialTheme.typography.headlineMedium
                )
                return@WaveColumn
            }

            state.rides.forEach { ride ->
                RideElementBox(
                    rideDetails = RideDetails(
                        title = ride.name,
                        bikeName = ride.bike.name,
                        distance = "${ride.distance.amount}${ride.distance.unit.suffix}",
                        duration = ride.minutes.toDurationString(),
                        date = formatDate(ride.dateMillis)
                    ),
                    onEditMenuOption = { goToEditRide(ride.id) },
                    onDeleteMenuOption = {
                        rideToDelete = ride
                        isDeleteRideOpenState.value = true
                    },
                    backgroundColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(PADDING_SMALL))
            }

        }

        // WaveColumn is a rebel teenager who only does what it wants.
        // For example, if there are not enough elements inside it to fill the screen height,
        // WaveColumn will not do so, even with fillMaxSize().
        // This box is here to fill the screen height background when WaveColumn just doesn't feel like it
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        )
    }

    DeleteDialog(
        isOpenState = isDeleteBikeOpenState,
        elementToDeleteName = bike.name,
        onDelete = {
            val deleteBikeEvent = BikeDetailsEvent.OnBikeDelete(bike.id)
            onEvent(deleteBikeEvent)
            goToPreviousScreen()
        }
    )

    DeleteDialog(
        isOpenState = isDeleteRideOpenState,
        elementToDeleteName = rideToDelete?.name ?: "",
        onDelete = {
            val deleteRideEvent = BikeDetailsEvent.OnRideDelete(rideToDelete?.id)
            onEvent(deleteRideEvent)
        }
    )
}

@Preview
@Composable
private fun BikeDetailsScreenPreview() =
    ScreenDarkPreview {
        val mockBike = Bike(
            id = 1,
            name = "Nukeproof Scout 290",
            type = BikeType.MTB,
            wheelSize = WheelSize.BIG,
            colorHex = Amber.value,
            serviceIn = Distance(170, DistanceUnit.KM)
        )
        val mockRides = List(3) {
            Ride(
                id = it + 1,
                name = "Firday 29 Ride",
                bike = mockBike,
                distance = Distance(60, DistanceUnit.KM),
                minutes = 134,
                dateMillis = 1680097200000
            )
        }
        val maxDistance = 1000f
        val mockState = BikeDetailsState(
            bike = mockBike,
            progress = (maxDistance - mockBike.serviceIn.amount) / maxDistance,
            rides = emptyList(),
            totalRidesDistance = Distance(450, DistanceUnit.KM)
        )
        val stateFlow = remember {
            MutableStateFlow(mockState).asStateFlow()
        }
        BikeDetailsScreen(
            stateFlow = stateFlow,
            onEvent = {},
            goToPreviousScreen = {},
            goToEditBike = {},
            goToEditRide = {}
        )
    }
