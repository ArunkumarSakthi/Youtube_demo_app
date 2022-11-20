package com.example.youtube_demo_app.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_demo_app.repository.UserDetailRepository
import com.example.youtube_demo_app.viewmodel.UserDetailViewModel

class UserDetailViewModelFactory (
    private val userDetailRepository: UserDetailRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailViewModel(userDetailRepository) as T
    }

}