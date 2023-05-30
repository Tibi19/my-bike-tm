package com.tam.mybike.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tam.mybike.ui.theme.WEIGHT_FILL

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleLarge
) =
    Text(
        text = text,
        style = style,
        modifier = modifier
    )

@Composable
fun RowScope.RowTitle(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    weight: Float = WEIGHT_FILL
) =
    Title(
        text = text,
        style = style,
        modifier = modifier.weight(weight)
    )

@Preview
@Composable
fun TitlePreview() =
    WrapHeightPreview {
        Title(
            text = "Title Text",
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }

@Preview
@Composable
fun RowTitlePreview() =
    WrapHeightPreview {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            RowTitle(
                text = "Title Text",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Text(
                text = "end text",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }