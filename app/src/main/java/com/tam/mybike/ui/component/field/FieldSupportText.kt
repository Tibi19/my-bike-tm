package com.tam.mybike.ui.component.field

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tam.mybike.ui.theme.HEIGHT_REQUIRED_ALERT
import com.tam.mybike.ui.theme.PADDING_X_SMALL
import com.tam.mybike.ui.theme.TEXT_REQUIRED_FIELD

@Composable
fun FieldSupportText(isAlertOn: Boolean) {
    if (isAlertOn) {
        Text(
            text = TEXT_REQUIRED_FIELD,
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier
                .padding(top = PADDING_X_SMALL)
                .height(HEIGHT_REQUIRED_ALERT)
        )

        return
    }

    Spacer(
        modifier = Modifier
            .padding(top = PADDING_X_SMALL)
            .height(HEIGHT_REQUIRED_ALERT)
    )
}