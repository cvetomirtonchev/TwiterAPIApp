package com.tsvetomir.tonchev.tweet.data.networking.model

import com.google.gson.annotations.SerializedName

data class TweetsResponse(
    @SerializedName("data")
    val tweetDataList: List<TweetDataResponse>,
    @SerializedName("meta")
    val metaData: MetaResponse
)
