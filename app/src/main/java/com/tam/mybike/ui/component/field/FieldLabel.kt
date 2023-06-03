package com.tam.mybike.ui.component.field

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.tam.mybike.R
import com.tam.mybike.ui.theme.GrayLight
import com.tam.mybike.ui.theme.HEIGHT_REQUIRED_ICON
import com.tam.mybike.ui.theme.OFFSET_Y_REQUIRED_ICON
import com.tam.mybike.ui.theme.PADDING_XX_SMALL
import com.tam.mybike.ui.theme.TEXT_REQUIRED_FIELD_CONTENT

@Composable
fun FieldLabel(
    label: String,
    isRequired: Boolean
) =
    Row(modifier = Modifier.padding(bottom = PADDING_XX_SMALL)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.inversePrimary
            )
        )
        if (isRequired) {
            Icon(
                painter = painterResource(id = R.drawable.icon_required),
                contentDescription = TEXT_REQUIRED_FIELD_CONTENT,
                tint = GrayLight,
                modifier = Modifier
                    .height(HEIGHT_REQUIRED_ICON)
                    .padding(start = PADDING_XX_SMALL)
                    .offset(y = OFFSET_Y_REQUIRED_ICON)
            )
        } else {
            Spacer(modifier = Modifier.height(HEIGHT_REQUIRED_ICON))
        }
    }