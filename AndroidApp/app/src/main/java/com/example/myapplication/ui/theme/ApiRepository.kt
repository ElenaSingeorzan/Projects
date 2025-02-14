package com.example.myapplication.ui.theme

import com.example.myapplication.dataClasses.Comment
import com.example.myapplication.dataClasses.EmailRequest
import com.example.myapplication.dataClasses.Post
import retrofit2.Response

class ApiRepository(private val apiService: ApiService) {
    suspend fun getPostsByKeyword(keyword: String): List<Post> = apiService.getPostsContainingKeyword(keyword)

    suspend fun getCommentsByKeyword(keyword: String): List<Comment> = apiService.getCommentsContainingKeyword(keyword)

    suspend fun approvePost (postId: Long): Response<Void> = apiService.approvePost(postId)

    suspend fun removePost(postId: Long): Response<Void> = apiService.removePost (postId)


    suspend fun getPendingPosts(): List<Post> { return apiService.getPendingPosts() }

    suspend fun sendEmail(emailRequest: EmailRequest) = apiService.sendEmail(emailRequest)

}