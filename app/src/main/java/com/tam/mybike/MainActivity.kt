package com.tam.mybike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tam.mybike.ui.component.field.ChoiceHolder
import com.tam.mybike.ui.component.field.DropdownField
import com.tam.mybike.ui.theme.MyBikeTheme
import com.tam.mybike.ui.theme.PADDING_SMALL
import com.tam.mybike.ui.theme.SIZE_RIDE_ICON

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                            .height(200.dp)
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                            .padding(PADDING_SMALL)
                    ) {
                        val selectecChoiceState = remember {
                            mutableStateOf(
                                ChoiceHolder(key = "2", "Highroad Scout 220")
                            )
                        }
                        DropdownField(
                            selectedChoiceState = selectecChoiceState,
                            choices = listOf(
                                ChoiceHolder(key = "0", "E-Bike Cannondale"),
                                ChoiceHolder(key = "1", "Nukeproof Scout 290"),
                                ChoiceHolder(key = "2", "Highroad Scout 220"),
                                ChoiceHolder(key = "3", "MTB Brawler"),
                                ChoiceHolder(key = "4", "Alpine Penguin 140")
                            ),
                            label = "Bike",
                            dropdownHorizontalPadding = PADDING_SMALL,
                            dropdownItemIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.bike_placeholder),
                                    contentDescription = "Bike choice",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(SIZE_RIDE_ICON)
                                )
                            }
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyBikeTheme {
        Greeting("Android")
    }
}