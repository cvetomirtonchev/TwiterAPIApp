package com.tsvetomir.tonchev.tweet.data.networking.services

import com.tsvetomir.tonchev.tweet.data.networking.model.TweetsResponse
import com.tsvetomir.tonchev.tweet.data.networking.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TweetsService {
    companion object {
        private const val GET_RECENT_TWEETS_PATH = "2/tweets/search/recent"
        private const val GET_USERS = "2/users/{id}"
        private const val KEYWORD_QUERY = "query"
    }

    @GET(GET_RECENT_TWEETS_PATH)
    fun getTweetsByKeyword(
        @Query(KEYWORD_QUERY) keyword: String,
        @Query("expansions") expansions: String
    ): Call<TweetsResponse>

    @GET(GET_USERS)
    fun getUsers(
        @Path("id") id: String,
        @Query("user.fields") userFields: String
    ): Call<UserResponse>
}