package com.tam.mybike.ui.component.field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.theme.WIDTH_FIELD_BORDER

@Composable
fun FieldContent(
    text: String,
    onClick: () -> Unit,
    borderColor: Color,
    modifier: Modifier = Modifier,
    trailingContent: (@Composable () -> Unit)? = null
) =
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .border(
                border = BorderStroke(
                    width = WIDTH_FIELD_BORDER,
                    color = borderColor,
                ),
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
            trailingContent?.let { content ->
                Spacer(modifier = Modifier.weight(WEIGHT_FILL))
                Spacer(modifier = Modifier.width(PADDING_XX_SMALL))
                content()
            }
        }
    }