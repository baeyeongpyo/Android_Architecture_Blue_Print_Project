package com.yeong.android_architecture_blue_print_project.util

import com.yeong.android_architecture_blue_print_project.data.Result

object ResultExt {

    fun <T : Any> Result<T>.onSuccess(bind: (T) -> Unit): Result<T> {
        if (this is Result.Success<T>) {
            bind(data)
        }
        return this
    }

    fun <T : Any> Result<T>.onFail(bind: (Int, Exception) -> Unit): Result<T> {
        if (this is Result.Fail) {
            bind(code, exception)
        }
        return this
    }

}