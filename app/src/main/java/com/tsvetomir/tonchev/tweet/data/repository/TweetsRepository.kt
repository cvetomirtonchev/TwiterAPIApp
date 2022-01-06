package com.tsvetomir.tonchev.tweet.data.repository

import com.tsvetomir.tonchev.tweet.data.networking.client.ApiResponse
import com.tsvetomir.tonchev.tweet.data.networking.datasource.remote.TweetsDataSource
import com.tsvetomir.tonchev.tweet.data.networking.model.TweetsResponse
import com.tsvetomir.tonchev.tweet.data.networking.model.UserResponse

object TweetsRepository {
    private val dataSource: TweetsDataSource by lazy {
        TweetsDataSource()
    }

    fun getTweetsByKeyword(keyword: String): ApiResponse<TweetsResponse> {
        val expansions = "author_id"
        return dataSource.getTweetsByKeyword(keyword, expansions)
    }

    fun getTweetUser(id: String): ApiResponse<UserResponse> {
        val userFields = "profile_image_url,username"
        return dataSource.getUsers(id, userFields)
    }
}