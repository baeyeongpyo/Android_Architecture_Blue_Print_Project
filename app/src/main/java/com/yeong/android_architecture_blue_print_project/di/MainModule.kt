package com.yeong.android_architecture_blue_print_project.di

import com.yeong.android_architecture_blue_print_project.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

}