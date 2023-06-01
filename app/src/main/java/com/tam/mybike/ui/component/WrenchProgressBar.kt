package com.tam.mybike.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tam.mybike.R
import com.tam.mybike.ui.theme.BREAKPOINT_BOLT_WARNING_COLOR
import com.tam.mybike.ui.theme.BluePaleDark
import com.tam.mybike.ui.theme.HEIGHT_WRENCH_PROGRESS_INDICATOR
import com.tam.mybike.ui.theme.OFFSET_X_WRENCH_STARTING_FROM
import com.tam.mybike.ui.theme.PADDING_HORIZONTAL_PROGRESS_INDICATOR
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.TEXT_WRENCH_PROGRESS

@Composable
fun WrenchProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) =
    Box(modifier = modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(R.drawable.loading_circle),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        LinearProgressIndicator(
            progress = progress,
            color = MaterialTheme.colorScheme.primary,
            trackColor = BluePaleDark,
            modifier = Modifier
                .padding(horizontal = PADDING_HORIZONTAL_PROGRESS_INDICATOR)
                .height(HEIGHT_WRENCH_PROGRESS_INDICATOR)
                .fillMaxWidth()
                .align(Alignment.Center)
        )

        Icon(
            painter = painterResource(R.drawable.loading_wrench),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = TEXT_WRENCH_PROGRESS,
            modifier = Modifier
                .align(
                    BiasAlignment(
                        horizontalBias = progressAsBias(progress),
                        verticalBias = 0f
                    )
                )
                .offset(x = biasedWrenchOffsetFromProgress(progress))
        )

        val boltColor = if (progress < BREAKPOINT_BOLT_WARNING_COLOR) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.error
        }

        Icon(
            painter = painterResource(R.drawable.loading_bolt),
            tint = boltColor,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }

private fun progressAsBias(progress: Float) =
    progress * 2 - 1

// Progress to bias calculation has to be accounted into the wrench offset
private fun biasedWrenchOffsetFromProgress(progress: Float) =
    (OFFSET_X_WRENCH_STARTING_FROM + progress * 2).dp

@Preview
@Composable
fun WrenchProgressPreview() =
    WrapHeightPreview {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
            WrenchProgressBar(
                progress = 0.6f,
                modifier = Modifier.padding(PADDING_LARGE)
            )
        }
    }