package com.yeong.reuslt

import java.lang.Exception

sealed class Result<out R> {

    fun interface SuccessBind<T> {
        fun bind(data: T)
    }

    data class Success<T>(val data: T) : Result<T>()
    data class Fail(val exception: Exception) : Result<Exception>()
    object Loading : Result<Nothing>()
    object Done : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[$data]"
            is Fail -> "Fail[${exception.message}]"
            is Loading -> "Loading"
            is Done -> "Done"
        }
    }

    inline fun <reified T> onSuccess(bind: SuccessBind<T>): Result<R> {
        if (this is Success && data is T) bind.bind(data)
        return this
    }

}