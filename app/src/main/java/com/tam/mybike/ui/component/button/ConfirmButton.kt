package com.tam.mybike.ui.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.theme.FACTOR_DISABLED_BUTTON_DARKNESS
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.TEXT_ADD_BIKE
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.util.darken

@Composable
fun ConfirmButton(
    confirmText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) =
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.primary
                .darken(FACTOR_DISABLED_BUTTON_DARKNESS),
            disabledContentColor = MaterialTheme.colorScheme.inversePrimary
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = confirmText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(weight = WEIGHT_FILL, fill = true)
        )
    }

@Preview
@Composable
private fun ConfirmButtonPreview() =
    WrapHeightPreview {
        ConfirmButton(
            confirmText = TEXT_ADD_BIKE,
            onClick = {},
            modifier = Modifier.padding(horizontal = PADDING_X_SMALL)
        )
    }

@Preview
@Composable
private fun ConfirmButtonDisabledPreview() =
    WrapHeightPreview {
        ConfirmButton(
            confirmText = TEXT_ADD_BIKE,
            onClick = {},
            enabled = false,
            modifier = Modifier.padding(horizontal = PADDING_X_SMALL)
        )
    }