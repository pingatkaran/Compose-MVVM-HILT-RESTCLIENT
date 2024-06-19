package com.app.firstcomposeapp.utils

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {

    object List : Screen("list")
    object Main : Screen("main")
    object Sticky : Screen("sticky")
    object BottomNavigation : Screen("bottom")
    object Details : Screen("details/{userJson}") {
        fun createRoute(userJson: String): String {
            val encodedUserJson = URLEncoder.encode(userJson, StandardCharsets.UTF_8.toString())
            return "details/$encodedUserJson"
        }
    }
}