package com.tam.mybike.ui.component.field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.theme.BlueGray
import com.tam.mybike.ui.theme.HEIGHT_INPUT_FIELD
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.WEIGHT_FILL
import com.tam.mybike.ui.theme.WIDTH_FIELD_BORDER

@Composable
fun InputField(
    value: String,
    onValueChange: (newValue: String) -> Unit,
    label: String,
    isRequired: Boolean,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingContent: (@Composable () -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocusedByInteractionSource by interactionSource.collectIsFocusedAsState()
    var isAlertOn by remember { mutableStateOf(false) }
    val borderColor by animateColorAsState(
        targetValue = if (isAlertOn) {
            MaterialTheme.colorScheme.error
        } else if (isFocusedByInteractionSource) {
            MaterialTheme.colorScheme.inversePrimary
        } else {
            BlueGray
        }
    )

    Column(modifier = modifier.fillMaxWidth()) {
        FieldLabel(label = label, isRequired = isRequired)
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
                if (isAlertOn && newValue.isNotEmpty()) {
                    isAlertOn = false
                }
            },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            textStyle = MaterialTheme.typography.labelLarge,
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                InputFieldDecoration(
                    innerTextField = innerTextField,
                    trailingContent = trailingContent,
                    borderColor = borderColor,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(HEIGHT_INPUT_FIELD)
                .onFocusChanged { focusState ->
                    if (!isRequired) return@onFocusChanged

                    val isTransitioningFromFocusToUnfocus = isFocusedByInteractionSource && !focusState.isFocused
                    if (isTransitioningFromFocusToUnfocus && value.isEmpty()) {
                        isAlertOn = true
                    }
                }
        )
        FieldSupportText(isAlertOn = isAlertOn)
    }
}

@Composable
private fun InputFieldDecoration(
    innerTextField: @Composable () -> Unit,
    trailingContent: (@Composable () -> Unit)?,
    borderColor: Color,
    modifier: Modifier = Modifier
) =
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .border(
                border = BorderStroke(
                    width = WIDTH_FIELD_BORDER,
                    color = borderColor,
                ),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(PADDING_MEDIUM))
            Row(modifier = Modifier.weight(WEIGHT_FILL)) {
                innerTextField()
            }
            trailingContent?.let { content ->
                Spacer(modifier = Modifier.width(PADDING_XX_SMALL))
                content()
            } ?: Spacer(modifier = Modifier.width(PADDING_MEDIUM))
        }
    }

@Composable
fun InputField(
    value: String,
    onValueChange: (newValue: String) -> Unit,
    label: String,
    isRequired: Boolean,
    trailingText: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) =
    InputField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        isRequired = isRequired,
        keyboardOptions = keyboardOptions,
        trailingContent = {
            Text(
                text = trailingText,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(end = PADDING_LARGE)
            )
        },
        modifier = modifier
    )

@Preview
@Composable
private fun InputFieldPreview() =
    WrapHeightPreview {
        InputField(
            value = "60",
            onValueChange = {},
            label = "Distance",
            isRequired = true,
            modifier = Modifier.padding(PADDING_LARGE)
        )
    }

@Preview
@Composable
private fun InputFieldWithTrailingTextPreview() =
    WrapHeightPreview {
        InputField(
            value = "60",
            onValueChange = {},
            label = "Distance",
            isRequired = true,
            trailingText = "KM",
            modifier = Modifier.padding(PADDING_LARGE)
        )
    }