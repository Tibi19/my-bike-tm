package com.tam.mybike.ui.component.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tam.mybike.ui.component.popup.Options
import com.tam.mybike.ui.theme.PADDING_OPTIONS_BUTTON_DEFAULT
import com.tam.mybike.ui.theme.PADDING_SMALL

@Composable
fun ElementBox(
    onEditMenuOption: () -> Unit,
    onDeleteMenuOption: () -> Unit,
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(PADDING_SMALL),
    optionsPaddingValues: PaddingValues = PaddingValues(PADDING_OPTIONS_BUTTON_DEFAULT),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable BoxScope.() -> Unit
) =
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(innerPaddingValues)
    ) {
        content()
        Options(
            onEditMenuOption = onEditMenuOption,
            onDeleteMenuOption = onDeleteMenuOption,
            modifier = Modifier
                .padding(optionsPaddingValues)
                .align(Alignment.TopEnd)
        )
    }