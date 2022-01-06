package com.tsvetomir.tonchev.tweet.data.networking.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val userData: UserData
)

data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_image_url")
    val profileImageUrl: String,
    @SerializedName("username")
    val username: String
)