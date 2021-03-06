package com.yeong.android_architecture_blue_print_project.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yeong.android_architecture_blue_print_project.App
import com.yeong.android_architecture_blue_print_project.data.WorkDataSource
import com.yeong.android_architecture_blue_print_project.data.WorkRepository
import com.yeong.android_architecture_blue_print_project.data.WorkRepositoryImpl
import com.yeong.android_architecture_blue_print_project.data.local.LocalWorkDataSourceImpl
import com.yeong.android_architecture_blue_print_project.data.local.WorkDAO
import com.yeong.android_architecture_blue_print_project.data.local.WorkDatabase
import com.yeong.android_architecture_blue_print_project.ui.support.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {
    companion object {
        @Provides
        fun bindWorkRoomDataBase(context: Context) =
            Room.databaseBuilder(context, WorkDatabase::class.java, "work.db")
                .build()

        @Provides
        fun bindWorkDao(workDatabase: WorkDatabase): WorkDAO = workDatabase.workDao()

    }

//    @Singleton
//    @Binds
//    abstract fun bindResourceProvider(resourceProvider: ResourceProvider): ResourceProvider

//    @Singleton
    @Binds
    abstract fun bindWorkRepository(repo: WorkRepositoryImpl): WorkRepository

//    @Singleton
    @Binds
    abstract fun bindLocalDataSource(dataSource: LocalWorkDataSourceImpl): WorkDataSource
}
