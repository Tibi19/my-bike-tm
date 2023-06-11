package com.tam.mybike.ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tam.mybike.R
import com.tam.mybike.ui.navigation.util.navigateAndReplaceStartRoute
import com.tam.mybike.ui.theme.BlueLight
import com.tam.mybike.ui.theme.DURATION_BOTTOM_BAR_TEXT_COLOR_ANIMATION
import com.tam.mybike.ui.theme.HEIGHT_BOTTOM_BAR
import com.tam.mybike.ui.theme.TEXT_BIKES
import com.tam.mybike.ui.theme.TEXT_BOTTOM_BAR_ITEM_CONTENT_END
import com.tam.mybike.ui.theme.TEXT_BOTTOM_BAR_ITEM_CONTENT_START
import com.tam.mybike.ui.theme.TEXT_RIDES
import com.tam.mybike.ui.theme.TEXT_SETTINGS

private val destinationsWithBottomBar = listOf(
    Destination.Bikes,
    Destination.Rides,
    Destination.Settings
)

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isScreenWithBottomBar = destinationsWithBottomBar
        .any { destination ->
            destination.route == currentDestination?.route
        }
    if (!isScreenWithBottomBar) return

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.height(HEIGHT_BOTTOM_BAR)
    ) {

        BottomBarItem(
            navController = navController,
            currentDestination = currentDestination,
            activeIconPainter = painterResource(id = R.drawable.icon_bikes_active),
            inactiveIconPainter = painterResource(id = R.drawable.icon_bikes_inactive),
            itemRoute = Destination.Bikes.route,
            label = TEXT_BIKES
        )

        BottomBarItem(
            navController = navController,
            currentDestination = currentDestination,
            activeIconPainter = painterResource(id = R.drawable.rides_active),
            inactiveIconPainter = painterResource(id = R.drawable.rides_inactive),
            itemRoute = Destination.Rides.route,
            label = TEXT_RIDES
        )

        BottomBarItem(
            navController = navController,
            currentDestination = currentDestination,
            activeIconPainter = painterResource(id = R.drawable.settings_active),
            inactiveIconPainter = painterResource(id = R.drawable.settings_inactive),
            itemRoute = Destination.Settings.route,
            label = TEXT_SETTINGS
        )

    }
}

@Composable
private fun RowScope.BottomBarItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
    activeIconPainter: Painter,
    inactiveIconPainter: Painter,
    itemRoute: String,
    label: String
) {
    val isSelected = currentDestination
        ?.hierarchy
        ?.any { destination ->
            destination.route == itemRoute
        } == true
    val iconPainter = if (isSelected) activeIconPainter else inactiveIconPainter
    val selectionColor by animateColorAsState(
        targetValue = if (isSelected) BlueLight else MaterialTheme.colorScheme.secondary,
        animationSpec = tween(durationMillis = DURATION_BOTTOM_BAR_TEXT_COLOR_ANIMATION)
    )
    val contentDescription = "$TEXT_BOTTOM_BAR_ITEM_CONTENT_START $label $TEXT_BOTTOM_BAR_ITEM_CONTENT_END"

    BottomNavigationItem(
        selected = isSelected,
        selectedContentColor = BlueLight,
        unselectedContentColor = MaterialTheme.colorScheme.secondary,
        icon = {
            Icon(
                painter = iconPainter,
                contentDescription = contentDescription,
            )
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = selectionColor
            )
        },
        onClick = {
            navController.navigateAndReplaceStartRoute(itemRoute)
        }
    )

}