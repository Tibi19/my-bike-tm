package com.tam.mybike.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    fillScreenHeight: Boolean = true,
    offsetWaveY: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current.density
    Box(
        modifier = if (fillScreenHeight) {
            Modifier
                .verticalScroll(scrollState)
                .drawBehind {
                    val yOffset = OFFSET_Y_WAVE_COLUMN_BEHIND_RECT + density * offsetWaveY.value
                    drawRect(
                        color = waveBackgroundColor,
                        topLeft = Offset(
                            x = OFFSET_X_WAVE_COLUMN_BEHIND_RECT,
                            y = yOffset
                        )
                    )
                }
            } else {
                Modifier
            }
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.background_wave),
            contentDescription = null,
            tint = waveBackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .scale(SCALE_WAVE_BACKGROUND)
                .offset(x = 0.dp, y = offsetWaveY)
        )
        Column(
            modifier = if (fillScreenHeight) {
                // fillMaxHeight does not work, so we have to force the height
                modifier
                    .padding(contentPadding)
                    .height(getColumnFillHeight(contentPadding))
            } else {
                modifier.padding(contentPadding)
            }
        ) {
            content()
        }
    }
}

@Composable
private fun getColumnFillHeight(contentPadding: PaddingValues): Dp {
    val screenHeight = LocalConfiguration
        .current
        .screenHeightDp
        .dp
    val verticalPadding = contentPadding.calculateTopPadding() + contentPadding.calculateBottomPadding()
    return screenHeight - verticalPadding
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