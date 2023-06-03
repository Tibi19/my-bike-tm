package com.tam.mybike.ui.component.popup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tam.mybike.ui.component.button.ConfirmButton
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_CANCEL
import com.tam.mybike.ui.theme.WEIGHT_FILL

@Composable
fun DialogButtons(
    confirmText: String,
    onConfirm: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) =
    Row(modifier = modifier) {
        TextButton(
            onClick = onClose,
            modifier = Modifier.weight(WEIGHT_FILL)
        ) {
            Text(
                text = TEXT_CANCEL,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.width(PADDING_SMALL))
        ConfirmButton(
            confirmText = confirmText,
            onClick = {
                onConfirm()
                onClose()
            },
            modifier = Modifier.weight(WEIGHT_FILL)
        )
    }