package com.tam.mybike.ui.screen.rides

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.tam.mybike.ui.component.RidesStatistics
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.component.button.AddButton
import com.tam.mybike.ui.component.button.ConfirmButton
import com.tam.mybike.ui.component.element.RideDetails
import com.tam.mybike.ui.component.element.RideElementBox
import com.tam.mybike.ui.component.text.RowTitle
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_ADD_RIDE
import com.tam.mybike.ui.theme.TEXT_MISSING_RIDES_CONTENT
import com.tam.mybike.ui.theme.TEXT_MISSING_RIDES_DOTTED_LINE_CONTENT
import com.tam.mybike.ui.theme.TEXT_OLDER
import com.tam.mybike.ui.theme.TEXT_RIDE
import com.tam.mybike.ui.theme.TEXT_RIDES
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.theme.WIDTH_DOTTED_LINE
import com.tam.mybike.ui.util.formatDate
import com.tam.mybike.ui.util.suffix
import com.tam.mybike.ui.util.toDurationString
import com.tam.mybike.ui.util.toMonthName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val MONTHS_COUNT_TO_DISPLAY = 3

@Composable
fun RidesScreen(
    stateFlow: StateFlow<RidesState>,
    onEvent: (RidesEvent) -> Unit,
    goToAddRide: () -> Unit,
    goToEditRide: () -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()

    if (state.ridesByMonth.isEmpty()) {
        EmptyRidesScreen(goToAddRide = goToAddRide)
        return
    }

    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .padding(horizontal = PADDING_MEDIUM)
    ) {
        Row(modifier = Modifier.padding(bottom = PADDING_MEDIUM)) {
            RowTitle(text = TEXT_RIDES)
            AddButton(
                elementTitle = TEXT_RIDE,
                onClick = goToAddRide
            )
        }
        RidesStatistics(
            bikeTypeToDistanceMap = state.bikeTypeToDistanceMap,
            totalDistance = state.totalDistance,
            modifier = Modifier.padding(bottom = PADDING_MEDIUM)
        )

        var ridesByMonthIndex = 0
        state.ridesByMonth.forEach { (month, rides) ->
            val monthTitle = if (ridesByMonthIndex <= MONTHS_COUNT_TO_DISPLAY) month.toMonthName() else TEXT_OLDER
            ridesByMonthIndex++
            monthTitle?.let {
                Title(
                    text = it,
                    style = MaterialTheme.typography.titleLarge
                        .copy(color = MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier.padding(bottom = PADDING_SMALL)
                )
            }

            rides.forEach { ride ->
                RideElementBox(
                    rideDetails = RideDetails(
                        title = ride.name,
                        bikeName = ride.bike.name,
                        distance = "${ride.distance.amount}${ride.distance.unit.suffix}",
                        duration = ride.minutes.toDurationString(),
                        date = formatDate(ride.dateMillis)
                    ),
                    onEditMenuOption = goToEditRide,
                    onDeleteMenuOption = {
                        val deleteEvent = RidesEvent.DeleteRide(ride.id)
                        onEvent(deleteEvent)
                    },
                    modifier = Modifier
                        .padding(bottom = PADDING_SMALL)
                        .fillMaxWidth()
                )
            }
        }
    }

}

@Composable
private fun EmptyRidesScreen(goToAddRide: () -> Unit) =
    Column(modifier = Modifier.padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL)) {
        Title(
            text = TEXT_RIDES,
            modifier = Modifier.padding(bottom = PADDING_LARGE)
        )
        Image(
            painter = painterResource(id = R.drawable.missing_ride_card),
            contentDescription = TEXT_MISSING_RIDES_CONTENT,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = PADDING_LARGE)
                .width(WIDTH_DOTTED_LINE * 2)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dotted_line),
                contentDescription = TEXT_MISSING_RIDES_DOTTED_LINE_CONTENT,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.width(WIDTH_DOTTED_LINE)
            )
        }
        Spacer(modifier = Modifier.weight(WEIGHT_FILL))
        ConfirmButton(
            confirmText = TEXT_ADD_RIDE,
            onClick = goToAddRide
        )
    }

@Preview
@Composable
private fun RidesScreenPreview() =
    ScreenDarkPreview {
        val mockBike = Bike(
            id = 1,
            name = "Nukeproof Scout 290",
            type = BikeType.MTB,
            wheelSize = WheelSize.BIG,
            colorHex = BikeColors[4].value,
            serviceIn = Distance(170, DistanceUnit.KM)
        )
        val marchRides = List(4) {
            Ride(
                id = it + 1,
                name = "Firday 29 Ride",
                bike = mockBike,
                distance = Distance(60, DistanceUnit.KM),
                minutes = 134,
                dateMillis = 1680037200000
            )
        }
        val mockState = RidesState(
            ridesByMonth = mapOf(
                    3 to marchRides
                ).toSortedMap(compareByDescending { it }),
            bikeTypeToDistanceMap = mapOf(
                BikeType.ROADBIKE to Distance(8500, DistanceUnit.KM),
                BikeType.MTB to Distance(2650, DistanceUnit.KM),
                BikeType.HYBRID to Distance(3420, DistanceUnit.KM),
                BikeType.ELECTRIC to Distance(11000, DistanceUnit.KM)
            ),
            totalDistance = Distance(25570, DistanceUnit.KM)
        )
        val stateFlow = remember {
            MutableStateFlow(mockState).asStateFlow()
        }
        RidesScreen(
            stateFlow = stateFlow,
            onEvent = {},
            goToAddRide = {},
            goToEditRide = {}
        )
    }

@Preview
@Composable
private fun EmptyRidesScreenPreview() =
    ScreenDarkPreview {
        EmptyRidesScreen(goToAddRide = {})
    }