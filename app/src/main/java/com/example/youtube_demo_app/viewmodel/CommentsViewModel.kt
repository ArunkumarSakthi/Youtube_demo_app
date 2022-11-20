package com.example.youtube_demo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtube_demo_app.model.UserCommentsResponse
import com.example.youtube_demo_app.repository.CommentsRepository
import com.example.youtube_demo_app.util.Coroutines
import kotlinx.coroutines.Job

class CommentsViewModel(
    private val commentsRepository: CommentsRepository
) :ViewModel(){

    private lateinit var job: Job

    private val _commentsData = MutableLiveData<List<UserCommentsResponse>>()
    val userCommentsData : LiveData<List<UserCommentsResponse>>
    get() = _commentsData


    fun getCommentsApiData(){
        job = Coroutines.ioThenMain(
            {commentsRepository.getCommentsScreenData()},
            {_commentsData.value = it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}