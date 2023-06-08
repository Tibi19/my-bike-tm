package com.tam.mybike.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tam.mybike.R
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.RATIO_BIKE_HEIGHT_TO_WIDTH
import com.tam.mybike.ui.theme.RATIO_BIKE_WIDTH_TO_SCREEN_WIDTH
import com.tam.mybike.ui.theme.TEXT_MIDDLE_BIKE_CONTENT
import com.tam.mybike.ui.theme.TEXT_OVER_BIKE_CONTENT
import com.tam.mybike.ui.theme.TEXT_UNDER_BIKE_CONTENT

private data class BikeImageState(
    val overResId: Int,
    val middleResId: Int,
    val underResId: Int
)

@Composable
fun BikeImage(
    bikeType: BikeType,
    wheelSize: WheelSize,
    color: Color,
    modifier: Modifier = Modifier
) {
    val bikeImageState = getBikeImageState(bikeType, wheelSize)
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    val bikeWidth = screenWidth * RATIO_BIKE_WIDTH_TO_SCREEN_WIDTH
    val bikeHeight = bikeWidth * RATIO_BIKE_HEIGHT_TO_WIDTH
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.height(bikeHeight)
    ) {
        Image(
            painter = painterResource(id = bikeImageState.underResId),
            contentDescription = TEXT_UNDER_BIKE_CONTENT,
            modifier = Modifier.size(bikeWidth)
        )
        Icon(
            painter = painterResource(id = bikeImageState.middleResId),
            contentDescription = TEXT_MIDDLE_BIKE_CONTENT,
            tint = color,
            modifier = Modifier.size(bikeWidth)
        )
        Image(
            painter = painterResource(id = bikeImageState.overResId),
            contentDescription = TEXT_OVER_BIKE_CONTENT,
            modifier = Modifier.size(bikeWidth)
        )
    }
}

private fun getBikeImageState(
    bikeType: BikeType,
    wheelSize: WheelSize,
): BikeImageState =
    when (bikeType) {
        BikeType.MTB -> BikeImageState(
            overResId = R.drawable.bike_mtb_over,
            middleResId = R.drawable.bike_mtb_middle,
            underResId = if (wheelSize == WheelSize.BIG) R.drawable.bike_mtb_big_wheels else R.drawable.bike_mtb_small_wheels
        )
        BikeType.ROADBIKE -> BikeImageState(
            overResId = R.drawable.bike_roadbike_over,
            middleResId = R.drawable.bike_roadbike_middle,
            underResId = if (wheelSize == WheelSize.BIG) R.drawable.bike_roadbike_big_wheels else R.drawable.bike_roadbike_small_wheels
        )
        BikeType.ELECTRIC -> BikeImageState(
            overResId = R.drawable.bike_electric_over,
            middleResId = R.drawable.bike_electric_middle,
            underResId = if (wheelSize == WheelSize.BIG) R.drawable.bike_electric_big_wheels else R.drawable.bike_electric_small_wheels
        )
        BikeType.HYBRID -> BikeImageState(
            overResId = R.drawable.bike_hybrid_over,
            middleResId = R.drawable.bike_hybrid_middle,
            underResId = if (wheelSize == WheelSize.BIG) R.drawable.bike_hybrid_big_wheels else R.drawable.bike_hybrid_small_wheels
        )
    }

@Preview
@Composable
private fun BikeImagePreview() =
    WrapHeightPreview {
        BikeImage(
            bikeType = BikeType.HYBRID,
            wheelSize = WheelSize.BIG,
            color = BikeColors[6]
        )
    }