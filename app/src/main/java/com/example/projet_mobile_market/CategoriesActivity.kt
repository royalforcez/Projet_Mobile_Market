package com.example.projet_mobile_market

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.reflect.Modifier
import java.net.URL

@Composable
fun CategoriesScreen(name: String = "Categories", modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier) {
    val categories = remember { mutableStateListOf<String>() }
    val scope = rememberCoroutineScope()

    // Récupération des données depuis l'API
    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = URL("https://api.jsonbin.io/v3/b/6760342bacd3cb34a8ba8657").readText()
                val jsonArray = JSONObject(response).getJSONArray("record") // Récupère le tableau 'record'
                val categoryList = mutableListOf<String>()

                // Parcours du tableau pour récupérer les titres des catégories
                for (i in 0 until jsonArray.length()) {
                    val category = jsonArray.getJSONObject(i) // Récupère chaque objet dans le tableau
                    categoryList.add(category.getString("title")) // Ajoute le titre à la liste
                }

                // Met à jour les catégories avec addAll
                categories.clear()
                categories.addAll(categoryList)
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
                    .padding(innerPadding)

            ) {
                // Header personnalisé
                Box(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFFFF5722), Color(0xFFFFC107))
                            )
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Rayons",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                // Liste des catégories
                LazyColumn(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(categories) { category ->
                        CategoryItem(category)
                    }
                }
            }
        }
    )
}

@Composable
fun CategoryItem(category: String) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = Color(0xFFEEEEEE),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { /* Action lors du clic */ }
            .padding(16.dp)
    ) {
        Text(
            text = category,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen()
}




