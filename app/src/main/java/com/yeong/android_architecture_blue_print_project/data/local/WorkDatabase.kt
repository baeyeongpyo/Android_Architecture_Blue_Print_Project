package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.Database
import com.yeong.android_architecture_blue_print_project.data.Work

@Database(entities = [Work::class], version = 1)
abstract class WorkDatabase {
    abstract fun workDao(): WorkDAO
}