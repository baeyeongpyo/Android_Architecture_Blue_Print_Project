package com.yeong.reuslt

import java.lang.Exception

sealed class Result<out R> {

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

}