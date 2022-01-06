package com.tsvetomir.tonchev.tweet.domain

import com.tsvetomir.tonchev.tweet.data.networking.client.ApiResponse
import com.tsvetomir.tonchev.tweet.data.networking.model.UserData
import com.tsvetomir.tonchev.tweet.data.repository.TweetsRepository
import com.tsvetomir.tonchev.tweet.ui.base.BaseUseCase
import com.tsvetomir.tonchev.tweet.ui.base.Either

object GetTweetUserUseCase : BaseUseCase<Either<String?, UserData>>() {
    const val USER_ID_PARAM = "USER_ID_PARAM"

    override suspend fun run(withData: Map<String, Any>?): Either<String?, UserData> {
        val id = withData?.get(USER_ID_PARAM) as String
        val response = TweetsRepository.getTweetUser(id)
        val responseData = response.responseData?.userData
        return if (response.responseStatus == ApiResponse.STATUS_OK && responseData != null) {
            Either.Right(responseData)
        } else {
            Either.Left(response.error)
        }
    }
}