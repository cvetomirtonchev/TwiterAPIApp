package com.tsvetomir.tonchev.tweet.ui.base

sealed class Either<out L, out R> {
    class Left<out L>(val left: L) : Either<L, Nothing>()
    class Right<out R>(val right: R) : Either<Nothing, R>()

    fun <T> either(onLeft: (L) -> T, onRight: (R) -> T): T {
        return when (this) {
            is Left -> onLeft(left)
            is Right -> onRight(right)
        }
    }

    fun onLeft(block: (L) -> Unit): Either<L, R> {
        if (this is Left)
            block(left)
        return this
    }

    fun onRight(block: (R) -> Unit): Either<L, R> {
        if (this is Right)
            block(right)
        return this
    }
}