package com.example.youtube_demo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtube_demo_app.model.UserPostResponse
import com.example.youtube_demo_app.repository.PostRepository
import com.example.youtube_demo_app.util.Coroutines
import kotlinx.coroutines.Job

class PostViewModel(
    private val postRepository: PostRepository
): ViewModel() {

    private lateinit var job: Job

    private val _postData = MutableLiveData<List<UserPostResponse>>()
    val userPostData : LiveData<List<UserPostResponse>>
    get() = _postData

    fun getPostApiData(){
        job = Coroutines.ioThenMain(
            {postRepository.getPostScreenData()},
            {_postData.value = it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}