package com.tsvetomir.tonchev.tweet.data.networking.model

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("newest_id")
    val newestId: String,
    @SerializedName("oldest_id")
    val oldestId: String,
    @SerializedName("result_count")
    val resultCount: String,
    @SerializedName("next_token")
    val nextToken: String
)