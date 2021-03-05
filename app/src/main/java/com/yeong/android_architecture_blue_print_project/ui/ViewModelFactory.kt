package com.yeong.android_architecture_blue_print_project.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.yeong.android_architecture_blue_print_project.App.Companion.application
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.data.WorkRepositoryImpl
import com.yeong.android_architecture_blue_print_project.data.local.LocalWorkDataSourceImpl
import com.yeong.android_architecture_blue_print_project.data.local.WorkDAO
import com.yeong.android_architecture_blue_print_project.data.local.WorkDatabase
import com.yeong.android_architecture_blue_print_project.ui.detail.DetailWorkViewModel
import com.yeong.android_architecture_blue_print_project.ui.edit.EditWorkViewModel
import com.yeong.android_architecture_blue_print_project.ui.support.ResourceProvider
import com.yeong.android_architecture_blue_print_project.ui.works.WorksViewModel

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    bundle: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, bundle) {

    companion object {
        private val workDao: WorkDAO by lazy {
            Room.databaseBuilder(application, WorkDatabase::class.java, "work.db")
                .build()
                .workDao()
        }

        private val workRepository: WorkRepository by lazy {
            WorkRepositoryImpl(
                LocalWorkDataSourceImpl(workDao)
            )
        }

        private val resourceProvider: ResourceProvider by lazy {
            ResourceProvider(application)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T =
        when {
            modelClass.isAssignableFrom(WorksViewModel::class.java) -> {
                WorksViewModel(workRepository) as T
            }
            modelClass.isAssignableFrom(EditWorkViewModel::class.java) -> {
                EditWorkViewModel(workRepository, resourceProvider, handle) as T
            }
            modelClass.isAssignableFrom(DetailWorkViewModel::class.java) -> {
                DetailWorkViewModel(workRepository, resourceProvider, handle) as T
            }
            else -> throw Exception("not found viewModel class : ${modelClass.canonicalName}")
        }

}
