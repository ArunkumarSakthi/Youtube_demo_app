package com.example.youtube_demo_app.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_demo_app.repository.CommentsRepository
import com.example.youtube_demo_app.viewmodel.CommentsViewModel

class CommentsViewModelFactory (
    private val commentsRepository: CommentsRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(commentsRepository) as T
    }
}