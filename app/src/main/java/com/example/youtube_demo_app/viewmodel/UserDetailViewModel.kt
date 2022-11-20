package com.example.youtube_demo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtube_demo_app.model.UserDetailResponse
import com.example.youtube_demo_app.repository.UserDetailRepository
import com.example.youtube_demo_app.util.Coroutines
import kotlinx.coroutines.Job

class UserDetailViewModel(
    private val userDetailRepository: UserDetailRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _userDetailData = MutableLiveData<UserDetailResponse>()

    fun getUserDetailApiData() {
        job = Coroutines.ioThenMain(
            { userDetailRepository.getUserDetailData() },
            { _userDetailData.value = it }
        )
    }


    fun observeMovieLiveData(): LiveData<UserDetailResponse> {
        return _userDetailData
    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}