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
        private const val EXPANSIONS_QUERY = "expansions"
        private const val PATH_ID = "id"
        private const val USER_FIELD_QUERY = "user.fields"
    }

    @GET(GET_RECENT_TWEETS_PATH)
    fun getTweetsByKeyword(
        @Query(KEYWORD_QUERY) keyword: String,
        @Query(EXPANSIONS_QUERY) expansions: String
    ): Call<TweetsResponse>

    @GET(GET_USERS)
    fun getUsers(
        @Path(PATH_ID) id: String,
        @Query(USER_FIELD_QUERY) userFields: String
    ): Call<UserResponse>
}