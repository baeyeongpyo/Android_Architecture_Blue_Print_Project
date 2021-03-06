package com.yeong.android_architecture_blue_print_project.di

import androidx.lifecycle.ViewModel
import com.yeong.android_architecture_blue_print_project.ui.works.WorksFragment
import com.yeong.android_architecture_blue_print_project.ui.works.WorksViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class WorksModule {

    @ContributesAndroidInjector
    abstract fun bindWorksFragment(): WorksFragment

    @Binds
    @IntoMap
    @ViewModelKey(WorksViewModel::class)
    abstract fun bindWorksViewModel(vm : WorksViewModel): ViewModel

}