package com.tam.mybike.ui.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.theme.BIAS_SWITCH_THUMB_START
import com.tam.mybike.ui.theme.BIAS_SWITCH_THUMB_END
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.SIZE_DEFAULT_SWITCH
import com.tam.mybike.ui.theme.SIZE_DEFAULT_SWITCH_THUMB
import com.tam.mybike.ui.theme.SIZE_DEFAULT_SWITCH_TRACK_STROKE

@Composable
fun DefaultSwitch(
    isOn: Boolean,
    modifier: Modifier = Modifier,
    onSwitch: (isOn: Boolean) -> Unit
) =
    // Custom switch implementation, as the material switch does not align with the required UI
    IconToggleButton(
        checked = isOn,
        onCheckedChange = onSwitch,
        modifier = modifier
    ) {
        Box(modifier = Modifier.size(SIZE_DEFAULT_SWITCH)) {
            DefaultSwitchTrack(isOn = isOn)
            DefaultSwitchThumb(isOn = isOn, onSwitch = onSwitch)
        }
    }

@Composable
private fun DefaultSwitchTrack(isOn: Boolean) {
    val trackColor by animateColorAsState(
        targetValue = if (isOn) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
    )
    Box {
        Canvas(
            Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            drawTrack(
                trackColor = trackColor,
                trackWidth = SIZE_DEFAULT_SWITCH.toPx(),
                strokeWidth = SIZE_DEFAULT_SWITCH_TRACK_STROKE.toPx()
            )
        }
    }
}

private fun DrawScope.drawTrack(
    trackColor: Color,
    trackWidth: Float,
    strokeWidth: Float
) {
    val strokeRadius = strokeWidth / 2
    drawLine(
        color = trackColor,
        start = Offset(x = strokeRadius, y = center.y),
        end = Offset(x = trackWidth - strokeRadius, y = center.y),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
private fun BoxScope.DefaultSwitchThumb(
    isOn: Boolean,
    onSwitch: (isOn: Boolean) -> Unit
) {
    val thumbAlignmentBias by animateFloatAsState(
        targetValue = if (isOn) BIAS_SWITCH_THUMB_END else BIAS_SWITCH_THUMB_START
    )
    val thumbDragState = rememberDraggableState { delta ->
        val isDraggingFromChecked = delta < 0 && isOn
        val isDraggingFromUnchecked = delta > 0 && !isOn
        if (isDraggingFromChecked || isDraggingFromUnchecked) {
            onSwitch(!isOn)
        }
    }
    Box(
        modifier = Modifier
            .size(SIZE_DEFAULT_SWITCH_THUMB)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            )
            .align(
                BiasAlignment(
                    horizontalBias = thumbAlignmentBias,
                    verticalBias = 0f
                )
            )
            .draggable(
                orientation = Orientation.Horizontal,
                state = thumbDragState
            )
    )
}

@Preview
@Composable
private fun DefaultSwitchPreview() =
    WrapHeightPreview {
        DefaultSwitch(
            isOn = true,
            modifier = Modifier.padding(horizontal = PADDING_X_SMALL),
            onSwitch = {}
        )
    }
