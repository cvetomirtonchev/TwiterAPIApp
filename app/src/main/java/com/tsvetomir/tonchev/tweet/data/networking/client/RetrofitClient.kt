package com.tsvetomir.tonchev.tweet.data.networking.client

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    private val baseUrl: String,
    private val client: HttpClient
) {

    private lateinit var mRetrofit: Retrofit

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
    }

    init {
        setBaseURL()
        setConverterFactory()
        buildRetrofitClient()
    }

    private fun setBaseURL() {
        retrofitBuilder.baseUrl(baseUrl)
    }

    private fun setConverterFactory() {
        val gson = GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
        retrofitBuilder.baseUrl(baseUrl)
        retrofitBuilder.addConverterFactory(gson)
    }

    private fun buildRetrofitClient() {
        mRetrofit = retrofitBuilder
            .client(client.get())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}