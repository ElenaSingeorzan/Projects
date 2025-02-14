package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.myapplication.ui.theme.ApiClient
import com.example.myapplication.ui.theme.ApiRepository
import com.example.myapplication.ui.theme.ApiService
import com.example.myapplication.ui.theme.AppNavigation
import com.example.myapplication.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val repository = ApiRepository(apiService)
        val viewModel = MainViewModel(repository)

        setContent {
            MaterialTheme {
                AppNavigation(viewModel)
               }
            }
        }
    }
        