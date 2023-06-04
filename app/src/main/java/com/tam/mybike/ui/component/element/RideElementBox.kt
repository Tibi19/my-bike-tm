package com.tam.mybike.ui.component.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.R
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.component.text.MediumDetails
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_RIDE_ICON_IMAGE
import com.tam.mybike.ui.theme.PADDING_RIDE_ICON_INSIDE_BACKGROUND
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.SIZE_RIDE_ICON
import com.tam.mybike.ui.theme.TEXT_BIKE
import com.tam.mybike.ui.theme.TEXT_DATE
import com.tam.mybike.ui.theme.TEXT_DISTANCE
import com.tam.mybike.ui.theme.TEXT_DURATION
import com.tam.mybike.ui.theme.TEXT_RIDE

@Composable
fun RideElementBox(
    rideDetails: RideDetails,
    onEditMenuOption: () -> Unit,
    onDeleteMenuOption: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background
) =
    ElementBox(
        onEditMenuOption = onEditMenuOption,
        onDeleteMenuOption = onDeleteMenuOption,
        innerPaddingValues = PaddingValues(horizontal = PADDING_SMALL, vertical = PADDING_MEDIUM),
        backgroundColor = backgroundColor,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(end = PADDING_MEDIUM)) {
            RideTitle(
                title = rideDetails.title,
                backgroundColor = backgroundColor,
                modifier = Modifier.padding(bottom = PADDING_XX_SMALL)
            )
            rideDetails
                .toIntroAndDescriptionPairs()
                .forEach { (intro, description) ->
                    MediumDetails(
                        intro = intro,
                        description = description,
                        modifier = Modifier.padding(bottom = PADDING_XX_SMALL)
                    )
                }
        }
    }

@Composable
private fun RideTitle(
    title: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) =
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        RideIcon(
            backgroundColor = backgroundColor,
            modifier = Modifier.padding(end = PADDING_SMALL)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }

@Composable
private fun RideIcon(
    backgroundColor: Color,
    modifier: Modifier = Modifier
) =
    Box(
        modifier = modifier
            .size(SIZE_RIDE_ICON)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            )
            .padding(PADDING_RIDE_ICON_INSIDE_BACKGROUND)
            .background(backgroundColor, CircleShape)
    ) {
        Icon(
            painter = painterResource(R.drawable.bike_placeholder),
            contentDescription = TEXT_RIDE,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(PADDING_RIDE_ICON_IMAGE)
                .align(Alignment.Center)
        )
    }

data class RideDetails(
    val title: String,
    val bikeName: String,
    val distance: String,
    val duration: String,
    val date: String
)

private fun RideDetails.toIntroAndDescriptionPairs() =
    listOf(
        TEXT_BIKE to bikeName,
        TEXT_DISTANCE to distance,
        TEXT_DURATION to duration,
        TEXT_DATE to date
    )

@Preview
@Composable
private fun RideSectionPreview() =
    WrapHeightPreview {
        RideElementBox(
            rideDetails = RideDetails(
                title = "Friday 29 Ride",
                bikeName = "Nukeproof Scout 290",
                distance = "60km",
                duration = "2h, 14min",
                date = "29.03.2023"
            ),
            onEditMenuOption = {},
            onDeleteMenuOption = {},
            modifier = Modifier.padding(PADDING_MEDIUM)
        )
    }