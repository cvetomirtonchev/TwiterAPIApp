package com.tsvetomir.tonchev.tweet.ui.tweet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsvetomir.tonchev.tweet.data.networking.model.UserData
import com.tsvetomir.tonchev.tweet.domain.GetTweetUserUseCase
import com.tsvetomir.tonchev.tweet.ui.main.model.Tweet
import com.tsvetomir.tonchev.tweet.ui.tweet.model.UserUiModel
import kotlinx.coroutines.launch

class TweetActivityViewModel : ViewModel() {

    private val _userLiveData = MutableLiveData<UserUiModel>()
    fun observeUserLiveData() = _userLiveData as LiveData<UserUiModel>


    fun getTweetUser(tweet: Tweet) {
        viewModelScope.launch {
            GetTweetUserUseCase.executeAsync(mapOf(GetTweetUserUseCase.USER_ID_PARAM to tweet.userId))
                .onLeft {
                    // send error
                }.onRight { responseData ->
                    _userLiveData.value = mapResponse(responseData, tweet)
                }
        }
    }

    private fun mapResponse(data: UserData, tweet: Tweet): UserUiModel =
        UserUiModel(
            data.username,
            data.profileImageUrl,
            tweet
        )
}