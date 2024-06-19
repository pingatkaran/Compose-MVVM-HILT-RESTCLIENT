package com.app.firstcomposeapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.firstcomposeapp.utils.Screen


@Composable
fun ListScreen(navController: NavHostController) {
    val listofItems = listOf("Rest Client", "Sticky Headers", "Bottom Navigation")
    LazyColumn {
        items(items =  listofItems, itemContent = { item ->
            ListItem(text = item) {
                when (item) {
                    "Rest Client" -> navController.navigate(Screen.Main.route)
                    "Sticky Headers" -> navController.navigate(Screen.Sticky.route)
                    "Bottom Navigation" -> navController.navigate(Screen.BottomNavigation.route)
                    // Add more navigation cases as needed
                }
            }
        })
    }
}


@Composable
fun ListItem(text: String, onClick: () -> Unit) {
    Text(text = text, modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .padding(16.dp))
}