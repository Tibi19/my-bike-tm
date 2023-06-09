package com.tam.mybike.ui.component.popup

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_CANCEL
import com.tam.mybike.ui.theme.TEXT_OK

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    isOpenState: MutableState<Boolean>,
    dateState: DatePickerState,
    onDateChange: (Long?) -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isOpenState.value) return

    val selectionDateState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    DatePickerDialog(
        onDismissRequest = { isOpenState.value = false },
        confirmButton = {
            Button(
                onClick = {
                    onDateChange(selectionDateState.selectedDateMillis)
                    isOpenState.value = false
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier
                    .padding(horizontal = PADDING_MEDIUM)
                    .padding(bottom = PADDING_SMALL)
            ) {
                Text(
                    text = TEXT_OK,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = PADDING_SMALL)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { isOpenState.value = false },
                modifier = Modifier.padding(bottom = PADDING_SMALL)
            ) {
                Text(
                    text = TEXT_CANCEL,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        colors = DatePickerDefaults.colors(
            titleContentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        DatePicker(state = selectionDateState)
    }
}