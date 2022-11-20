package com.example.youtube_demo_app.util

import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    // this class is to handle the api exception
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            //@todo handle api exception
            throw ApiException(response.code().toString())
        }
    }

}

class ApiException(message: String): IOException(message)
class NoInternetException(message: String): IOException(message)