package com.example.myapplication.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.dataClasses.Post
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.sp


@Composable
fun PendingPostItem(post: Post, onApprove: () -> Unit, onRemove: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Title: ${post.title}", style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp))
        Text(text = "Content: ${post.content}")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onApprove, modifier = Modifier.weight(1f)) {
                Text("Approve")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onRemove, modifier = Modifier.weight(1f)) {
                Text("Remove")
            }
        }
    }
}
@Composable
fun PendingPostsScreen(navController: NavController, viewModel: MainViewModel) {
    val pendingPosts by viewModel.pendingPosts.collectAsState(initial = emptyList()) // Observă lista de postări pending
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pending Posts", style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp))

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pendingPosts) { post ->
                PendingPostItem(post = post, onApprove = {
                    coroutineScope.launch { viewModel.approvePost(post.id) }
                }, onRemove = {
                    coroutineScope.launch { viewModel.removePost(post.id) }
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Home")
        }
    }
}
