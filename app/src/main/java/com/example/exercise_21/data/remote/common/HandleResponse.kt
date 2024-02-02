package com.example.exercise_21.data.remote.common

import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HandleResponse {
    fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            val response  = call()
            val body = response.body()
            if(response.isSuccessful && body != null){
                emit(Resource.Success(result = body))
            } else {
                emit(Resource.Failure(errorMessage = response.errorBody()?.string() ?: ""))
            }
        } catch (e: Throwable){
            emit(Resource.Failure(errorMessage = e.message ?: ""))
        }
        emit(Resource.Loading(isLoading = false))
    }
}