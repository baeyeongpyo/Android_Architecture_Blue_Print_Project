package com.yeong.android_architecture_blue_print_project.ui.support

import android.app.Application
import androidx.annotation.StringRes

class ResourceProvider(private val context: Application) {

    private fun getString(@StringRes stringId: Int) = context.getString(stringId)

}