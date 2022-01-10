package com.tsvetomir.tonchev.tweet.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsvetomir.tonchev.tweet.data.networking.model.TweetDataResponse
import com.tsvetomir.tonchev.tweet.domain.GetTweetByKeywordUseCase
import com.tsvetomir.tonchev.tweet.ui.main.model.Tweet
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _tweetsListLiveData = MutableLiveData<List<Tweet>>()
    fun observeTweetsListLiveData() = _tweetsListLiveData as LiveData<List<Tweet>>


    fun getTweetByKeyword(keyword: String) {
        viewModelScope.launch {
            GetTweetByKeywordUseCase.executeAsync(mapOf(GetTweetByKeywordUseCase.KEYWORD_PARAM to keyword))
                .onLeft {
                    // send error
                }.onRight { responseData ->
                    _tweetsListLiveData.value = mapResponse(responseData)
                }
        }
    }

    private fun mapResponse(data: List<TweetDataResponse>): List<Tweet> {
        val tweets = mutableListOf<Tweet>()
        for (tweet in data) {
            tweets.add(Tweet(tweet.id, tweet.tweetText, tweet.authorId))
        }
        return tweets
    }
}