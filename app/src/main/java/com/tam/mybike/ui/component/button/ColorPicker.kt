package com.tam.mybike.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.component.WrapHeightDarkPreview
import com.tam.mybike.ui.theme.Amber
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.RADIUS_COLOR_BUTTON_RIPPLE
import com.tam.mybike.ui.theme.RADIUS_COLOR_BUTTON_SELECTED_BORDER
import com.tam.mybike.ui.theme.SIZE_COLOR_BUTTON

@Composable
fun ColorPicker(
    selectedColor: Color,
    onSelectionChange: (Color) -> Unit,
    modifier: Modifier = Modifier
) =
    Row(
        modifier = modifier.horizontalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(PADDING_MEDIUM))
        BikeColors.forEach { color ->
            ColorButton(
                color = color,
                isSelected = selectedColor == color,
                onSelect = { onSelectionChange(color) }
            )
        }
    }


@Composable
private fun ColorButton(
    color: Color,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectionColor = if (isSelected) Color.White else Color.Transparent
    val density = LocalDensity.current.density
    Box(
        modifier = modifier
            .padding(end = PADDING_LARGE)
            .size(SIZE_COLOR_BUTTON)
            .drawBehind {
                val circleRadius = RADIUS_COLOR_BUTTON_SELECTED_BORDER.value * density
                drawCircle(color = selectionColor, radius = circleRadius)
            }
            .background(color = color, shape = CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = RADIUS_COLOR_BUTTON_RIPPLE
                ),
                onClick = onSelect
            )
    )
}

@Preview
@Composable
private fun ColorPickerPreview() =
    WrapHeightDarkPreview {
        ColorPicker(
            selectedColor = Amber,
            onSelectionChange = {},
            modifier = Modifier.padding(vertical = PADDING_MEDIUM)
        )
    }