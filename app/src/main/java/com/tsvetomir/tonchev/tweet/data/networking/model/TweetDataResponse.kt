package com.tsvetomir.tonchev.tweet.data.networking.model

import com.google.gson.annotations.SerializedName

data class TweetDataResponse(
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val tweetText: String
)