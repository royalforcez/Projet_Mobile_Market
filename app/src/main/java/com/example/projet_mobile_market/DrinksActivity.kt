package com.example.projet_mobile_market

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class DrinksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinksScreen()
        }
    }
}

@Composable
fun DrinksScreen() {
    val drinks = remember { mutableStateListOf<Drink>() }

    LaunchedEffect(Unit) {
        val data = fetchDrinksData()
        drinks.addAll(data)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Header avec un dégradé
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFF5722), Color(0xFFFFC107))
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Boissons",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        // Liste des boissons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            drinks.forEach { drink ->
                DrinkCard(drink)
            }
        }
    }
}

@Composable
fun DrinkCard(drink: Drink) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(drink.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = drink.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = drink.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 2
                )
            }
        }
    }
}

suspend fun fetchDrinksData(): List<Drink> = withContext(Dispatchers.IO) {
    val url = "https://api.jsonbin.io/v3/b/676033b0acd3cb34a8ba8614"
    val response = URL(url).readText()
    val jsonObject = JSONObject(response)
    val drinksArray = jsonObject.getJSONArray("record")

    List(drinksArray.length()) { i ->
        val drinkObject = drinksArray.getJSONObject(i)
        Drink(
            name = drinkObject.getString("name"),
            description = drinkObject.getString("description"),
            imageUrl = drinkObject.getString("picture_url")
        )
    }
}

data class Drink(
    val name: String,
    val description: String,
    val imageUrl: String
)
