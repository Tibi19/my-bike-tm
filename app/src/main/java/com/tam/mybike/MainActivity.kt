package com.tam.mybike

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.component.RidesStatistics
import com.tam.mybike.ui.component.button.DefaultSwitch
import com.tam.mybike.ui.screen.settings.SettingsEvent
import com.tam.mybike.ui.theme.BikeColors
import com.tam.mybike.ui.theme.MyBikeTheme
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_X_SMALL

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBikeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {

                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                            .fillMaxSize()
                    ) {

                        var isSwitchOn by remember { mutableStateOf(true) }
                        DefaultSwitch(
                            isOn = isSwitchOn,
                            onSwitch = {
                                isSwitchOn = !isSwitchOn
                            },
                            modifier = Modifier
                                .padding(start = PADDING_MEDIUM, end = PADDING_X_SMALL)
                        )

                    }

                }

            }
        }
    }
}
