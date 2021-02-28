package com.yeong.android_architecture_blue_print_project.data.local

import androidx.room.*
import com.yeong.android_architecture_blue_print_project.data.Work

@Dao
interface WorkDAO {

    @Query("SELECT * FROM Work")
    suspend fun allWork(): List<Work>

    @Query("SELECT * FROM Work WHERE isComplete = :complete")
    suspend fun isCompleteWorkList(complete: Boolean): List<Work>

    @Insert
    suspend fun addWork(vararg work: Work)

    @Delete
    suspend fun removeWork(vararg work: Work)

    @Update
    suspend fun updateWork(vararg work: Work)

}