package com.tsvetomir.tonchev.tweet.data.networking.datasource.remote

import com.tsvetomir.tonchev.tweet.data.networking.datasource.RemoteDataSource
import com.tsvetomir.tonchev.tweet.data.networking.services.TweetsService

class TweetsDataSource : RemoteDataSource() {

    private val service: TweetsService by lazy {
        baseRetrofitClient.createService(TweetsService::class.java)
    }

    fun getTweetsByKeyword(keyword: String) = getResponseBody(service.getTweetsByKeyword(keyword))
}