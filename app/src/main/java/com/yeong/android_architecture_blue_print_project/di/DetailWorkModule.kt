package com.yeong.android_architecture_blue_print_project.di

import androidx.lifecycle.ViewModel
import com.yeong.android_architecture_blue_print_project.ui.detail.DetailWorkFragment
import com.yeong.android_architecture_blue_print_project.ui.detail.DetailWorkViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailWorkModule {

    @ContributesAndroidInjector
    abstract fun detailWorkFragment(): DetailWorkFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailWorkViewModel::class)
    abstract fun bindDetailViewModel(factory: DetailWorkViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

}