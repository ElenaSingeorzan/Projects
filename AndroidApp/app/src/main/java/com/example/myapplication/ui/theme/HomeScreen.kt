package com.example.myapplication.ui.theme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Home Screen", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("comments") }) {
                Text(" Comment Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("posts") }) {
                Text(" Post Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("pending_posts") }) {
                Text("View Pending Posts")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("sendEmail") }) {
                Text(" Send Email ")
            }


        }
    }
}

