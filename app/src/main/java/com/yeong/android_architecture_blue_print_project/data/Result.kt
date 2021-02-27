package com.yeong.android_architecture_blue_print_project.data

import java.lang.Exception

sealed class Result<T : Any> {
    data class Success<T : Any>(val data: T) : Result<T>()
    data class Fail(val code: Int, val exception: Exception) : Result<Nothing>()
}