package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yeong.android_architecture_blue_print_project.data.Work

@Dao
interface WorkDAO {

    @Query("SELECT * FROM Work")
    fun allWork(): List<Work>

    @Insert
    fun addWork(vararg work: Work)

    @Delete
    fun removeWork(vararg work: Work)

}