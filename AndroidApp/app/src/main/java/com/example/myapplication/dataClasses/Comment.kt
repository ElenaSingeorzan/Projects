package com.example.myapplication.dataClasses

data class Comment(
    val id: Long,
    val content: String,
    val createdOn: String,
    val user: User,
    val post: Post
)
