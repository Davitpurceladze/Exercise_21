package com.example.exercise_21.data.remote.common

sealed class Resource<out R> {
    data class Success<out R : Any>(val result: R) : Resource<R>()
    data class Failure<out R : Any>(val errorMessage: String) : Resource<R>()
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
}
