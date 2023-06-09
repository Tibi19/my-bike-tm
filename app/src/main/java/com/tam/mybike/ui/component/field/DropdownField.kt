package com.tam.mybike.ui.component.field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tam.mybike.R
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.component.popup.DropdownChoicesMenu
import com.tam.mybike.ui.theme.BlueGray
import com.tam.mybike.ui.theme.OFFSET_Y_DROPDOWN_ICON
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_EXTEND_FIELD_CHOICES

data class ChoiceHolder(
    val key: String,
    val text: String,
)

@Composable
fun DropdownField(
    selectedChoice: ChoiceHolder,
    onChoiceChange: (ChoiceHolder) -> Unit,
    choices: List<ChoiceHolder>,
    label: String,
    dropdownHorizontalPadding: Dp,
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
    dropdownItemIcon: (@Composable () -> Unit)? = null
) {
    val isDropdownExpandedState = remember { mutableStateOf(false) }
    val borderColor by animateColorAsState(
        targetValue = if (isDropdownExpandedState.value) {
            MaterialTheme.colorScheme.inversePrimary
        } else {
            BlueGray
        }
    )

    Column(modifier = modifier.fillMaxWidth()) {
        FieldLabel(
            label = label,
            isRequired = isRequired
        )
        FieldContent(
            text = selectedChoice.text,
            onClick = { isDropdownExpandedState.value = true },
            borderColor = borderColor,
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_dropdown),
                    contentDescription = TEXT_EXTEND_FIELD_CHOICES,
                    modifier = Modifier
                        .offset(y = OFFSET_Y_DROPDOWN_ICON)
                        .clickable { isDropdownExpandedState.value = true }
                )
            }
        )
        FieldSupportText(isAlertOn = false)

        DropdownChoicesMenu(
            isExpandedState = isDropdownExpandedState,
            selectedChoice = selectedChoice,
            onChoiceChange = onChoiceChange,
            choices = choices,
            dropdownHorizontalPadding = dropdownHorizontalPadding,
            dropdownItemIcon = dropdownItemIcon
        )
    }
}

@Preview
@Composable
private fun DropdownFieldPreview() =
    WrapHeightPreview {
        var selectedChoice by remember {
            mutableStateOf(ChoiceHolder(key = "2", "Highroad Scout 220"))
        }
        DropdownField(
            selectedChoice = selectedChoice,
            onChoiceChange = { newChoice ->
                selectedChoice = newChoice
            },
            choices = listOf(
                ChoiceHolder(key = "0", "E-Bike Cannondale"),
                ChoiceHolder(key = "1", "Nukeproof Scout 290"),
                ChoiceHolder(key = "2", "Highroad Scout 220"),
                ChoiceHolder(key = "3", "MTB Brawler"),
                ChoiceHolder(key = "4", "Alpine Penguin 140")
            ),
            label = "Bike",
            dropdownHorizontalPadding = PADDING_SMALL,
            modifier = Modifier.padding(PADDING_LARGE)
        )
    }