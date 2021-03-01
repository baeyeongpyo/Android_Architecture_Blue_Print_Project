package com.yeong.android_architecture_blue_print_project.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentExt {

    fun FragmentManager.replace(
        @IdRes containerId: Int,
        fragmentClass: Class<out Fragment>,
        args: Bundle? = null
    ) {
        beginTransaction()
            .replace(containerId, fragmentClass, args)
            .commit()
    }

    fun FragmentManager.replaceBackStack(
        @IdRes containerId: Int,
        backStackTagName: String,
        fragmentClass: Class<out Fragment>,
        args: Bundle? = null
    ) {
        beginTransaction()
            .addToBackStack(backStackTagName)
            .replace(containerId, fragmentClass, args)
            .commit()
    }

}