package com.app.firstcomposeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.app.firstcomposeapp.models.Data
import com.app.firstcomposeapp.utils.Screen
import com.app.firstcomposeapp.viewmodel.UsersViewModel
import com.google.gson.Gson


@Composable
fun MainScreen(navController: NavHostController,onClick: (data: Data) -> Unit) {

    val usersViewModel: UsersViewModel = hiltViewModel()
    val users: State<List<Data>> = usersViewModel.users.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        items(users.value) { user ->
            UserItem(data = user, onClick = {
                val userJson = Gson().toJson(user)
                navController.navigate(Screen.Details.createRoute(userJson))
            })
        }
    }

    Text(text = "Sticky Headers", modifier = Modifier.clickable {
        navController.navigate(Screen.Sticky.route)
    })
}


@Composable
fun UserItem(data: Data, onClick: (data: Data) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(180.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEEEEEE))
            .clickable { onClick(data) },
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(data.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${data.first_name} ${data.last_name}",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = data.email,
                fontSize = 14.sp,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}