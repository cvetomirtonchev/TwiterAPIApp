package com.tsvetomir.tonchev.tweet.data.repository

import com.tsvetomir.tonchev.tweet.data.models.remote.TweetsResponse
import com.tsvetomir.tonchev.tweet.data.networking.client.ApiResponse
import com.tsvetomir.tonchev.tweet.data.networking.datasource.remote.TweetsDataSource

object TweetsRepositoryImpl : TweetsRepository {
    private val dataSource: TweetsDataSource by lazy {
        TweetsDataSource()
    }

    override suspend fun getTweetsByKeyword(keyword: String): ApiResponse<TweetsResponse> {
        return dataSource.getTweetsByKeyword(keyword)
    }
}