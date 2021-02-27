package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yeong.android_architecture_blue_print_project.data.Work

@Database(entities = [Work::class], version = 1)
abstract class WorkDatabase : RoomDatabase(){
    abstract fun workDao(): WorkDAO
}