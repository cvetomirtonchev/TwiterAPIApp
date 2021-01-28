package com.tsvetomir.tonchev.tweet.data.models.remote

import com.google.gson.annotations.SerializedName

data class TweetsResponse(
    @SerializedName("data")
    val tweetDataList: List<TweetDataResponse>,
    @SerializedName("meta")
    val metaData: MetaResponse
)
