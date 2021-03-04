package com.yeong.android_architecture_blue_print_project.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.data.WorkRepositoryImpl
import com.yeong.android_architecture_blue_print_project.data.local.LocalWorkDataSourceImpl
import com.yeong.android_architecture_blue_print_project.data.local.WorkDAO
import com.yeong.android_architecture_blue_print_project.data.local.WorkDatabase
import com.yeong.android_architecture_blue_print_project.ui.support.ResourceProvider
import com.yeong.android_architecture_blue_print_project.ui.tasks.TaskViewModel

class ViewModelFactory private constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {

        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application)
            }
            return INSTANCE!!
        }

    }

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

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(TaskViewModel::class.java) -> {
                TaskViewModel(workRepository) as T
            }
            else -> throw Exception("not found viewModel class : ${modelClass.canonicalName}")
        }

}
