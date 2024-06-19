package com.app.firstcomposeapp.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.firstcomposeapp.utils.BottomNavigationBar
import com.app.firstcomposeapp.utils.Screen
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun setupBottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
        NavHost(navController, startDestination = Screen.Main.route) {
            composable(Screen.Main.route) {
                MainScreen(navController = navController, onClick = { user ->
                    val userJson = Gson().toJson(user)
                    navController.navigate(Screen.Details.createRoute(userJson))
                })
            }
            composable(Screen.Sticky.route) {
                StickyHeaders(navController = navController) {}
            }
            composable(
                route = Screen.Details.route,
                arguments = listOf(navArgument("userJson") { type = NavType.StringType })
            ) { backStackEntry ->
                DetailsScreen(navController = navController, navBackStackEntry = backStackEntry)
            }
        }
    }
}
