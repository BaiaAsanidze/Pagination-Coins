package com.example.paging3.domain.util

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()

    class Error<T>(
        val message: String? = null,
        val data: T? = null,
    ) : Resource<T>()

    object Loading : Resource<Nothing>()

    object Idle : Resource<Nothing>()

}