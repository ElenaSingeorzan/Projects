package com.example.myapplication.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.dataClasses.Comment


@Composable
fun CommentItem(comment: Comment) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = comment.user.name, style = MaterialTheme.typography.bodyLarge)
        Text(text = comment.content, style = MaterialTheme.typography.bodyMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}
@Composable
fun CommentScreen(navController: NavController, viewModel: MainViewModel) {
    var keyword by remember { mutableStateOf("") }
    val comments by viewModel.comments.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = keyword,
            onValueChange = { keyword = it },
            label = { Text("Search comments") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.fetchCommentsByKeyword(keyword) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Home")
        }
    }
}