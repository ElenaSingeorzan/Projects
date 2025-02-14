package com.example.myapplication.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataClasses.Comment
import com.example.myapplication.dataClasses.EmailRequest
import com.example.myapplication.dataClasses.Post
import com.example.myapplication.dataClasses.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: ApiRepository) : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())

    val posts: StateFlow<List<Post>> = _posts

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())

    val comments: StateFlow<List<Comment>> = _comments

    private val _pendingPosts = MutableStateFlow<List<Post>>(emptyList())
    val pendingPosts: StateFlow<List<Post>> = _pendingPosts.asStateFlow()

    init {
        fetchPendingPosts()
    }

    private fun fetchPendingPosts() {
        viewModelScope.launch {
            val posts = repository.getPendingPosts()
            _pendingPosts.value = posts
        }
    }
    fun fetchPostsByKeyword(keyword: String) {
        viewModelScope.launch {
            try {
                val result = repository.getPostsByKeyword(keyword)
                _posts.value = result
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching posts: ${e.message}")
            }
        }
    }

    fun fetchCommentsByKeyword(keyword: String) {
        viewModelScope.launch {
            val result = repository.getCommentsByKeyword(keyword)
            _comments.value = result
        }
    }

    fun approvePost(postId: Long) {
        try {
            viewModelScope.launch {
                repository.approvePost(postId)}
        } catch (e: Exception) {
            Log.e("Error", "Error approving post", e)
        }
        _pendingPosts.value = _pendingPosts.value.filter { it.id != postId }
        }


    fun removePost (postId: Long) {
        try {
            viewModelScope. launch {
                repository.removePost(postId)
            }
        } catch (e: Exception) {
            Log.e("Error", "Error approving post", e)
        }
        _pendingPosts.value = _pendingPosts.value.filter { it.id != postId }
    }



    private val _userEmails = MutableLiveData<List<String>>()
    val userEmails: LiveData<List<String>> get() = _userEmails

    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    // Funcție pentru a obține utilizatorii și extrage email-urile
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = withContext(Dispatchers.IO) {
                    apiService.getUsers()
                }
                // Extragem doar câmpul "email" din lista de utilizatori
                _userEmails.value = users.map { it.email }
            } catch (e: Exception) {
                _userEmails.value = emptyList()
            }
        }
    }

    fun sendEmail(emailRequest: EmailRequest) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.sendEmail(emailRequest)
                }

            } catch (e: Exception) {
                Log.e("Error","Error sending email: ",e)
            }
        }
    }

}
