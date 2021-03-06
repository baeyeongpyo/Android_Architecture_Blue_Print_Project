package com.yeong.android_architecture_blue_print_project.di

import android.content.Context
import com.yeong.android_architecture_blue_print_project.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MainModule::class,
        EditWorkModule::class,
        WorksModule::class,
        DetailWorkModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(context: Context): Builder
        fun build(): ApplicationComponent
    }

}
