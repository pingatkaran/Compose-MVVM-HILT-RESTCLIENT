package com.app.firstcomposeapp.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.app.firstcomposeapp.models.Person

@Composable
fun StickyHeaders(navController: NavHostController, onClick: (Person) -> Unit) {
    val persons = remember { mutableStateListOf<Person>() }
    val names = listOf("Shawn Mendes", "Ed Sheeran", "Taylor Swift")
    val imageUrls = listOf(
        "https://images.pexels.com/photos/6485032/pexels-photo-6485032.jpeg?auto=compress&cs=tinysrgb&w=600",
        "https://images.pexels.com/photos/6485033/pexels-photo-6485033.jpeg?auto=compress&cs=tinysrgb&w=600",
        "https://images.pexels.com/photos/6485034/pexels-photo-6485034.jpeg?auto=compress&cs=tinysrgb&w=600",
    )

    LaunchedEffect(Unit) {
        var section = 1
        for (i in 1..200) {
            if (i % 15 == 0) {
                section++
            }
            val name = names[i % names.size]
            val imageUrl = imageUrls[i % imageUrls.size]
            persons.add(
                Person(
                    id = i,
                    section = section,
                    name = name,
                    imageUrl = imageUrl
                )
            )
        }
    }

    LazyColumnClickableAdvStickyDemo(persons) { person ->
        onClick(person)  // Invoke the onClick callback with the selected person
        Log.e("TAG", "StickyHeaders: ${person.name}")
    }
}

@Composable
fun ImageLoader(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(120.dp)
    )
}

@Composable
fun ListItem(person: Person, selectedPerson: (Person) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { selectedPerson(person) },
    ) {
        Row {
            ImageLoader(person.imageUrl)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                person.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnClickableAdvStickyDemo(
    persons: List<Person>,
    selectedPerson: (Person) -> Unit
) {
    val grouped = persons.groupBy { it.section }

    LazyColumn() {
        grouped.forEach { (section, sectionPersons) ->
            stickyHeader {
                Text(
                    text = "SECTION: $section",
                    color = Color.White,
                    modifier = Modifier
                        .background(color = Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            items(
                items = sectionPersons,
                itemContent = {
                    ListItem(person = it, selectedPerson = selectedPerson)
                }
            )
        }
    }
}

