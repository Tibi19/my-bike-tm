package com.tam.mybike.ui.screen.bikes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tam.mybike.R
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.component.BikeImage
import com.tam.mybike.ui.component.ScreenDarkPreview
import com.tam.mybike.ui.component.WaveColumn
import com.tam.mybike.ui.component.WrenchProgressBar
import com.tam.mybike.ui.component.button.AddButton
import com.tam.mybike.ui.component.button.ConfirmButton
import com.tam.mybike.ui.component.element.ElementBox
import com.tam.mybike.ui.component.popup.DeleteDialog
import com.tam.mybike.ui.component.text.LargeDetails
import com.tam.mybike.ui.component.text.RowTitle
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.FALLBACK_BIKE_PROGRESS
import com.tam.mybike.ui.theme.LINE_HEIGHT_TEXT_MISSING_BIKES
import com.tam.mybike.ui.theme.PADDING_BIKE_BOX_INNER_PADDING
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_XX_LARGE
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.TEXT_ADD_BIKE
import com.tam.mybike.ui.theme.TEXT_BIKE
import com.tam.mybike.ui.theme.TEXT_BIKES
import com.tam.mybike.ui.theme.TEXT_CONNECTOR_IN
import com.tam.mybike.ui.theme.TEXT_INCHES_NOTATION
import com.tam.mybike.ui.theme.TEXT_MISSING_BIKES
import com.tam.mybike.ui.theme.TEXT_MISSING_BIKES_CONTENT
import com.tam.mybike.ui.theme.TEXT_MISSING_BIKES_DOTTED_LINE_CONTENT
import com.tam.mybike.ui.theme.TEXT_SERVICE
import com.tam.mybike.ui.theme.TEXT_WHEELS
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.theme.WIDTH_DOTTED_LINE
import com.tam.mybike.ui.util.suffix
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun BikesScreen(
    stateFlow: StateFlow<BikesState>,
    onEvent: (BikesEvent) -> Unit,
    goToAddBike: () -> Unit,
    goToEditBike: () -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()

    if (state.bikes.isEmpty()) {
        EmptyBikesScreen(goToAddBike = goToAddBike)
        return
    }

    val isDeleteDialogOpenState = remember { mutableStateOf(false) }
    var bikeToDelete by remember { mutableStateOf<Bike?>(null) }

    LazyColumn(modifier = Modifier.padding(horizontal = PADDING_MEDIUM)) {
        item {
            Spacer(modifier = Modifier.height(PADDING_XX_LARGE + PADDING_LARGE))
            Row(modifier = Modifier.padding(bottom = PADDING_MEDIUM)) {
               RowTitle(text = TEXT_BIKES)
               AddButton(
                   elementTitle = TEXT_BIKE,
                   onClick = goToAddBike
               )
            }
        }

        items(state.bikes) {bike ->
            ElementBox(
                onEditMenuOption = goToEditBike,
                onDeleteMenuOption = {
                    bikeToDelete = bike
                    isDeleteDialogOpenState.value = true
                },
                innerPaddingValues = PaddingValues(PADDING_BIKE_BOX_INNER_PADDING),
                optionsPaddingValues = PaddingValues(PADDING_SMALL),
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.padding(bottom = PADDING_SMALL)
            ) {
                WaveColumn(
                    waveBackgroundColor = MaterialTheme.colorScheme.background,
                    snapToScreenHeight = false,
                    modifier = Modifier
                        .padding(vertical = PADDING_SMALL, horizontal = PADDING_MEDIUM)
                ) {
                    BikeImage(
                        bikeType = bike.type,
                        wheelSize = bike.wheelSize,
                        color = Color(bike.colorHex),
                        modifier = Modifier
                            .padding(top = PADDING_MEDIUM)
                            .fillMaxWidth()
                    )
                    Title(text = bike.name)
                    LargeDetails(
                        intro = TEXT_WHEELS,
                        description = "${bike.wheelSize.inches}$TEXT_INCHES_NOTATION"
                    )
                    LargeDetails(
                        intro = TEXT_SERVICE,
                        description = "${bike.serviceIn.amount}${state.distanceUnit.suffix}",
                        connector = TEXT_CONNECTOR_IN
                    )
                    val bikeProgress = state.bikeToProgressMap[bike.id] ?: FALLBACK_BIKE_PROGRESS
                    WrenchProgressBar(
                        progress = bikeProgress,
                        modifier = Modifier.padding(top = PADDING_SMALL, bottom = PADDING_X_SMALL)
                    )
                }
            }
        }
    }

    DeleteDialog(
        isOpenState = isDeleteDialogOpenState,
        elementToDeleteName = bikeToDelete?.name ?: "",
        onDelete = {
            val deleteEvent = BikesEvent.DeleteBike(bikeToDelete?.id)
            onEvent(deleteEvent)
        }
    )

}

@Composable
private fun EmptyBikesScreen(goToAddBike: () -> Unit) =
    Column(modifier = Modifier.padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL)) {
        Title(
            text = TEXT_BIKES,
            modifier = Modifier.padding(bottom = PADDING_LARGE)
        )
        Image(
            painter = painterResource(id = R.drawable.missing_bike_card),
            contentDescription = TEXT_MISSING_BIKES_CONTENT,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .padding(vertical = PADDING_LARGE)
                .width(WIDTH_DOTTED_LINE * 2)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dotted_line),
                contentDescription = TEXT_MISSING_BIKES_DOTTED_LINE_CONTENT,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.width(WIDTH_DOTTED_LINE)
            )
            Text(
                text = TEXT_MISSING_BIKES,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                lineHeight = LINE_HEIGHT_TEXT_MISSING_BIKES,
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(color = MaterialTheme.colorScheme.surface)
            )
        }
        Spacer(modifier = Modifier.weight(WEIGHT_FILL))
        ConfirmButton(
            confirmText = TEXT_ADD_BIKE,
            onClick = goToAddBike
        )
    }

@Preview
@Composable
private fun BikesScreenPreview() =
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
        val mockState = BikesState(
            bikes = mockBikes,
            bikeToProgressMap = mockBikes.associate { bike ->
                val maxDistance = 1000f
                bike.id to ((maxDistance - bike.serviceIn.amount) / maxDistance)
            },
            distanceUnit = DistanceUnit.KM
        )
        val stateFlow = remember {
            MutableStateFlow(mockState).asStateFlow()
        }
        BikesScreen(
            stateFlow = stateFlow,
            onEvent = {},
            goToAddBike = {},
            goToEditBike = {}
        )
    }

@Preview
@Composable
private fun EmptyBikesScreenPreview() =
    ScreenDarkPreview {
        EmptyBikesScreen(goToAddBike = {})
    }