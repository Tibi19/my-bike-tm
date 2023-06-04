package com.tam.mybike.ui.component.field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.component.popup.DurationDialog
import com.tam.mybike.ui.theme.BlueGray
import com.tam.mybike.ui.theme.TEXT_DURATION
import com.tam.mybike.ui.theme.TEXT_DURATION_DESCRIPTION_HOURS
import com.tam.mybike.ui.theme.TEXT_DURATION_DESCRIPTION_MINUTES

data class DurationState(
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String =
        "$hours$TEXT_DURATION_DESCRIPTION_HOURS$minutes$TEXT_DURATION_DESCRIPTION_MINUTES"
}

@Composable
fun DurationField(
    durationState: MutableState<DurationState>,
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
) {
    val isDurationPickerOpenState = remember { mutableStateOf(false) }
    val borderColor by animateColorAsState(
        targetValue = if (isDurationPickerOpenState.value) {
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
            text = durationState.value.toString(),
            onClick = { isDurationPickerOpenState.value = true },
            borderColor = borderColor
        )
        FieldSupportText(isAlertOn = false)
    }

    DurationDialog(
        isOpenState = isDurationPickerOpenState,
        durationState = durationState
    )
}