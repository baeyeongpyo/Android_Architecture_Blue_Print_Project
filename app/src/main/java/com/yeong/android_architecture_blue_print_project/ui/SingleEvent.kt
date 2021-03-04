package com.yeong.android_architecture_blue_print_project.ui

open class SingleEvent<out T>(private val contentData: T) {

    var hasBeenHandled = false
        private set

    fun getHandlingData() = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        contentData
    }

    fun peekContentData() = contentData

}