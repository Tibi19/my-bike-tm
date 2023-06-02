package com.tam.mybike.ui.component.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import com.tam.mybike.R
import com.tam.mybike.ui.component.WrapHeightPreview
import com.tam.mybike.ui.theme.HEIGHT_OPTIONS_ITEM
import com.tam.mybike.ui.theme.OFFSET_X_OPTIONS_ITEM_TEXT_TOWARDS_ICON
import com.tam.mybike.ui.theme.OFFSET_X_OPTIONS_MENU
import com.tam.mybike.ui.theme.OFFSET_Y_OPTIONS_MENU
import com.tam.mybike.ui.theme.PADDING_OPTIONS_ITEM_END
import com.tam.mybike.ui.theme.SIZE_OPTIONS_ITEM_ICON
import com.tam.mybike.ui.theme.SIZE_TEXT_X_SMALL
import com.tam.mybike.ui.theme.TEXT_DELETE
import com.tam.mybike.ui.theme.TEXT_EDIT
import com.tam.mybike.ui.theme.TEXT_OPTIONS
import com.tam.mybike.ui.theme.WIDTH_OPTIONS_ITEM

@Composable
fun Options(
    onEditMenuOption: () -> Unit,
    onDeleteMenuOption: () -> Unit,
    modifier: Modifier = Modifier
) =
    Box(modifier = modifier) {
        var isMenuExpanded by remember { mutableStateOf(false) }

        Icon(
            painter = painterResource(id = R.drawable.icon_more),
            contentDescription = TEXT_OPTIONS,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable { isMenuExpanded = !isMenuExpanded }
        )

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = !isMenuExpanded },
            offset = DpOffset(x = OFFSET_X_OPTIONS_MENU, y = OFFSET_Y_OPTIONS_MENU),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiary)
        ) {
            OptionsItem(
                onSelect = onEditMenuOption,
                onDismiss = { isMenuExpanded = false },
                iconPainter = painterResource(id = R.drawable.icon_edit),
                text = TEXT_EDIT
            )
            OptionsItem(
                onSelect = onDeleteMenuOption,
                onDismiss = { isMenuExpanded = false },
                iconPainter = painterResource(id = R.drawable.icon_delete),
                text = TEXT_DELETE
            )
        }

    }

@Composable
private fun OptionsItem(
    onSelect: () -> Unit,
    onDismiss: () -> Unit,
    iconPainter: Painter,
    text: String
) =
    DropdownMenuItem(
        leadingIcon = {
            Icon(
                painter = iconPainter,
                contentDescription = text,
                modifier = Modifier.size(SIZE_OPTIONS_ITEM_ICON)
            )
        },
        text = {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = SIZE_TEXT_X_SMALL,
                modifier = Modifier.offset(x = OFFSET_X_OPTIONS_ITEM_TEXT_TOWARDS_ICON)
            )
        },
        onClick = {
            onSelect()
            onDismiss()
        },
        contentPadding = PaddingValues(
            start = MenuDefaults
                .DropdownMenuItemContentPadding
                .calculateStartPadding(LayoutDirection.Ltr),
            end = PADDING_OPTIONS_ITEM_END
        ),
        modifier = Modifier
            .width(WIDTH_OPTIONS_ITEM)
            .height(HEIGHT_OPTIONS_ITEM)
    )

@Preview
@Composable
fun OptionsPreview() =
    WrapHeightPreview {
        Options(
            onEditMenuOption = {},
            onDeleteMenuOption = {},
        )
    }