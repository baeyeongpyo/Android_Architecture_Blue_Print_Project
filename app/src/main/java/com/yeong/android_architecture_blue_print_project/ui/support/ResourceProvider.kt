package com.yeong.android_architecture_blue_print_project.ui.support

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes stringId: Int) = context.getString(stringId)

}