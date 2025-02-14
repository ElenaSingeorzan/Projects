package com.example.myapplication.dataClasses

data class EmailRequest(val recipients: List<String>,
                        val subject: String,
                        val body: String)
