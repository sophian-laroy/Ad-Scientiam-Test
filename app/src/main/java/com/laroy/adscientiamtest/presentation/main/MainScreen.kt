package com.laroy.adscientiamtest.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.laroy.adscientiamtest.R
import com.laroy.adscientiamtest.presentation.position.PositionsScreen
import com.laroy.adscientiamtest.presentation.settings.SettingsScreen
import com.laroy.adscientiamtest.presentation.drag.DragScreen
import com.laroy.adscientiamtest.presentation.IconResource

const val DRAG_SCREEN_ROUTE = "drag"
const val POSITIONS_SCREEN_ROUTE = "positions"
const val SETTINGS_SCREEN_ROUTE = "settings"

// Main Tabs
private val tabItems = listOf(
    DRAG_SCREEN_ROUTE,
    POSITIONS_SCREEN_ROUTE,
    SETTINGS_SCREEN_ROUTE
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(id = R.string.drag),
                        route = DRAG_SCREEN_ROUTE,
                        icon = IconResource.fromDrawableResource(R.drawable.ic_drag)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.positions),
                        route = POSITIONS_SCREEN_ROUTE,
                        icon = IconResource.fromDrawableResource(R.drawable.ic_history)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.settings),
                        route = SETTINGS_SCREEN_ROUTE,
                        icon = IconResource.fromImageVector(Icons.Default.Settings)
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(navController = navController)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DRAG_SCREEN_ROUTE) {
        composable(DRAG_SCREEN_ROUTE) {
            DragScreen()
        }
        composable(POSITIONS_SCREEN_ROUTE) {
            PositionsScreen()
        }
        composable(SETTINGS_SCREEN_ROUTE) {
            SettingsScreen()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val selectionMap = remember(currentDestination) {
        tabItems.associateWith { tabItem ->
            (currentDestination?.hierarchy?.any { it.route == tabItem } == true)
        }
    }
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = selectionMap[item.route] ?: false
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        onItemClick(item)
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = item.icon.asPainterResource(),
                            contentDescription = item.name
                        )
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}