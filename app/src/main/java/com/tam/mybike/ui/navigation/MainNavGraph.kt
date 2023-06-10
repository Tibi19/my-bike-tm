package com.tam.mybike.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tam.mybike.domain.model.Bike
import com.tam.mybike.domain.model.BikeType
import com.tam.mybike.domain.model.Distance
import com.tam.mybike.domain.model.DistanceUnit
import com.tam.mybike.domain.model.WheelSize
import com.tam.mybike.ui.screen.bikes.BikesScreen
import com.tam.mybike.ui.screen.bikes.BikesState
import com.tam.mybike.ui.theme.BikeColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) =
    NavHost(
        navController = navController,
        startDestination = Destination.Bikes.route,
        modifier = modifier
    ) {
        composable(route = Destination.Bikes.route) {
            val mockBikes = listOf(
                Bike(
                    id = 1,
                    name = "Nukeproof Scout 290",
                    type = BikeType.MTB,
                    wheelSize = WheelSize.BIG,
                    colorHex = BikeColors[4].value,
                    serviceIn = Distance(170, DistanceUnit.KM)
                ),
                Bike(
                    id = 2,
                    name = "E-Bike Cannondale",
                    type = BikeType.ELECTRIC,
                    wheelSize = WheelSize.BIG,
                    colorHex = BikeColors[0].value,
                    serviceIn = Distance(900, DistanceUnit.KM)
                ),
                Bike(
                    id = 3,
                    name = "Highroad Scout 220",
                    type = BikeType.ROADBIKE,
                    wheelSize = WheelSize.SMALL,
                    colorHex = BikeColors[5].value,
                    serviceIn = Distance(200, DistanceUnit.KM)
                )
            )
            val mockState = BikesState(
                bikes = mockBikes,
                bikeToProgressMap = mockBikes.associate { bike ->
                    val maxDistance = 1000f
                    bike.id to ((maxDistance - bike.serviceIn.amount) / maxDistance)
                },
                distanceUnit = DistanceUnit.KM
            )
            val stateFlow = remember {
                MutableStateFlow(mockState).asStateFlow()
            }
            BikesScreen(
                stateFlow = stateFlow,
                onEvent = {},
                goToAddBike = {},
                goToEditBike = {}
            )
        }

        composable(route = Destination.Rides.route) {

        }

        composable(route = Destination.Settings.route) {

        }

        composable(route = Destination.AddBike.route) {

        }

        composable(route = Destination.AddRide.route) {

        }

        composable(
            route = Destination.BikeDetails.route,
            arguments = listOf(
                navArgument(ARG_BIKE_ID) { type = NavType.IntType }
            )
        ) {

        }

        composable(
            route = Destination.EditBike.route,
            arguments = listOf(
                navArgument(ARG_BIKE_ID) { type = NavType.IntType }
            )
        ) {

        }

        composable(
            route = Destination.EditRide.route,
            arguments = listOf(
                navArgument(ARG_RIDE_ID) { type = NavType.IntType }
            )
        ) {

        }
    }