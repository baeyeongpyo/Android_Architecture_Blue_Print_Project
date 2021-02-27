package com.yeong.android_architecture_blue_print_project.data

import java.lang.Exception

sealed class Result<out R : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Fail(val code: Int, val exception: Exception) : Result<Nothing>(){
        constructor(exception: Exception) : this(0, exception)
    }

}