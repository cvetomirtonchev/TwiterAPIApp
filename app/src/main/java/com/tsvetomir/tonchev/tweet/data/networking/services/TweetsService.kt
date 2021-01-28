package com.tsvetomir.tonchev.tweet.data.networking.services

import com.tsvetomir.tonchev.tweet.data.models.remote.TweetsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TweetsService {
    companion object {
        private const val GET_RECENT_TWEETS_PATH = "2/tweets/search/recent"
        private const val KEYWORD_QUERY = "query"
    }

    @GET(GET_RECENT_TWEETS_PATH)
    fun getTweetsByKeyword(@Query(KEYWORD_QUERY) keyword: String): Call<TweetsResponse>
}