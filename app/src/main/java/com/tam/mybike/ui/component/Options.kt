package com.tam.mybike.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.tam.mybike.R
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.SIZE_TEXT_SMALL
import com.tam.mybike.ui.theme.SIZE_TEXT_X_SMALL
import com.tam.mybike.ui.theme.TEXT_DELETE
import com.tam.mybike.ui.theme.TEXT_EDIT
import com.tam.mybike.ui.theme.TEXT_OPTIONS

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
                modifier = Modifier.size(14.dp) // TODO add to constants
            )
        },
        text = {
            Text(
                text = text,
                fontSize = SIZE_TEXT_X_SMALL
            )
        },
        onClick = {
            onSelect()
            onDismiss()
        }
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