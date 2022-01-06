package com.tsvetomir.tonchev.tweet.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tweet(
    val id: String,
    val info: String,
    val userId: String
) : Parcelable