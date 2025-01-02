package com.example.projet_mobile_market

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet_mobile_market.ui.theme.Projet_Mobile_MarketTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class CategoriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projet_Mobile_MarketTheme {
                CategoriesScreen()
            }
        }
    }
}

@Composable
fun CategoriesScreen(modifier: Modifier = Modifier) {
    var categories by remember { mutableStateOf(listOf<String>()) }
    val scope = rememberCoroutineScope()

    // Récupération des données depuis l'API
    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = URL("https://api.jsonbin.io/v3/b/6760342bacd3cb34a8ba8657").readText()
                val json = JSONObject(response)
                val records = json.getJSONObject("record")
                val categoryList = records.keys().asSequence().toList()
                categories = categoryList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Header personnalisé
                Box(
                    modifier = Modifier
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
                    modifier = Modifier
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
        modifier = Modifier
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
    Projet_Mobile_MarketTheme {
        CategoriesScreen()
    }
}
