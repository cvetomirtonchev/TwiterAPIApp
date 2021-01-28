package com.tsvetomir.tonchev.tweet.data.networking.client

import com.tsvetomir.tonchev.tweet.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class HttpClient {

    private val okHttpBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
    }

    init {
        setDebugLevel()
    }

    fun get(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        okHttpBuilder
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(BuildConfig.CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthInterceptor())

        return okHttpBuilder.build()
    }

    private fun setDebugLevel() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addNetworkInterceptor(logging)
    }

    private class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                //TODO this token is like this only for example need to be refactored
                .addHeader(
                    "Authorization",
                    "Bearer AAAAAAAAAAAAAAAAAAAAANXMMAEAAAAAQPqsmjvrO3BZMZVb04hr%2FsAaw%2Fg%3DSD9B8OKtjJunksQM440BynvVZENhGShi1ICsBtncr1XlaQeSJT"
                )
                .build()

            return chain.proceed(request)
        }
    }
}