package com.tsvetomir.tonchev.tweet.data.models.remote

import com.google.gson.annotations.SerializedName

data class TweetDataResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val tweetText: String
)