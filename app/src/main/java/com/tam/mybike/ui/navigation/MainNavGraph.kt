package com.tam.mybike.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tam.mybike.ui.navigation.util.navigateAndReplaceStartRoute
import com.tam.mybike.ui.screen.bike.add.AddBikeScreen
import com.tam.mybike.ui.screen.bike.add.AddBikeViewModel
import com.tam.mybike.ui.screen.bike.details.BikeDetailsScreen
import com.tam.mybike.ui.screen.bike.details.BikeDetailsViewModel
import com.tam.mybike.ui.screen.bike.edit.EditBikeScreen
import com.tam.mybike.ui.screen.bike.edit.EditBikeViewModel
import com.tam.mybike.ui.screen.bikes.BikesScreen
import com.tam.mybike.ui.screen.bikes.BikesViewModel
import com.tam.mybike.ui.screen.ride.add.AddRideScreen
import com.tam.mybike.ui.screen.ride.add.AddRideViewModel
import com.tam.mybike.ui.screen.ride.edit.EditRideScreen
import com.tam.mybike.ui.screen.ride.edit.EditRideViewModel
import com.tam.mybike.ui.screen.rides.RidesScreen
import com.tam.mybike.ui.screen.rides.RidesViewModel
import com.tam.mybike.ui.screen.settings.SettingsScreen
import com.tam.mybike.ui.screen.settings.SettingsViewModel
import com.tam.mybike.ui.screen.splash.SplashScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) =
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route,
        modifier = modifier
    ) {
        composable(route = Destination.Splash.route) {
            SplashScreen(
                goToBikesScreen = {
                    val bikesRoute = Destination.Bikes.route
                    navController.navigateAndReplaceStartRoute(bikesRoute)
                }
            )
        }

        composable(route = Destination.Bikes.route) {
            val bikesViewModel = hiltViewModel<BikesViewModel>()
            BikesScreen(
                stateFlow = bikesViewModel.state,
                onEvent = bikesViewModel::onEvent,
                goToAddBike = {
                    val addBikeRoute = Destination.AddBike.route
                    navController.navigate(addBikeRoute)
                },
                goToEditBike = { bikeId ->
                    val editBikeRoute = Destination.EditBike.createRoute(bikeId)
                    navController.navigate(editBikeRoute)
                },
                goToBikeDetails = { bikeId ->
                    val bikeDetailsRoute = Destination.BikeDetails.createRoute(bikeId)
                    navController.navigate(bikeDetailsRoute)
                }
            )
        }

        composable(route = Destination.Rides.route) {
            val ridesViewModel = hiltViewModel<RidesViewModel>()
            RidesScreen(
                stateFlow = ridesViewModel.state,
                onEvent = ridesViewModel::onEvent,
                goToAddRide = {
                    val addRideRoute = Destination.AddRide.route
                    navController.navigate(addRideRoute)
                },
                goToEditRide = { rideId ->
                    val editRideRoute = Destination.EditRide.createRoute(rideId)
                    navController.navigate(editRideRoute)
                }
            )
        }

        composable(route = Destination.Settings.route) {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(
                stateFlow = settingsViewModel.state,
                onEvent = settingsViewModel::onEvent
            )
        }

        composable(route = Destination.AddBike.route) {
            val addBikeViewModel = hiltViewModel<AddBikeViewModel>()
            AddBikeScreen(
                stateFlow = addBikeViewModel.state,
                onEvent = addBikeViewModel::onEvent,
                goToPreviousScreen = { navController.popBackStack() }
            )
        }

        composable(route = Destination.AddRide.route) {
            val addRideViewModel = hiltViewModel<AddRideViewModel>()
            AddRideScreen(
                stateFlow = addRideViewModel.state,
                onEvent = addRideViewModel::onEvent,
                goToPreviousScreen = { navController.popBackStack() }
            )
        }

        composable(
            route = Destination.BikeDetails.route,
            arguments = listOf(
                navArgument(ARG_BIKE_ID) { type = NavType.IntType }
            )
        ) {
            val bikeDetailsViewModel = hiltViewModel<BikeDetailsViewModel>()
            BikeDetailsScreen(
                stateFlow = bikeDetailsViewModel.state,
                onEvent = bikeDetailsViewModel::onEvent,
                goToPreviousScreen = { navController.popBackStack() },
                goToEditBike = { bikeId ->
                    val editBikeRoute = Destination.EditBike.createRoute(bikeId)
                    navController.navigate(editBikeRoute)
                },
                goToEditRide = { rideId ->
                    val editRideRoute = Destination.EditRide.createRoute(rideId)
                    navController.navigate(editRideRoute)
                }
            )
        }

        composable(
            route = Destination.EditBike.route,
            arguments = listOf(
                navArgument(ARG_BIKE_ID) { type = NavType.IntType }
            )
        ) {
            val editBikeViewModel = hiltViewModel<EditBikeViewModel>()
            EditBikeScreen(
                stateFlow = editBikeViewModel.state,
                onEvent = editBikeViewModel::onEvent,
                goToPreviousScreen = { navController.popBackStack() }
            )
        }

        composable(
            route = Destination.EditRide.route,
            arguments = listOf(
                navArgument(ARG_RIDE_ID) { type = NavType.IntType }
            )
        ) {
            val editRideViewModel = hiltViewModel<EditRideViewModel>()
            EditRideScreen(
                stateFlow = editRideViewModel.state,
                onEvent = editRideViewModel::onEvent,
                goToPreviousScreen = { navController.popBackStack() }
            )
        }

    }