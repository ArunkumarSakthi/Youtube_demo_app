package com.example.youtube_demo_app.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_demo_app.repository.HomeScreenRepository
import com.example.youtube_demo_app.viewmodel.HomeScreenViewModel

class UserViewModelFactory(
    private val repository: HomeScreenRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(repository) as T
    }

}