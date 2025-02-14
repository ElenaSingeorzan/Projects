package com.example.myapplication.ui.theme

import com.example.myapplication.dataClasses.Comment
import com.example.myapplication.dataClasses.EmailRequest
import com.example.myapplication.dataClasses.Post
import com.example.myapplication.dataClasses.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/posts/search")
    suspend fun getPostsContainingKeyword(@Query("keyword") keyword: String): List<Post>
    @GET("/allPosts")
    suspend fun getPosts(): List<Post>

    @GET("/pendingPosts")
    suspend fun getPendingPosts():List<Post>

    @GET("/users")
    suspend fun getUsers():List<User>

    @GET("comments/search")
    suspend fun getCommentsContainingKeyword(@Query("keyword") keyword: String): List<Comment>

    @PUT("/{postId}/approve")
    suspend fun approvePost(@Path("postId") postId: Long): Response<Void>

    @PUT("/{postId}/remove")
    suspend fun removePost(@Path("postId") postId: Long): Response<Void>

    @POST("/sendEmail")
    suspend fun sendEmail(@Body emailRequest: EmailRequest): Response<String>
}