package com.tsvetomir.tonchev.tweet.domain

import com.tsvetomir.tonchev.tweet.data.models.remote.TweetDataResponse
import com.tsvetomir.tonchev.tweet.data.networking.client.ApiResponse
import com.tsvetomir.tonchev.tweet.data.repository.TweetsRepositoryImpl
import com.tsvetomir.tonchev.tweet.ui.base.BaseUseCase
import com.tsvetomir.tonchev.tweet.ui.base.Either

object GetTweetByKeywordUseCase : BaseUseCase<Either<String?, List<TweetDataResponse>>>() {
    const val KEYWORD_PARAM = "keyword-param"

    override suspend fun run(withData: Map<String, Any>?): Either<String?, List<TweetDataResponse>> {
        val category = withData?.get(KEYWORD_PARAM) as String
        val response = TweetsRepositoryImpl.getTweetsByKeyword(category)
        val responseData = response.responseData
        return if (response.responseStatus == ApiResponse.STATUS_OK && responseData != null) {
            Either.Right(responseData.tweetDataList)
        } else {
            Either.Left(response.error)
        }
    }
}