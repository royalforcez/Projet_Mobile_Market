package com.example.projet_mobile_market

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

@Composable
fun CategoriesScreen(
    name: String = "Rayons",
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    val context = LocalContext.current
    val categories = remember { mutableStateListOf<String>() }

    // Récupération des catégories depuis l'API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                val response = URL("https://api.jsonbin.io/v3/b/6760342bacd3cb34a8ba8657").readText()
                val jsonArray = JSONObject(response).getJSONArray("record")

                for (i in 0 until jsonArray.length()) {
                    val category = jsonArray.getJSONObject(i).getString("title")
                    categories.add(category)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Header avec un dégradé
                Column(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFFFF5722), Color(0xFFFFC107)) // Dégradé orange/jaune
                            )
                        )
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                // Boutons générés dynamiquement
                categories.forEachIndexed { index, category ->
                    CategoryButton(
                        label = category,
                        onClick = {
                            val intent = Intent(context, when (index) {
                                0 -> CanActivity::class.java
                                1 -> FrozenActivity::class.java
                                2 -> CheeseActivity::class.java
                                3 -> DrinksActivity::class.java
                                else -> SauceActivity::class.java
                            })
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun CategoryButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth(0.8f) // Bouton occupe 80% de la largeur
            .height(60.dp)
            .padding(vertical = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE))
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333) // Gris foncé pour le texte
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen()
}
