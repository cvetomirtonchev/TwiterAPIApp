package com.tsvetomir.tonchev.tweet.data.networking.datasource

import com.tsvetomir.tonchev.tweet.BuildConfig
import com.tsvetomir.tonchev.tweet.data.networking.client.ApiResponse
import com.tsvetomir.tonchev.tweet.data.networking.client.HttpClient
import com.tsvetomir.tonchev.tweet.data.networking.client.RetrofitClient
import retrofit2.Call

open class RemoteDataSource {

    protected val baseRetrofitClient: RetrofitClient by lazy {
        RetrofitClient(BuildConfig.BASE_URL, HttpClient())
    }

    fun <T> getResponseBody(call: Call<T>): ApiResponse<T> {
        val baseApiResponse = ApiResponse<T>()
        val response = call.execute()
        baseApiResponse.httpCode = response.code()

        when (ApiResponse.checkStatus(response.code())) {
            ApiResponse.STATUS_OK -> {
                baseApiResponse.responseStatus = ApiResponse.STATUS_OK
                baseApiResponse.responseData = response.body()
            }
            ApiResponse.STATUS_ERROR -> {
                baseApiResponse.responseStatus = ApiResponse.STATUS_ERROR
                val errorBody = response.errorBody()?.string()
                if (errorBody != null) {
                    baseApiResponse.error = errorBody
                }
            }
            ApiResponse.STATUS_FAILED -> {
                baseApiResponse.responseStatus = ApiResponse.STATUS_FAILED
            }
        }

        return baseApiResponse
    }
}