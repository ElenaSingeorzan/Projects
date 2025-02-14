package com.example.myapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("comments") {
            CommentScreen(navController, viewModel)
        }
        composable("posts") {
            PostScreen(navController, viewModel)
        }
        composable("pending_posts") {
            PendingPostsScreen(navController, viewModel)
        }
        composable("sendEmail") {
            SendEmailScreen(navController,viewModel)
        }
    }
}
