package com.tam.mybike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.tam.mybike.ui.component.text.Title
import com.tam.mybike.ui.theme.MyBikeTheme
import com.tam.mybike.ui.theme.PADDING_LARGE
import com.tam.mybike.ui.theme.PADDING_MEDIUM
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.TEXT_BIKES
import com.tam.mybike.ui.theme.TEXT_MISSING_ICON_CONTENT

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBikeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {

                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                            .fillMaxSize()
                    ) {

                        Column(modifier = Modifier.padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL)) {
                            Title(
                                text = TEXT_BIKES,
                                modifier = Modifier.padding(bottom = PADDING_LARGE)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.missing_bike_card),
                                contentDescription = TEXT_MISSING_ICON_CONTENT,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                    }

                }
            }
        }
    }
}