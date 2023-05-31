package com.tam.mybike.ui.component

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
import com.tam.mybike.ui.theme.PADDING_SMALL

@Composable
fun ElementBox(
    onEditMenuOption: () -> Unit,
    onDeleteMenuOption: () -> Unit,
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(horizontal = PADDING_SMALL, vertical = PADDING_SMALL),
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
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }