package com.example.projet_mobile_market

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        composable(
            "student_info/{name}/{email}",
            arguments = listOf(
                androidx.navigation.navArgument("name") { type = androidx.navigation.NavType.StringType },
                androidx.navigation.navArgument("email") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
            val email = backStackEntry.arguments?.getString("email") ?: "Unknown"
            StudentInfoScreen(name = name, email = email)
        }
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
                verticalArrangement = Arrangement.Top
            ) {
                // Title "Infos"
                Text(
                    text = "Infos",
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
                StudentButton(navController, "Student 1", "student1@example.com")
                Spacer(modifier = Modifier.height(16.dp))
                StudentButton(navController, "Student 2", "student2@example.com")
                Spacer(modifier = Modifier.height(16.dp))
                StudentButton(navController, "Student 3", "student3@example.com")
            }
        }
    )
}

@Composable
fun StudentButton(navController: NavHostController, name: String, email: String) {
    Button(
        onClick = { navController.navigate("student_info/$name/$email") },
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
fun StudentInfoScreen(name: String, email: String) {
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
                verticalArrangement = Arrangement.Center
            ) {
                // Title with the student's name
                Text(
                    text = name,
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Email
                Text(
                    text = email,
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Placeholder for additional details
                Text(
                    text = "Additional details about $name",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    )
}
