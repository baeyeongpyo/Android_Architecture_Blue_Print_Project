package com.yeong.android_architecture_blue_print_project.util

import com.yeong.android_architecture_blue_print_project.data.Result
import java.lang.Exception

object ResultExt {

    fun <T : Any> Result<T>.onSuccess(bind: (T) -> Unit): Result<T> {
        val result = this as Result.Success<T>
        bind(result.data)
        return this
    }

    fun <T : Any> Result<T>.onFail(bind: (Int, Exception) -> Unit): Result<T> {
        val result = this as Result.Fail
        bind(result.code, result.exception)
        return this
    }

}