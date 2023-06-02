package com.tam.mybike.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.R
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.TEXT_CLOSE

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) =
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_x),
            contentDescription = TEXT_CLOSE,
            tint = MaterialTheme.colorScheme.secondary
        )
    }

@Preview
@Composable
private fun CloseButtonPreview() =
    WrapHeightPreview {
        Row(horizontalArrangement = Arrangement.End) {
            CloseButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = PADDING_X_SMALL)
            )
        }
    }
