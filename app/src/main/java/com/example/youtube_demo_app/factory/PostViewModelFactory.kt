package com.example.youtube_demo_app.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_demo_app.repository.PostRepository
import com.example.youtube_demo_app.viewmodel.PostViewModel

class PostViewModelFactory(
    private val postRepository: PostRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postRepository) as T
    }
}