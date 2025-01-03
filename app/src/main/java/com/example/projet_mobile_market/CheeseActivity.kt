package com.example.projet_mobile_market



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.text.ClickableText
import android.widget.Toast


class CheeseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheeseScreen()
        }
    }
}

@Composable
fun CheeseScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Title
                Text(
                    text = "Donald Duck",
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Image
                Image(
                    painter = painterResource(id = R.drawable.donald),
                    contentDescription = "Donald Duck",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email
                Text(
                    text = "Donald-Duck@epsi.com",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center
                )

                // Clickable link
                ClickableLinkSauce(url = "http://www.epsi.fr")
            }
        }
    )
}

@Composable
fun ClickableLinkCheese(url: String) {
    val context = LocalContext.current

    ClickableText(
        text = AnnotatedString(url),
        onClick = {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Unable to open link", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier.padding(8.dp),
        style = androidx.compose.ui.text.TextStyle(color = Color.Blue, fontSize = 16.sp)
    )
}


