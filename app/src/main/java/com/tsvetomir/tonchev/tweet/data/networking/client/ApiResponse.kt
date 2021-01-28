package com.tsvetomir.tonchev.tweet.data.networking.client

import androidx.annotation.IntDef

class ApiResponse<T> {

    var responseData: T? = null
    var error: String? = null
    var httpCode: Int = 0

    @ResponseStatus
    @get:ResponseStatus
    var responseStatus: Int = 0

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(STATUS_OK, STATUS_ERROR, STATUS_FAILED)
    annotation class ResponseStatus
    companion object {

        const val STATUS_OK = 200
        const val STATUS_ERROR = 400
        const val STATUS_FAILED = 500
        fun checkStatus(code: Int): Int {
            return when (code) {
                in 200..299 -> {
                    STATUS_OK
                }
                in 400..499 -> {
                    STATUS_ERROR
                }
                in 500..599 -> {
                    STATUS_FAILED
                }
                else -> {
                    STATUS_FAILED
                }
            }
        }
    }
}