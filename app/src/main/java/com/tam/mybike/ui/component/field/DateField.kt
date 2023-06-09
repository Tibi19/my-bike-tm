package com.tam.mybike.ui.component.field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.component.popup.DateDialog
import com.tam.mybike.ui.theme.BlueGray
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.TEXT_DATE
import com.tam.mybike.ui.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    dateState: DatePickerState,
    onDateChange: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    isRequired: Boolean = true
) {
    val isDatePickerOpenState = remember { mutableStateOf(false) }
    val borderColor by animateColorAsState(
        targetValue = if (isDatePickerOpenState.value) {
            MaterialTheme.colorScheme.inversePrimary
        } else {
            BlueGray
        }
    )

    Column(modifier = modifier.fillMaxWidth()) {
        FieldLabel(
            label = TEXT_DATE,
            isRequired = isRequired
        )
        FieldContent(
            text = formatDate(
                dateMillis = dateState.selectedDateMillis ?: System.currentTimeMillis()
            ),
            onClick = { isDatePickerOpenState.value = true },
            borderColor = borderColor
        )
        FieldSupportText(isAlertOn = false)
    }

    DateDialog(
        isOpenState = isDatePickerOpenState,
        dateState = dateState,
        onDateChange = onDateChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DateFieldPreview() =
    WrapHeightPreview {
        val dateState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
        DateField(
            dateState = dateState,
            onDateChange = {},
            modifier = Modifier.padding(PADDING_LARGE)
        )
    }