package com.tam.mybike.ui.component.field

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tam.mybike.ui.component.popup.DurationDialog
import com.tam.mybike.ui.theme.BlueGray
import com.tam.mybike.ui.theme.TEXT_DURATION
import com.tam.mybike.ui.util.getStringFromDuration

data class DurationState(
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String =
        if (isEmpty()) {
            ""
        } else {
            getStringFromDuration(hours, minutes)
        }

    fun isEmpty(): Boolean = hours == 0 && minutes == 0

    fun toMinutes(): Int = hours * MINUTES_IN_HOUR + minutes

    companion object {
        private const val MINUTES_IN_HOUR = 60

        fun fromMinutes(minutes: Int): DurationState {
            val minutesToHour = minutes % MINUTES_IN_HOUR
            val hours = minutes / MINUTES_IN_HOUR
            return DurationState(hours, minutesToHour)
        }
    }
}

@Composable
fun DurationField(
    duration: DurationState,
    onDurationChange: (DurationState) -> Unit,
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
) {
    val isDurationPickerOpenState = remember { mutableStateOf(false) }
    var wasDurationDialogClosed by remember { mutableStateOf(false) }
    val isAlertOn = duration.isEmpty() && wasDurationDialogClosed
    val borderColor by animateColorAsState(
        targetValue = if (isAlertOn) {
            MaterialTheme.colorScheme.error
        } else if (isDurationPickerOpenState.value) {
            MaterialTheme.colorScheme.inversePrimary
        } else {
            BlueGray
        }
    )

    Column(modifier = modifier.fillMaxWidth()) {
        FieldLabel(
            label = TEXT_DURATION,
            isRequired = isRequired
        )
        FieldContent(
            text = duration.toString(),
            onClick = { isDurationPickerOpenState.value = true },
            borderColor = borderColor
        )
        FieldSupportText(isAlertOn = isAlertOn)
    }

    DurationDialog(
        isOpenState = isDurationPickerOpenState,
        duration = duration,
        onDurationChange = onDurationChange,
        onClose = {
            wasDurationDialogClosed = true
        }
    )
}