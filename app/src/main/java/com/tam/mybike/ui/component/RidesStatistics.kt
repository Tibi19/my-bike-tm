package com.tam.mybike.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tam.mybike.R
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.Amber
import com.tam.mybike.ui.theme.BIAS_BOX_LINE_TOP
import com.tam.mybike.ui.theme.BIAS_STEP_BOX_LINE
import com.tam.mybike.ui.theme.FACTOR_GRAPH_LINE_DARKNESS
import com.tam.mybike.ui.theme.HEIGHT_GRAPH
import com.tam.mybike.ui.theme.HEIGHT_GRAPH_BAR_MAX_EXTRA
import com.tam.mybike.ui.theme.HEIGHT_GRAPH_BAR_STARTING
import com.tam.mybike.ui.theme.PADDING_GRAPH_TOTAL_DISTANCE_BOTTOM
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.Red
import com.tam.mybike.ui.theme.RADIUS_DEFAULT
import com.tam.mybike.ui.theme.RADIUS_GRAPH_BAR_BACKGROUND_BOTTOM
import com.tam.mybike.ui.theme.SIZE_TEXT_LARGE
import com.tam.mybike.ui.theme.TEXT_CITY
import com.tam.mybike.ui.theme.TEXT_EBIKE
import com.tam.mybike.ui.theme.TEXT_MTB
import com.tam.mybike.ui.theme.TEXT_ROAD
import com.tam.mybike.ui.theme.TEXT_STATISTICS_ICON_CONTENT
import com.tam.mybike.ui.theme.TEXT_STATISTICS_TITLE
import com.tam.mybike.ui.theme.TEXT_TOTAL
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.theme.WIDTH_GRAPH_BAR
import com.tam.mybike.ui.theme.WIDTH_GRAPH_BOX_LINE
import com.tam.mybike.ui.theme.WIDTH_GRAPH_BOX_THICK_LINE
import com.tam.mybike.ui.theme.Yellow
import com.tam.mybike.ui.util.darken
import com.tam.mybike.ui.util.format
import com.tam.mybike.ui.util.suffix

private const val GRAPH_SMALL_INNER_LINES = 9

@Composable
fun RidesStatistics(
    bikeTypeToDistanceMap: Map<BikeType, Distance>,
    totalDistance: Distance,
    modifier: Modifier = Modifier
) =
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = PADDING_MEDIUM, horizontal = PADDING_MEDIUM)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_stats),
                contentDescription = TEXT_STATISTICS_ICON_CONTENT,
                modifier = Modifier.padding(end = PADDING_SMALL)
            )
            Title(text = TEXT_STATISTICS_TITLE)
        }

        RidesGraph(
            bikeTypeToDistanceMap = bikeTypeToDistanceMap,
            totalDistance = totalDistance,
            modifier = Modifier.padding(horizontal = PADDING_SMALL)
        )

        val totalDistanceFormatted = totalDistance.amount.format()
        val totalDistanceSuffix = totalDistance.unit.suffix.uppercase()
        val totalDistanceText = "$TEXT_TOTAL$totalDistanceFormatted$totalDistanceSuffix"
        Text(
            text = totalDistanceText,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(top = PADDING_X_SMALL, bottom = PADDING_GRAPH_TOTAL_DISTANCE_BOTTOM)
                .align(Alignment.CenterHorizontally)
        )
    }

@Composable
private fun RidesGraph(
    bikeTypeToDistanceMap: Map<BikeType, Distance>,
    totalDistance: Distance,
    modifier: Modifier = Modifier
) =
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(HEIGHT_GRAPH)
            .border(
                border = BorderStroke(
                    width = WIDTH_GRAPH_BOX_LINE,
                    color = MaterialTheme.colorScheme.tertiary
                        .darken(FACTOR_GRAPH_LINE_DARKNESS),
                ),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        val topBoxLineBias = BIAS_BOX_LINE_TOP + BIAS_STEP_BOX_LINE
        repeat(GRAPH_SMALL_INNER_LINES) {
            StatisticsBoxLine(
                strokeSize = WIDTH_GRAPH_BOX_LINE,
                modifier = Modifier.align(
                    BiasAlignment(
                        horizontalBias = 0f,
                        verticalBias = topBoxLineBias + it * BIAS_STEP_BOX_LINE
                    )
                )
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(bottom = PADDING_X_SMALL)
                .padding(horizontal = PADDING_SMALL)
                .align(Alignment.BottomCenter)
        ) {
            val fallbackDistance = Distance(0, totalDistance.unit)
            val roadDistance = bikeTypeToDistanceMap[BikeType.ROADBIKE] ?: fallbackDistance
            val mtbDistance = bikeTypeToDistanceMap[BikeType.MTB] ?: fallbackDistance
            val cityDistance = bikeTypeToDistanceMap[BikeType.HYBRID] ?: fallbackDistance
            val eBikeDistance = bikeTypeToDistanceMap[BikeType.ELECTRIC] ?: fallbackDistance
            GraphColumn(
                description = TEXT_ROAD,
                distance = roadDistance,
                height = getBarHeight(roadDistance, totalDistance),
                color = Red
            )
            Spacer(modifier = Modifier.weight(WEIGHT_FILL))
            GraphColumn(
                description = TEXT_MTB,
                distance = mtbDistance,
                height = getBarHeight(mtbDistance, totalDistance),
                color = Amber
            )
            Spacer(modifier = Modifier.weight(WEIGHT_FILL))
            GraphColumn(
                description = TEXT_CITY,
                distance = cityDistance,
                height = getBarHeight(cityDistance, totalDistance),
                color = Yellow
            )
            Spacer(modifier = Modifier.weight(WEIGHT_FILL))
            GraphColumn(
                description = TEXT_EBIKE,
                distance = eBikeDistance,
                height = getBarHeight(eBikeDistance, totalDistance),
                color = Color.White
            )
        }

        StatisticsBoxLine(
            strokeSize = WIDTH_GRAPH_BOX_THICK_LINE,
            modifier = Modifier.align(
                BiasAlignment(
                    horizontalBias = 0f,
                    verticalBias = topBoxLineBias + GRAPH_SMALL_INNER_LINES * BIAS_STEP_BOX_LINE
                )
            )
        )

    }

private fun getBarHeight(
    barDistance: Distance,
    totalDistance: Distance
): Dp =
    HEIGHT_GRAPH_BAR_STARTING + getBarExtraHeight(barDistance, totalDistance)

private fun getBarExtraHeight(
    barDistance: Distance,
    totalDistance: Distance
): Dp {
    val barDistancePercentFromTotal = barDistance.amount.toFloat() / totalDistance.amount.toFloat()
    return (barDistancePercentFromTotal * HEIGHT_GRAPH_BAR_MAX_EXTRA.value).dp
}

@Composable
private fun GraphBar(
    distance: Distance,
    height: Dp,
    color: Color,
    modifier: Modifier = Modifier
) =
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .width(WIDTH_GRAPH_BAR)
            .height(height)
            .background(
                color = color,
                shape = RoundedCornerShape(
                    topStart = RADIUS_DEFAULT,
                    topEnd = RADIUS_DEFAULT,
                    bottomStart = RADIUS_GRAPH_BAR_BACKGROUND_BOTTOM,
                    bottomEnd = RADIUS_GRAPH_BAR_BACKGROUND_BOTTOM
                )
            )
    ) {
        Text(
            text = distance.amount.format(),
            fontSize = SIZE_TEXT_LARGE,
            color = Color.Black,
            modifier = Modifier.padding(bottom = PADDING_XX_SMALL)
        )
    }

@Composable
private fun GraphColumn(
    description: String,
    distance: Distance,
    height: Dp,
    color: Color,
    modifier: Modifier = Modifier
) =
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        GraphBar(
            distance = distance,
            height = height,
            color = color
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall
                .copy(fontSize = SIZE_TEXT_LARGE)
        )
    }

@Composable
private fun StatisticsBoxLine(
    strokeSize: Dp,
    modifier: Modifier = Modifier
) =
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(strokeSize)
            .background(
                color = MaterialTheme.colorScheme.tertiary
                    .darken(FACTOR_GRAPH_LINE_DARKNESS),
                shape = RectangleShape
            )
    )

@Preview
@Composable
private fun RidesStatisticsPreview() =
    WrapHeightDarkPreview {
        RidesStatistics(
            bikeTypeToDistanceMap = mapOf(
                BikeType.ROADBIKE to Distance(8500, DistanceUnit.KM),
                BikeType.MTB to Distance(2650, DistanceUnit.KM),
                BikeType.HYBRID to Distance(3420, DistanceUnit.KM),
                BikeType.ELECTRIC to Distance(11000, DistanceUnit.KM)
            ),
            totalDistance = Distance(25570, DistanceUnit.KM),
            modifier = Modifier.padding(PADDING_MEDIUM)
        )
    }