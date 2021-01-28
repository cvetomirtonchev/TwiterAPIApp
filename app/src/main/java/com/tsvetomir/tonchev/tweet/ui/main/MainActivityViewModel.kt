package com.tsvetomir.tonchev.tweet.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsvetomir.tonchev.tweet.data.models.local.Tweet
import com.tsvetomir.tonchev.tweet.data.models.remote.TweetDataResponse
import com.tsvetomir.tonchev.tweet.domain.GetTweetByKeywordUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val mTweetsListLiveData = MutableLiveData<List<Tweet>>()
    fun observeTweetsListLiveData() = mTweetsListLiveData as LiveData<List<Tweet>>


    fun getTweetByKeyword(keyword: String) {
        viewModelScope.launch {
            GetTweetByKeywordUseCase.executeAsync(mapOf(GetTweetByKeywordUseCase.KEYWORD_PARAM to keyword))
                .onLeft {
                    // send error
                }.onRight { responseData ->
                    mTweetsListLiveData.value = mapResponse(responseData)
                }
        }
    }

    private fun mapResponse(data: List<TweetDataResponse>): List<Tweet> {
        val tweets = mutableListOf<Tweet>()
        for (tweet in data) {
            tweets.add(Tweet(tweet.id, tweet.tweetText))
        }
        return tweets
    }
}