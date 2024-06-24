package com.example.paging3.data.remote.interceptors

import com.example.paging3.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader("Accept", "application/json")
        currentRequest.addHeader("X-CMC_PRO_API_KEY", BuildConfig.API_KEY)
        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}