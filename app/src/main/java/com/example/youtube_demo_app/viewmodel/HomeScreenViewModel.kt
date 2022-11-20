package com.example.youtube_demo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtube_demo_app.model.UserResponse
import com.example.youtube_demo_app.repository.HomeScreenRepository
import com.example.youtube_demo_app.util.Coroutines
import kotlinx.coroutines.Job

class HomeScreenViewModel (
    private val repository: HomeScreenRepository
) : ViewModel() {

    private lateinit var job: Job


    private val _userData=  MutableLiveData<List<UserResponse>>()
    val homescreenData: LiveData<List<UserResponse>>
        get() = _userData


    fun getApiData() {
        job = Coroutines.ioThenMain(
            { repository.getHomeScreenData() },
            { _userData.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }



}