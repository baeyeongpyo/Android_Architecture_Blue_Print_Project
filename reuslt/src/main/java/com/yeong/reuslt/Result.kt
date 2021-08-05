package com.yeong.reuslt

import java.lang.Exception

sealed class Result<out R> {

    fun interface SuccessBind<T> {
        fun bind(data: T)
    }

    fun interface FailBind {
        fun bind(data: Exception)
    }

    fun interface LoadingBind {
        fun bind()
    }

    fun interface DoneBind {
        fun bind()
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

    @Suppress("UNCHECKED_CAST")
    fun <T> onSuccess(bind: SuccessBind<T>): Result<R> {
        if (this is Success) bind.bind(data as T)
        return this
    }

    fun onFail(bind: FailBind): Result<R> {
        if (this is Fail) bind.bind(exception)
        return this
    }

    fun onLoading(bind: LoadingBind): Result<R> {
        if (this is Loading) bind.bind()
        return this
    }

    fun onDone(bind: DoneBind): Result<R> {
        if (this is Done) bind.bind()
        return this
    }

}