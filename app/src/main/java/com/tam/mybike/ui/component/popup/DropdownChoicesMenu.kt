package com.tam.mybike.ui.component.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.tam.mybike.ui.component.field.ChoiceHolder
import com.tam.mybike.ui.theme.FACTOR_SELECTED_DROPDOWN_CHOICE_DARKNESS
import com.tam.mybike.ui.theme.HEIGHT_DROPDOWN_FIELD_ITEM
import com.tam.mybike.ui.theme.OFFSET_X_DROPDOWN_FIELD_MENU
import com.tam.mybike.ui.theme.OFFSET_Y_DROPDOWN_FIELD_MENU
import com.tam.mybike.ui.theme.PADDING_DROPDOWN_CHOICE_END
import com.tam.mybike.ui.theme.SIZE_TEXT_X_SMALL
import com.tam.mybike.ui.util.darken

@Composable
fun DropdownChoicesMenu(
    isExpandedState: MutableState<Boolean>,
    selectedChoice: ChoiceHolder,
    onChoiceChange: (ChoiceHolder) -> Unit,
    choices: List<ChoiceHolder>,
    dropdownHorizontalPadding: Dp,
    modifier: Modifier = Modifier,
    dropdownItemIcon: (@Composable () -> Unit)? = null
) =
    DropdownMenu(
        expanded = isExpandedState.value,
        onDismissRequest = { isExpandedState.value = false },
        offset = DpOffset(x = OFFSET_X_DROPDOWN_FIELD_MENU, y = OFFSET_Y_DROPDOWN_FIELD_MENU),
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.tertiary)
            .width(getDropdownWidth(dropdownHorizontalPadding))
    ) {
        choices.forEach { choice ->
            val backgroundColor = if (choice == selectedChoice) {
                MaterialTheme.colorScheme.tertiary
                    .darken(darkenFactor = FACTOR_SELECTED_DROPDOWN_CHOICE_DARKNESS)
            } else {
                MaterialTheme.colorScheme.tertiary
            }
            DropdownMenuItem(
                text = {
                    Text(
                        text = choice.text,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = SIZE_TEXT_X_SMALL
                    )
                },
                onClick = {
                    onChoiceChange(choice)
                    isExpandedState.value = false
                },
                contentPadding = PaddingValues(
                    start = MenuDefaults
                        .DropdownMenuItemContentPadding
                        .calculateStartPadding(LayoutDirection.Ltr),
                    end = PADDING_DROPDOWN_CHOICE_END
                ),
                leadingIcon = dropdownItemIcon,
                modifier = Modifier
                    .background(color = backgroundColor)
                    .height(HEIGHT_DROPDOWN_FIELD_ITEM)
            )
        }
    }

// Dropdown is built inside a container, normal padding will simply add padding inside the container, leaving a black background behind
// So we calculate the width to simulate the passed horizontal padding and a fillMaxWidth modifier
@Composable
private fun getDropdownWidth(dropdownHorizontalPadding: Dp): Dp =
    with(LocalConfiguration.current) {
        val screenWidth = screenWidthDp.dp
        screenWidth - (dropdownHorizontalPadding + dropdownHorizontalPadding)
    }