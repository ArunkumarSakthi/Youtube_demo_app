package com.example.youtube_demo_app.repository

import com.example.youtube_demo_app.network.RetroInstance
import com.example.youtube_demo_app.util.SafeApiRequest

class HomeScreenRepository(private val retroInstance: RetroInstance) : SafeApiRequest() {

    suspend fun getHomeScreenData() = apiRequest { retroInstance.fetchVideos() }
}