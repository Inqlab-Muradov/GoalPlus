package com.example.goalplus.data.api

import com.example.goalplus.common.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder().addHeader("X-Auth-Token", API_KEY).build()
        return chain.proceed(newRequest)
    }
}