package com.tsvetomir.tonchev.tweet.data.repository

import com.tsvetomir.tonchev.tweet.data.models.remote.TweetsResponse
import com.tsvetomir.tonchev.tweet.data.networking.client.ApiResponse

interface TweetsRepository {

    suspend fun getTweetsByKeyword(keyword: String): ApiResponse<TweetsResponse>
}