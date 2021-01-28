package com.tsvetomir.tonchev.tweet.ui.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<T> {

    open suspend fun execute(withData: Map<String, Any>? = null): T {
        return withContext(Dispatchers.Main) {
            run(withData)
        }
    }

    open suspend fun executeAsync(withData: Map<String, Any>? = null): T {
        return withContext(Dispatchers.IO) {
            run(withData)
        }
    }

    protected abstract suspend fun run(withData: Map<String, Any>? = null): T
}