package com.example.youtube_demo_app.model

data class UserCommentsResponse(
    val postId: Int?,
    val id: Int?,
    val name: String?,
    val email: String?,
    val body: String?
)