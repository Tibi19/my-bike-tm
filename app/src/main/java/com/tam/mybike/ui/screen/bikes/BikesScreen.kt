package com.tam.mybike.ui.screen.bikes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tam.mybike.ui.component.BikeImage
import com.tam.mybike.ui.component.SurfacePreview
import com.tam.mybike.ui.component.WaveColumn
import com.tam.mybike.ui.component.WrenchProgressBar
import com.tam.mybike.ui.component.button.AddButton
import com.tam.mybike.ui.component.element.ElementBox
import com.tam.mybike.ui.component.text.LargeDetails
import com.tam.mybike.ui.component.text.RowTitle
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.FALLBACK_BIKE_PROGRESS
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_XX_LARGE
import com.tam.mybike.ui.theme.TEXT_BIKE
import com.tam.mybike.ui.theme.TEXT_BIKES
import com.tam.mybike.ui.theme.TEXT_CONNECTOR_IN
import com.tam.mybike.ui.theme.TEXT_INCHES_NOTATION
import com.tam.mybike.ui.theme.TEXT_SERVICE
import com.tam.mybike.ui.theme.TEXT_WHEELS
import com.tam.mybike.ui.util.text
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BikesScreen(
    stateFlow: StateFlow<BikesState>,
    onEvent: (BikesEvent) -> Unit,
    goToAddBike: () -> Unit,
    goToEditBike: () -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier.padding(PADDING_MEDIUM)) {
        item {
            Spacer(modifier = Modifier.height(PADDING_XX_LARGE))
            Row {
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
                    val deleteEvent = BikesEvent.DeleteBike(bike.id)
                    onEvent(deleteEvent)
                },
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                WaveColumn(waveBackgroundColor = MaterialTheme.colorScheme.background) {
                    BikeImage(
                        bikeType = bike.type,
                        wheelSize = bike.wheelSize,
                        color = bike.color
                    )
                    Title(text = bike.name)
                    LargeDetails(
                        intro = TEXT_WHEELS,
                        description = "${bike.wheelSize.inches}$TEXT_INCHES_NOTATION"
                    )
                    LargeDetails(
                        intro = TEXT_SERVICE,
                        description = "${bike.serviceIn}${state.distanceUnit.text}",
                        connector = TEXT_CONNECTOR_IN
                    )
                    val bikeProgress = state.bikeToProgressMap[bike.id] ?: FALLBACK_BIKE_PROGRESS
                    WrenchProgressBar(progress = bikeProgress)
                }
            }
        }
    }

}

@Preview
@Composable
fun BikesScreenPreview() =
    SurfacePreview {
        // TODO add mock state and event
//        BikesScreen(goToAddBike = {})
    }