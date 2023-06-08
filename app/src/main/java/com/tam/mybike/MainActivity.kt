package com.tam.mybike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.ui.component.RidesStatistics
import com.tam.mybike.ui.theme.MyBikeTheme
import com.tam.mybike.ui.theme.PADDING_MEDIUM

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

                    RidesStatistics(
                        bikeTypeToDistanceMap = mapOf(
                            BikeType.ROADBIKE to Distance(8500, DistanceUnit.KM),
                            BikeType.MTB to Distance(2650, DistanceUnit.KM),
                            BikeType.HYBRID to Distance(3420, DistanceUnit.KM),
                            BikeType.ELECTRIC to Distance(11000, DistanceUnit.KM)
                        ),
                        totalDistance = Distance(25570, DistanceUnit.KM),
                        modifier = Modifier.padding(PADDING_MEDIUM)
                    )

                }
            }
        }
    }
}