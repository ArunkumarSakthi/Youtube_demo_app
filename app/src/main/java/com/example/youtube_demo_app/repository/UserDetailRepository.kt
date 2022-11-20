package com.example.youtube_demo_app.repository

import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.util.SafeApiRequest

class UserDetailRepository(private val retroInstance: RetroInstance, private val id: Long) :
    SafeApiRequest() {

    suspend fun getUserDetailData() = apiRequest { retroInstance.userDataService(id = id) }
}