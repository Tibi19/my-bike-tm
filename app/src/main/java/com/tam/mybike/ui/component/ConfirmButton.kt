package com.tam.mybike.ui.component

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
import androidx.compose.ui.unit.dp
import com.tam.mybike.ui.theme.WEIGHT_FILL

@Composable
fun ConfirmButton(
    confirmText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) =
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
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
fun ConfirmButtonPreview() =
    WrapHeightPreview {
        ConfirmButton(
            confirmText = "Add Bike",
            onClick = {},
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }