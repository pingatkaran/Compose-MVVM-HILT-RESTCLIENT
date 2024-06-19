package com.app.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.firstcomposeapp.screens.DetailsScreen
import com.app.firstcomposeapp.screens.ListScreen
import com.app.firstcomposeapp.screens.MainScreen
import com.app.firstcomposeapp.screens.StickyHeaders
import com.app.firstcomposeapp.screens.setupBottomNavigationScreen
import com.app.firstcomposeapp.utils.Screen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(route = Screen.List.route) {
            ListScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController, onClick = { user ->
                val userJson = Gson().toJson(user)
                navController.navigate(Screen.Details.createRoute(userJson))
            })
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("userJson") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(navController = navController, navBackStackEntry = backStackEntry)
        }
        composable(route = Screen.Sticky.route) {
            StickyHeaders(navController = navController, onClick = {
                navController.navigate(Screen.Sticky.route)
            })
        }
        composable(route = Screen.BottomNavigation.route) {
            setupBottomNavigationScreen()
        }
    }
}