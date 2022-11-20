package com.example.youtube_demo_app.repository

import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.util.SafeApiRequest

class CommentsRepository(private val retroInstance: RetroInstance, private val id: Long) :
    SafeApiRequest() {

    suspend fun getCommentsScreenData() = apiRequest { retroInstance.commentsDataService(id = id) }

}