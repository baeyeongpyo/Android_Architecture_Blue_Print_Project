package com.yeong.android_architecture_blue_print_project

import android.content.Context
import com.yeong.android_architecture_blue_print_project.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .setContext(this)
            .build()
    }

    companion object {
        lateinit var application: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}