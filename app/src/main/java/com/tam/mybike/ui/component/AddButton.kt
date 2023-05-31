package com.tam.mybike.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tam.mybike.R
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.TEXT_ADD
import com.tam.mybike.ui.theme.TEXT_BIKE

@Composable
fun AddButton(
    elementTitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) =
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.secondary)
    ) {
        val addText = "$TEXT_ADD $elementTitle"
        // TextButton RowScope automatically centers items vertically, resulting in a bad alignment
        // So we create our own Row for control over alignment
        Row {
            Icon(
                painter = painterResource(id = R.drawable.icon_add),
                contentDescription = addText
            )
            Text(
                text = addText,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }

@Preview
@Composable
private fun AddButtonPreview() =
    WrapHeightPreview {
        Row(horizontalArrangement = Arrangement.End) {
            AddButton(
                elementTitle = TEXT_BIKE,
                onClick = {},
                modifier = Modifier.padding(horizontal = PADDING_X_SMALL)
            )
        }
    }
