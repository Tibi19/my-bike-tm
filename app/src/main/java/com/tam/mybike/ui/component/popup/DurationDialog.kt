package com.tam.mybike.ui.component.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.NumberPicker
import com.tam.mybike.ui.component.field.DurationState
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_X_LARGE
import com.tam.mybike.ui.theme.SIZE_TEXT_X_LARGE
import com.tam.mybike.ui.theme.TEXT_HOURS
import com.tam.mybike.ui.theme.TEXT_HOURS_MINUTES_DIVIDER
import com.tam.mybike.ui.theme.TEXT_MINUTES
import com.tam.mybike.ui.theme.TEXT_OK
import com.tam.mybike.ui.theme.WIDTH_NUMBER_PICKER

private val RANGE_HOURS = 0..24
private val RANGE_MINUTES = 0..59

@Composable
fun DurationDialog(
    isOpenState: MutableState<Boolean>,
    duration: DurationState,
    onDurationChange: (DurationState) -> Unit,
    onClose: () -> Unit = {}
) {
    if (!isOpenState.value) return

    val hoursState = remember { mutableStateOf(duration.hours) }
    val minutesState = remember { mutableStateOf(duration.minutes) }
    Dialog(onDismissRequest = {
        isOpenState.value = false
        onClose()
    }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = PADDING_LARGE)
                    .padding(top = PADDING_X_LARGE, bottom = PADDING_LARGE)
            ) {
                DurationPicker(
                    hoursState = hoursState,
                    minutesState = minutesState
                )
                DialogButtons(
                    confirmText = TEXT_OK,
                    onConfirm = {
                        isOpenState.value = false
                        val newDuration = DurationState(
                            hours = hoursState.value,
                            minutes = minutesState.value
                        )
                        onDurationChange(newDuration)
                        onClose()
                    },
                    onClose = {
                        isOpenState.value = false
                        onClose()
                    },
                    modifier = Modifier.padding(
                        top = PADDING_MEDIUM,
                        start = PADDING_LARGE,
                        end = PADDING_LARGE
                    )
                )
            }

        }
    }

}

@Composable
private fun DurationPicker(
    hoursState: MutableState<Int>,
    minutesState: MutableState<Int>,
    modifier: Modifier = Modifier
) =
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ColumnNumberPicker(
            pickerState = hoursState,
            title = TEXT_HOURS,
            range = RANGE_HOURS
        )
        Text(
            text = TEXT_HOURS_MINUTES_DIVIDER,
            fontSize = SIZE_TEXT_X_LARGE,
            modifier = Modifier
                .padding(horizontal = PADDING_MEDIUM)
                .padding(top = PADDING_MEDIUM)
        )
        ColumnNumberPicker(
            pickerState = minutesState,
            title = TEXT_MINUTES,
            range = RANGE_MINUTES
        )
    }

@Composable
private fun ColumnNumberPicker(
    pickerState: MutableState<Int>,
    title: String,
    range: IntRange,
    modifier: Modifier = Modifier
) =
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.inversePrimary
            )
        )
        NumberPicker(
            value = pickerState.value,
            onValueChange = { pickerState.value = it },
            range = range,
            dividersColor = MaterialTheme.colorScheme.primary,
            textStyle = MaterialTheme.typography.titleLarge
                .copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .width(WIDTH_NUMBER_PICKER)
                // While scrolling, numbers jump outside of the composable, so we clip to prevent this
                .clip(MaterialTheme.shapes.medium)
        )
    }
