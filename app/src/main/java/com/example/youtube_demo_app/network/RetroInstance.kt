package com.example.youtube_demo_app.network

import com.example.youtube_demo_app.model.UserCommentsResponse
import com.example.youtube_demo_app.model.UserDetailResponse
import com.example.youtube_demo_app.model.UserPostResponse
import com.example.youtube_demo_app.model.UserResponse
import com.example.youtube_demo_app.util.Constants.Companion.BASE_URL
import com.example.youtube_demo_app.util.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroInstance {

    // User api call
    @GET("users")
    suspend fun fetchVideos() : Response<List<UserResponse>>

    // User Detail api call
    @GET("users/{id}")
    suspend fun userDataService(@Path("id") id: Long) : Response<UserDetailResponse>

    // Post api call
    @GET("users/{id}/posts")
    suspend fun postDataService(@Path("id") id: Long) : Response<List<UserPostResponse>>

    // Comments api call
    @GET("posts/{id}/comments")
    suspend fun commentsDataService(@Path("id") id: Long) : Response<List<UserCommentsResponse>>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : RetroInstance {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetroInstance::class.java)
        }
    }

}