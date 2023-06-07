package com.tam.mybike.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.R
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.OFFSET_X_WAVE_COLUMN_BEHIND_RECT
import com.tam.mybike.ui.theme.OFFSET_Y_WAVE_COLUMN_BEHIND_RECT
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.SCALE_WAVE_BACKGROUND

@Composable
fun WaveColumn(
    waveBackgroundColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    color = waveBackgroundColor,
                    topLeft = Offset(
                        x = OFFSET_X_WAVE_COLUMN_BEHIND_RECT,
                        y = OFFSET_Y_WAVE_COLUMN_BEHIND_RECT
                    )
                )
            }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.background_wave),
            contentDescription = null,
            tint = waveBackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .scale(SCALE_WAVE_BACKGROUND)
        )
        Column(modifier = modifier) {
            content()
        }
    }
}

@Preview
@Composable
private fun WaveColumnPreview() =
    ScreenPreview {
        WaveColumn(
            waveBackgroundColor = MaterialTheme.colorScheme.background
        ) {
            repeat(50) {
                Title(
                    text = "Nukeproof Scout 290",
                    modifier = Modifier.padding(
                        bottom = PADDING_MEDIUM,
                        start = PADDING_MEDIUM
                    )
                )
            }
        }
    }