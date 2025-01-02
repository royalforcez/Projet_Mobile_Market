package com.example.projet_mobile_market

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GroupInfosActivity() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "group_infos") {
        composable("group_infos") { GroupInfosScreen(navController) }
        composable("hello_world") { HelloWorldScreen() }
    }
}

@Composable
fun GroupInfosScreen(navController: NavHostController) {
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
                verticalArrangement = Arrangement.Center // Aligne le bouton au centre
            ) {
                // Bouton unique qui navigue vers "Hello World"
                StudentButton(name = "Student") {
                    navController.navigate("hello_world")
                }
            }
        }
    )
}

@Composable
fun StudentButton(name: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(
            text = name,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun HelloWorldScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hello World",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    )
}
