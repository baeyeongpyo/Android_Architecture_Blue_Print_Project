package com.yeong.android_architecture_blue_print_project.di

import androidx.lifecycle.ViewModel
import com.yeong.android_architecture_blue_print_project.ui.edit.EditWorkFragment
import com.yeong.android_architecture_blue_print_project.ui.edit.EditWorkViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class EditWorkModule {

    @ContributesAndroidInjector
    abstract fun bindEditWorkFragment(): EditWorkFragment

    @Binds
    @IntoMap
    @ViewModelKey(EditWorkViewModel::class)
    abstract fun bindEditWorkViewModel(factory : EditWorkViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

}