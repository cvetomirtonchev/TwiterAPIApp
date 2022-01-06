package com.tsvetomir.tonchev.tweet.ui.tweet.model

import com.tsvetomir.tonchev.tweet.ui.main.model.Tweet

data class UserUiModel(
    val username: String,
    val profileImageUrl: String,
    val tweet: Tweet
)