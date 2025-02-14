package com.example.myapplication.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.dataClasses.Comment
import com.example.myapplication.dataClasses.Post


@Composable
fun PostItem(post: Post) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "${post.user.name} : ${post.title}", style = MaterialTheme.typography.bodyLarge)}
        Text(text = post.content, style = MaterialTheme.typography.bodyMedium)
        Text(text = post.status, style = MaterialTheme.typography.bodyMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun PostScreen(navController: NavController, viewModel: MainViewModel) {
    var keyword by remember { mutableStateOf("") }
    val posts by viewModel.posts.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = keyword,
            onValueChange = { keyword = it },
            label = { Text("Search posts") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.fetchPostsByKeyword(keyword) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(posts) { post ->
                PostItem(post)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Home")
        }
    }

}
