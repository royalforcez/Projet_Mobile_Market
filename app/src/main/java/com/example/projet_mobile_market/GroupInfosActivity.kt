package com.example.projet_mobile_market

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

@Composable
fun GroupInfosScreen() {
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
                Text(
                    text = "Epsi",
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Buttons for Students
                val context = LocalContext.current

                Button(
                    onClick = {
                        val intent = Intent(context, Student1Activity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(0.8f).height(60.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Student 1")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, Student2Activity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(0.8f).height(60.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Student 2")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, Student3Activity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(0.8f).height(60.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Student 3")
                }
            }
        }
    )
}