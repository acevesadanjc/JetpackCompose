package com.compose.hilt.ui.features.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Bottom Bar Navigation
 *
 * Los
 *
 */
@Composable
fun BottomNavigationView() {
    var indexState by remember { mutableIntStateOf(0) }

    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
        //val navBackStackEntry by navController.currentBackStackEntryAsState()
        //var currentDestination = navBackStackEntry?.destination
        //topLevelRoutes.forEach { topLevelRoute ->
        //Items del Bottom Navigation, icono y texto
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home",) },
            label = { Text(text = "Home",) },
            selected = true,
            onClick = {
                indexState = 0
                /*navController.navigate(topLevelRoute.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
                */
            }
        )
        //}
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Contacts, contentDescription = "Contacts") },
            label = { Text("Contacts") },
            selected = indexState == 1,
            onClick = { indexState = 1 }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = indexState == 2,
            onClick = { indexState = 2 }
        )
    }
}