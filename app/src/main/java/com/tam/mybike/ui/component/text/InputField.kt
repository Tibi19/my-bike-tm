package com.tam.mybike.ui.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.R
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.theme.GrayLight
import com.tam.mybike.ui.theme.HEIGHT_IN_INPUT_FIELD
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.SIZE_TEXT_SMALL
import com.tam.mybike.ui.theme.TEXT_REQUIRED_FIELD

sealed class InputRequirement {
    data class Required(val isInputOkState: State<Boolean>): InputRequirement()
    object NotRequired : InputRequirement()

    val isRequired get() = this is Required
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    onValueChange: (newValue: String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    inputRequirement: InputRequirement = InputRequirement.NotRequired,
    trailingText: String? = null
) =
    Column(modifier = modifier.fillMaxWidth()) {
        TextFieldDefaults.textFieldColors()
        InputFieldLabel(label = label, inputRequirement = inputRequirement)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            trailingIcon = {
                trailingText ?: return@OutlinedTextField
                Text(
                    text = trailingText,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = PADDING_LARGE)
                )
            },
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium
                .copy(fontSize = SIZE_TEXT_SMALL),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = GrayLight,
                containerColor = MaterialTheme.colorScheme.background,
                textColor = GrayLight
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = HEIGHT_IN_INPUT_FIELD)
        )
    }

@Composable
private fun InputFieldLabel(
    label: String,
    inputRequirement: InputRequirement
) =
    Row(modifier = Modifier.padding(bottom = PADDING_X_SMALL)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.inversePrimary
            )
        )
        if (inputRequirement.isRequired) {
            Icon(
                painter = painterResource(id = R.drawable.icon_required),
                contentDescription = TEXT_REQUIRED_FIELD,
                tint = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.padding(start = PADDING_XX_SMALL)
            )
        }
    }

@Preview
@Composable
fun InputFieldPreview() =
    WrapHeightPreview {
        val isInputOkState = remember { mutableStateOf(false) }
        InputField(
            value = "60",
            onValueChange = {},
            label = "Distance",
            trailingText = "KM",
            inputRequirement = InputRequirement.Required(isInputOkState),
            modifier = Modifier.padding(PADDING_LARGE)
        )
    }