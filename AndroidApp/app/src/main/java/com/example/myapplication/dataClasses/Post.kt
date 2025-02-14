package com.example.myapplication.dataClasses

data class Post(
    val id: Long,
    val title:String,
    val content: String,
    val user: User,
    val createdOn: String,
    val status: String
)
