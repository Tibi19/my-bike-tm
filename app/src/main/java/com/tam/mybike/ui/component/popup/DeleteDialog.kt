package com.tam.mybike.ui.component.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.tam.mybike.ui.component.ScreenPreview
import com.tam.mybike.ui.component.button.ConfirmButton
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_CANCEL
import com.tam.mybike.ui.theme.TEXT_DELETE
import com.tam.mybike.ui.theme.TEXT_DELETE_PROMPT
import com.tam.mybike.ui.theme.WEIGHT_FILL

@Composable
fun DeleteDialog(
    isOpenState: MutableState<Boolean>,
    elementToDeleteName: String,
    onDelete: () -> Unit
) =
    Dialog(onDismissRequest = { isOpenState.value = false }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(PADDING_LARGE)
            ) {
                Text(
                    text = elementToDeleteName,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = TEXT_DELETE_PROMPT,
                    style = MaterialTheme.typography.titleMedium
                )
                DeleteDialogButtons(
                    onDelete = onDelete,
                    onClose = { isOpenState.value = false },
                    modifier = Modifier.padding(
                        top = PADDING_MEDIUM,
                        start = PADDING_LARGE,
                        end = PADDING_LARGE
                    )
                )
            }

        }
    }

@Composable
private fun DeleteDialogButtons(
    onDelete: () -> Unit,
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
            confirmText = TEXT_DELETE,
            onClick = {
                onDelete()
                onClose()
            },
            modifier = Modifier.weight(WEIGHT_FILL)
        )
    }

@Preview
@Composable
fun DeleteDialogPreview() =
    ScreenPreview {
        val isOpenState = remember { mutableStateOf(true) }
        DeleteDialog(
            isOpenState = isOpenState,
            elementToDeleteName = "Nukeproof Scout 290",
            onDelete = {}
        )
    }